import org.junit.Assert.assertEquals
import org.junit.Test

class Day1Tests {

    @Test
    fun testFuel() {
        assertEquals(calculateFuel(12), 2)
        assertEquals(calculateFuel(14), 2)
        assertEquals(calculateFuel(1969), 654)
        assertEquals(calculateFuel(100756), 33583)
        assertEquals(calculateFuel(2), 0)
    }

    @Test
    fun testTotal() {
        assertEquals(calculateTotalFuel(14), 2)
        assertEquals(calculateTotalFuel(1969), 966)
        assertEquals(calculateTotalFuel(100756), 50346)
    }
}