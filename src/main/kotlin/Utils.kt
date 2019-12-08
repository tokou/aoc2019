import java.io.File

private const val buildDir = "./build"

fun resource(name: String): File = File("$buildDir/resources/main/$name.txt")

fun Int.check(other: Int): String = if (this == other) "✅ $this" else "❎ expecting $other, actual $this"

fun File.readIntLines() = readLines().map(String::toInt)

fun File.readIntValues() = readText().split(",").map(String::toInt)

fun File.readPointLines() = readLines().map(String::asLine)

fun File.readRange() = readText().split("-").map(String::toInt).let { IntRange(it[0], it[1]) }

fun File.readOrbitsDirected() = readLines().map { it.split(")") }.map { it[1] to it[0] }

fun File.readOrbitsUndirected() = readLines().map { it.split(")") }.flatMap { listOf(it[0] to it[1], it[1] to it[0]) }
