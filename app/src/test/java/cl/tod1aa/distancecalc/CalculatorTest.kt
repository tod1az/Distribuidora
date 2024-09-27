package cl.tod1aa.distancecalc

import org.junit.Test
import org.junit.Assert.*
import cl.tod1aa.distancecalc.data.Calculator
class CalculatorTest {
    @Test
    fun testCalculatorDistance(){
        val position1 = arrayOf<Double>( -33.0373824786368, -71.34742039306286 )
        val position2 = arrayOf<Double>( -33.04376359310534, -71.36529999821455 )
        val aproxDistance = 1.9
        val result = Calculator.distance(position1, position2)
        assertEquals(aproxDistance,result, 1.0)
    }
}