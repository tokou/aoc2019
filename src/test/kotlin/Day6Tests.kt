import org.junit.Assert.assertEquals
import org.junit.Test

class Day6Tests {

    private val example1 = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L"
        .split("\n")
        .map { it.split(")") }
        .map { it[1] to it[0] }
        .asGraph()

    private val example2 = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L\nK)YOU\nI)SAN"
        .split("\n")
        .map { it.split(")") }
        .flatMap { listOf(it[0] to it[1], it[1] to it[0]) }
        .asGraph()

    @Test
    fun testDfs() {
        assertEquals(3, dfs(example1, "D"))
        assertEquals(7, dfs(example1, "L"))
        assertEquals(42, example1.totalOrbits())
    }

    @Test
    fun testBfs() {
        assertEquals(4, example2.minimumTransfers("YOU", "SAN"))
    }
}