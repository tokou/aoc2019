

fun main() {
    val input = resource("input5").readIntValues()

    val partOne = input.compute(sequenceOf(1)).last()

    println(partOne)

    val partTwo = input.compute(sequenceOf(5)).last()

    println(partTwo)
}