
fun List<Int>.legacyCompute(noun: Int, verb: Int): Int? {
    val memory = this.toMutableList()

    memory[1] = noun
    memory[2] = verb

    var i = 0
    while (true) {
        when(memory[i]) {
            1 -> {
                val a = memory[memory[i+1]]
                val b = memory[memory[i+2]]
                val r = memory[i+3]
                memory[r] = a + b
            }
            2 -> {
                val a = memory[memory[i+1]]
                val b = memory[memory[i+2]]
                val r = memory[i+3]
                memory[r] = a * b
            }
            99 -> return memory[0]
            else -> return null
        }
        i += 4
    }
}

fun List<Int>.compute(noun: Int, verb: Int): Int? {
    var result: Int? = null
    compute(emptySequence(), { memory ->
        memory[1] = noun
        memory[2] = verb
    }, { memory ->
        result = memory[0]
    }).count() // just to trigger the sequence
    return result
}

fun main() {

    val input = resource("input2").readIntValues()

    val partOne = input.compute(12, 2)

    println(partOne)

    val params = generateSequence(0 to 0) { (l, r) -> when {
        r == 99 -> l + 1 to 0
        l > 99 -> null
        else -> l to r + 1
    } }

    val partTwo = params
        .map { it to input.compute(it.first, it.second) }
        .first { it.second == 19690720 }
        .first
        .let { it.first * 100 + it .second }

    println(partTwo)
}