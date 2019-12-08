import org.junit.Assert.assertEquals
import org.junit.Test

class Day3Tests {

    @Test
    fun testDistance1() {
        val res = distance(
            "R8,U5,L5,D3".asLine(),
            "U7,R6,D4,L4".asLine()
        )
        assertEquals(6, res)
    }

    @Test
    fun testDistance2() {
        val res = distance(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72".asLine(),
            "U62,R66,U55,R34,D71,R55,D58,R83".asLine()
        )
        assertEquals(159, res)
    }

    @Test
    fun testDistance3() {
        val res = distance(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".asLine(),
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".asLine()
        )
        assertEquals(135, res)
    }

    @Test
    fun testDelay1() {
        val res = delay(
            "R8,U5,L5,D3".asLine(),
            "U7,R6,D4,L4".asLine()
        )
        assertEquals(30, res)
    }

    @Test
    fun testDelay2() {
        val res = delay(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".asLine(),
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".asLine()
        )
        assertEquals(410, res)
    }

    @Test
    fun testDelay3() {
        val res = delay(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72".asLine(),
            "U62,R66,U55,R34,D71,R55,D58,R83".asLine()
        )
        assertEquals(610, res)
    }
}