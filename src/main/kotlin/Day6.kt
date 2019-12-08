import java.util.*

fun List<Pair<String, String>>.asGraph() = groupBy { it.first }.mapValues { it.value.map { v -> v.second } }

fun dfs(graph: Map<String, List<String>>, vertex: String): Int {
    fun dfsRec(graph: Map<String, List<String>>, vertex: String, i: Int): Int {
        val explored = mutableSetOf(vertex)
        return graph[vertex]?.filterNot { e -> explored.contains(e) }?.map { e ->
            val r = dfsRec(graph, e, i+1)
            explored += e
            r
        }?.max() ?: i
    }
    return dfsRec(graph, vertex, 0)
}

fun bfs(graph: Map<String, List<String>>, vertex: String, dest: String): Int {
    fun bfsRec(graph: Map<String, List<String>>, vertex: String, dest: String): Map<String, String> {
        val queue = PriorityQueue<String>()
        val discovered = mutableSetOf(vertex)
        val parents = mutableMapOf<String, String>()
        queue.add(vertex)
        while (queue.isNotEmpty()) {
            val v = queue.remove()
            if (v == dest) return parents
            graph[v]?.forEach { w ->
                if (!discovered.contains(w)) {
                    discovered.add(w)
                    parents[w] = v
                    queue.add(w)
                }
            }
        }
        return parents
    }
    val link = bfsRec(graph, vertex, dest)
    var i = 0
    var v = dest
    while (v != vertex) {
        v = link[v] ?: error("Interrupted link")
        i++
    }
    return i
}

fun Map<String, List<String>>.totalOrbits(): Int = map { dfs(this, it.key) }.sum()

fun Map<String, List<String>>.minimumTransfers(from: String, to: String): Int = bfs(this, from, to) - 2

fun main() {

    val input1 = resource("input6").readOrbitsDirected().asGraph()
    val partOne = input1.totalOrbits()
    println(partOne)

    val input2 = resource("input6").readOrbitsUndirected().asGraph()
    val partTwo = input2.minimumTransfers("YOU", "SAN")
    println(partTwo)
}