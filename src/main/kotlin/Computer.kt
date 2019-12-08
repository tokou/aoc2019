
sealed class Op(val code: Int, val params: Int) {
    data class Add(val immediateL: Boolean, val immediateR: Boolean): Op(1, 3)
    data class Mul(val immediateL: Boolean, val immediateR: Boolean): Op(2, 3)
    object In: Op(3, 1) { override fun toString(): String = "In" }
    data class Out(val immediate: Boolean): Op(4, 1)
    data class Jmp(val immediateL: Boolean, val immediateR: Boolean): Op(5, 2)
    data class JmpF(val immediateL: Boolean, val immediateR: Boolean): Op(6, 2)
    data class Lt(val immediateL: Boolean, val immediateR: Boolean): Op(7, 3)
    data class Eq(val immediateL: Boolean, val immediateR: Boolean): Op(8, 3)
    object Halt: Op(99, 0) { override fun toString(): String = "Halt" }
}

fun List<Int>.getValue(address: Int, immediate: Boolean = true): Int =
    get(address).let { if (immediate) it else get(it) }

fun List<Int>.compute(
    inputSeq: Sequence<Int>,
    init: (MutableList<Int>) -> Unit = {},
    inspect: (MutableList<Int>) -> Unit = {},
    debug: Boolean = false
): Sequence<Int> = sequence {
    val memory = this@compute.toMutableList()
    val inputs = inputSeq.iterator()

    init(memory)

    var ip = 0
    loop@while (true) {
        val instruction = memory[ip]
        val code = instruction % 100
        val modes = (instruction / 100).let { Triple(it % 10 == 1, it / 10 % 10 == 1, it / 100 == 1) }
        if (debug) println("ip $ip ins $instruction code $code mod $modes")
        val op = when(code) {
            1 -> Op.Add(modes.first, modes.second)
            2 -> Op.Mul(modes.first, modes.second)
            3 -> Op.In
            4 -> Op.Out(modes.first)
            5 -> Op.Jmp(modes.first, modes.second)
            6 -> Op.JmpF(modes.first, modes.second)
            7 -> Op.Lt(modes.first, modes.second)
            8 -> Op.Eq(modes.first, modes.second)
            99 -> Op.Halt
            else -> error("Unknown op code $code $instruction")
        }
        if (debug) println("ip $ip op $op")
        var nextIp: Int? = null
        when (op) {
            is Op.Add -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                val o = memory.getValue(ip + 3)
                memory[o] = l + r
                if (debug) println("$$o $l $r ${memory[o]}")
            }
            is Op.Mul -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                val o = memory.getValue(ip + 3)
                memory[o] = l * r
                if (debug) println("$$o $l $r ${memory[o]}")
            }
            Op.In -> {
                val o = memory.getValue(ip + 1)
                memory[o] = inputs.next()
                if (debug) println("$$o ${memory[o]}")
            }
            is Op.Out -> {
                val o = memory.getValue(ip + 1, op.immediate)
                yield(o)
            }
            is Op.Jmp -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                if (l != 0) nextIp = r
                if (debug) println("$l $r $nextIp")
            }
            is Op.JmpF -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                if (l == 0) nextIp = r
                if (debug) println("$l $r $nextIp")
            }
            is Op.Lt -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                val o = memory.getValue(ip + 3)
                memory[o] = if (l < r) 1 else 0
                if (debug) println("$$o $l $r ${memory[o]}")
            }
            is Op.Eq -> {
                val l = memory.getValue(ip + 1, op.immediateL)
                val r = memory.getValue(ip + 2, op.immediateR)
                val o = memory.getValue(ip + 3)
                memory[o] = if (l == r) 1 else 0
                if (debug) println("$$o $l $r ${memory[o]}")
            }
            Op.Halt -> break@loop
        }
        ip = nextIp ?: (ip + op.params + 1)
    }
    inspect(memory)
}
