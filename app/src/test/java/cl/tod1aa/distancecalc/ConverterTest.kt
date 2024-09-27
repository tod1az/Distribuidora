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
}