import resource
import check

val input = resource("input2")
        .readText()
        .split(",")
        .map(String::toInt)

/* --- Part One --- */

fun List<Int>.compute(noun: Int, verb: Int): Int? {
    val memory = this.toMutableList()

    memory[1] = noun
    memory[2] = verb

    var i = 0
    while (true) {
        val a = memory[memory[i+1]]
        val b = memory[memory[i+2]]
        val r = memory[i+3]
        when(memory[i]) {
            1 -> memory[r] = a + b
            2 -> memory[r] = a * b
            99 -> return memory[0]
            else -> return null
        }
        i += 4
    }
}

input.compute(12, 2)?.check(3850704)

/* --- Part two --- */

val params = generateSequence(0 to 0) { (l, r) -> when {
    r == 99 -> l + 1 to 0
    l > 99 -> null
    else -> l to r + 1
} }

params
    .map { it to input.compute(it.first, it.second) }
    .first { it.second == 19690720 }
    .first
    .let { it.first * 100 + it .second }
    .check(6718)
