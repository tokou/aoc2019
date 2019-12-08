import org.junit.Assert.*
import org.junit.Test

class Day4Tests {

    @Test
    fun testCheckDoubleNotMore() {
        assertTrue(checkDoubleNotMore(112233))
        assertFalse(checkDoubleNotMore(123444))
        assertTrue(checkDoubleNotMore(111122))
    }
}