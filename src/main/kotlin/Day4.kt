
fun checkDouble(password: Int): Boolean {
    val string = password.toString()
    val pairs = string.zip("0" + string.dropLast(1))
    return pairs.any { (l, r) -> l == r }
}

fun checkIncrease(password: Int): Boolean {
    val string = password.toString()
    val pairs = string.zip("0" + string.dropLast(1))
    return pairs.none { (l, r) -> l < r }
}

fun checkLength(password: Int): Boolean {
    val string = password.toString()
    return string.length == 6
}

fun checkDoubleNotMore(password: Int): Boolean {
    val string = password.toString()
    val r = "0" + string.dropLast(1)
    val l = string.drop(1) + "0"
    val pairs = string.toList().zip(r.zip(l))
    return pairs
        .filter { (o, n) -> o == n.first }
        .groupBy { it.first }
        .map { it.key to it.value.size }
        .any { it.second == 1 }
}

fun main() {
    val input = resource("input4").readRange()

    val partOne = input.filter(::checkLength).filter(::checkIncrease).filter(::checkDouble).count()

    println(partOne)

    val partTwo = input.filter(::checkLength).filter(::checkIncrease).filter(::checkDoubleNotMore).count()

    println(partTwo)
}