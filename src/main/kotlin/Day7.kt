
fun List<Int>.permutations(): Set<List<Int>> = when (size) {
    0 -> emptySet()
    1 -> setOf(this)
    else -> {
        val res = mutableSetOf<List<Int>>()
        for (i in 0 until size) {
            val e = get(i)
            val rem = take(i) + drop(i + 1)
            for (p in rem.permutations()) {
                res += listOf(e) + p
            }
        }
        res
    }
}

fun amplify(program: List<Int>, phases: List<Int>): Int = phases.fold(0) { res, phase ->
    program.toMutableList().compute(sequenceOf(phase, res)).last()
}

fun amplify2(program: List<Int>, phases: List<Int>, feedback: Boolean = false): Int {
    var redirectA: Sequence<Int> = emptySequence()
    val inA = sequence {
        yield(phases[0])
        yield(0)
        yieldAll(redirectA)
    }
    val outA = program.toMutableList().compute(inA)
    val inB = sequenceOf(phases[1]) + outA
    val outB = program.toMutableList().compute(inB)
    val inC = sequenceOf(phases[2]) + outB
    val outC = program.toMutableList().compute(inC)
    val inD = sequenceOf(phases[3]) + outC
    val outD = program.toMutableList().compute(inD)
    val inE = sequenceOf(phases[4]) + outD
    val outE = program.toMutableList().compute(inE)
    if (feedback) redirectA = outE
    return outE.last()
}

fun main() {

    val input = resource("input7").readIntValues()

    val phases = listOf(0, 1, 2, 3, 4).permutations()
    val partOne = phases.map { amplify(input, it) }.max()

    println(partOne)

    val phases2 = listOf(5, 6, 7, 8, 9).permutations()
    val partTwo = phases2.map { amplify2(input, it, true) }.max()

    println(partTwo)
}
