package cl.tod1aa.distancecalc

import org.junit.Test
import org.junit.Assert.*
import cl.tod1aa.distancecalc.data.Converter

class ConverterTest {
    @Test
    fun testConvertToRadians(){
        val result = Converter.decimalToRadians(57.2958)
        assertEquals(1.000000358,result, 1.0)
    }
    @Test
    fun testFahrenheitToCelsius(){
        val result = Converter.fahrenheitToCelsius(44.0)
        assertEquals(6.66667, result, 1.0)
    }
}