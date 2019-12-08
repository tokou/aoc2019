import org.junit.Assert.assertEquals
import org.junit.Test

class Day8Tests {

    private val ex1 = "123456789012"
    private val ex2 = "0222112222120000"

    @Test
    fun testParsing() {
        assertEquals(2, ex1.asImage(3, 2).size)
    }

    @Test
    fun testDecoding() {
        val res = ex2.asImage(2, 2).decode()
        assertEquals("0110", res.joinToString("") { it.joinToString("") })
    }
}