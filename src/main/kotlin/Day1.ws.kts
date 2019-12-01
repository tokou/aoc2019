import resource
import check

val input = resource("input1").readLines().map(String::toInt)

/* --- Part One --- */

fun calculateFuel(mass: Int): Int =
    (mass / 3 - 2).coerceAtLeast(0)

calculateFuel(12).check(2)
calculateFuel(14).check(2)
calculateFuel(1969).check(654)
calculateFuel(100756).check(33583)
calculateFuel(2).check(0)

val total = input.map(::calculateFuel).sum()

total.check(3364035)

/* --- Part two --- */

fun calculateFuel2(mass: Int): Int =
    if (mass <= 0) 0
    else calculateFuel(mass).let { it + calculateFuel2(it) }

calculateFuel2(14).check(2)
calculateFuel2(1969).check(966)
calculateFuel2(100756).check(50346)

val total2 = input.map(::calculateFuel2).sum()

total2.check(5043167)
