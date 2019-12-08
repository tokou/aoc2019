import org.junit.Assert.assertEquals
import org.junit.Test

class Day2Tests {

    @Test
    fun testComputer1() {
        val res = listOf(1,0,0,0,99).compute(0, 0)
        val resLegacy = listOf(1,0,0,0,99).legacyCompute(0, 0)
        assertEquals(2, res)
        assertEquals(2, resLegacy)
    }

    @Test
    fun testComputer2() {
        val res = listOf(2,3,0,3,99).compute(3, 0)
        val resLegacy = listOf(2,3,0,3,99).legacyCompute(3, 0)
        assertEquals(2, res)
        assertEquals(2, resLegacy)
    }

    @Test
    fun testComputer3() {
        val res = listOf(2,4,4,5,99,0).compute(4, 4)
        val resLegacy = listOf(2,4,4,5,99,0).legacyCompute(4, 4)
        assertEquals(2, res)
        assertEquals(2, resLegacy)
    }

    @Test
    fun testComputer4() {
        val res = listOf(1,1,1,4,99,5,6,0,99).compute(1, 1)
        val resLegacy = listOf(1,1,1,4,99,5,6,0,99).legacyCompute(1, 1)
        assertEquals(30, resLegacy)
    }

    @Test
    fun testComputer5() {
        val res = listOf(1,9,10,3,2,3,11,0,99,30,40,50).compute(9, 10)
        assertEquals(3500, res)
    }
}