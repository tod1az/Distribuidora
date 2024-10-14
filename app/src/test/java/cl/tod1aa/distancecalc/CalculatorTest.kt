package cl.tod1aa.distancecalc

import org.junit.Test
import org.junit.Assert.*
import cl.tod1aa.distancecalc.data.Calculator
import com.google.android.gms.maps.model.LatLng
import kotlin.math.roundToInt

class CalculatorTest {
    @Test
    fun testCalculatorDistance(){
        val position1 = LatLng( -33.0373824786368, -71.34742039306286 )
        val position2 = LatLng( -33.04376359310534, -71.36529999821455 )
        val aproxDistance = 1.9
        val result = Calculator.distance(position1, position2)
        assertEquals(aproxDistance,result, 1.0)
    }

    @Test
    fun testCalculatorPrice(){
        val distance1 = 20.1
        val distance2 = 19.0

        val price1 = 50000
        val price2 = 26000
        val price3 = 20000

        val result1 = Calculator.deliveryPrice(price1, distance1)
        assertEquals(-1, result1)

        val result2 = Calculator.deliveryPrice(price1, distance2)
        assertEquals(0, result2)

        val result3 = Calculator.deliveryPrice(price2, distance2)
        var expected = distance2.roundToInt()* 150
        assertEquals(expected, result3)

        val result4 = Calculator.deliveryPrice(price3, distance2)
         expected =distance2.roundToInt() * 300
        assertEquals(expected, result4)
    }
}