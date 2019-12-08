
typealias Layer = List<List<Char>>

fun String.asImage(width: Int, height: Int): List<Layer> = toList().chunked(width).chunked(height)

fun Layer.pixelCount(pixel: Char): Int = sumBy { line -> line.count { it == pixel } }

fun List<Layer>.minCountLayer(pixel: Char): Layer =
    mapIndexed { index, layer -> index to layer.pixelCount(pixel) }
        .minBy { it.second }!!
        .first
        .let { get(it) }

fun List<Layer>.decode(): Layer = reduce { img, layer ->
    val (width, height) = layer[0].size to layer.size

    val next = mutableListOf<MutableList<Char>>().apply {
        for (i in 0 until height) {
            add(mutableListOf())
        }
    }

    for (i in 0 until width) {
        for (j in 0 until height) {
            when (img[j][i]) {
                '2' -> next[j].add(layer[j][i])
                '0', '1' -> next[j].add(img[j][i])
                else -> error("Wrong format")
            }
        }
    }
    next
}

fun Layer.draw(white: Char, black: Char) = map { line -> line.map { when (it) {
    '1' -> black
    '0' -> white
    else -> ' '
} } }

fun main() {
    val input = resource("input8").readText().asImage(25, 6)

    val layer = input.minCountLayer('0')
    val check = layer.pixelCount('1') * layer.pixelCount('2')
    println(check)

    val result = input.decode().draw('_', '█')

    result.forEach { println(it.joinToString("")) }
}