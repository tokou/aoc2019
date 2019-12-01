import java.io.File

private const val buildDir = "/Users/tarek/Sources/perso/aoc2019/build"

fun resource(name: String): File = File("$buildDir/resources/main/$name.txt")

fun Int.check(other: Int): String = if (this == other) "✅ $this" else "❎ expecting $other, actual $this"
