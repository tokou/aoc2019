import kotlin.math.abs

typealias Line = List<String>

fun String.asLine(): Line = split(",")

data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "($x,$y)"

    fun distance(other: Point = Point(0, 0)) = abs(x - other.x) + abs(y - other.y)

    infix fun collectRight(d: Int) = (0..d).map { Point(x + it, y) }.toSet()
    infix fun collectUp(d: Int) = (0..d).map { Point(x, y + it) }.toSet()
    infix fun collectLeft(d: Int) = (0..d).map { Point(x - it, y) }.toSet()
    infix fun collectDown(d: Int) = (0..d).map { Point(x, y - it) }.toSet()

    infix fun right(d: Int) = Point(x + d, y)
    infix fun up(d: Int) = Point(x, y + d)
    infix fun left(d: Int) = Point(x - d, y)
    infix fun down(d: Int) = Point(x, y - d)
}

fun intersections(lines: Array<out Line>): List<Point> = lines.map { line ->
    line.fold(setOf(Point(0, 0))) { acc, move ->
        val point = acc.last()
        val dist = move.drop(1).toInt()
        val next = when (move[0]) {
            'R' -> point collectRight dist
            'U' -> point collectUp dist
            'L' -> point collectLeft dist
            'D' -> point collectDown dist
            else -> error("Unknown operation")
        }
        acc + next
    }
}
    .reduce { acc, next -> acc.intersect(next) }
    .filterNot { it == Point(0, 0) }

fun distance(vararg lines: Line): Int = intersections(lines).map { it.distance() }.min() ?: 0

fun Line.lengthTo(target: Point): Int = fold((0 to Point(0, 0))) { acc, move ->
    var (total, point) = acc
    var dist = move.drop(1).toInt()
    while (dist > 0 && point != target) {
        point = when (move[0]) {
            'R' -> point right 1
            'U' -> point up 1
            'L' -> point left 1
            'D' -> point down 1
            else -> error("Unknown operation")
        }
        total++
        dist--
    }
    total to point
}.first

fun delay(vararg lines: Line): Int = intersections(lines).map { p -> lines.sumBy { it.lengthTo(p) } }.min() ?: 0

fun main() {

    val input = resource("input3").readPointLines()

    val distance = distance(*input.toTypedArray())

    println(distance)

    val delay = delay(*input.toTypedArray())

    println(delay)
}