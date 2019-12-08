import org.junit.Assert.assertEquals
import org.junit.Test

class Day5Tests {

    @Test
    fun testInputOutput() {
        val res = listOf(3, 0, 4, 0, 99).compute(sequenceOf(42)).last()
        assertEquals(42, res)
    }

    @Test
    fun testModes() {
        val res1 = listOf(1101, 14, 32, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(14 + 32, res1)
        val res2 = listOf(101, 14, 1, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(14 + 14, res2)
        val res3 = listOf(1001, 2, 32, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(64, res3)
        val res4 = listOf(1102, 14, 32, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(14 * 32, res4)
        val res5 = listOf(102, 14, 1, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(14 * 14, res5)
        val res6 = listOf(1002, 2, 32, 0, 4, 0, 99).compute(emptySequence()).last()
        assertEquals(32 * 32, res6)
    }

    @Test
    fun testJumps() {
        val input1 = listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(0, input1.compute(sequenceOf(1)).last())
        assertEquals(1, input1.compute(sequenceOf(8)).last())
        val input2 = listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(0, input2.compute(sequenceOf(9)).last())
        assertEquals(1, input2.compute(sequenceOf(1)).last())
        val input3 = listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99)
        assertEquals(0, input3.compute(sequenceOf(12)).last())
        assertEquals(1, input3.compute(sequenceOf(8)).last())
        val input4 = listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99)
        assertEquals(0, input4.compute(sequenceOf(11)).last())
        assertEquals(1, input4.compute(sequenceOf(2)).last())
        val input5 = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
        assertEquals(0, input5.compute(sequenceOf(0)).last())
        assertEquals(1, input5.compute(sequenceOf(3)).last())
        val input6 = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
        assertEquals(0, input6.compute(sequenceOf(0)).last())
        assertEquals(1, input6.compute(sequenceOf(5)).last())
    }

    @Test
    fun testLarge() {
        val input = listOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99)
        assertEquals(999, input.compute(sequenceOf(1)).last())
        assertEquals(1000, input.compute(sequenceOf(8)).last())
        assertEquals(1001, input.compute(sequenceOf(12)).last())
    }
}