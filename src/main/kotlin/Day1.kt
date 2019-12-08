
fun calculateFuel(mass: Int): Int =
    (mass / 3 - 2).coerceAtLeast(0)

fun calculateTotalFuel(mass: Int): Int =
    if (mass <= 0) 0
    else calculateFuel(mass).let { it + calculateTotalFuel(it) }

fun main() {

    val input = resource("input1").readIntLines()

    val fuel = input.map(::calculateFuel).sum()

    println(fuel)

    val total = input.map(::calculateTotalFuel).sum()

    println(total)
}