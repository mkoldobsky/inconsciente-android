package com.inconsciente.colectiv.model

import com.inconsciente.colectiv.network.AreaProperty
import org.junit.Assert.*
import org.junit.Test

class AreasTest{
    @Test
    fun shouldGetAreaThanContainsHasZipcode(){
        var areaList = arrayListOf<AreaProperty>(AreaProperty("uno", arrayListOf<String>("uno", "dos")))
        val areas = Areas(areaList)

        var area = areas.getAreaByZipcode("dos")

        assertEquals("uno", area.toString())

    }
    @Test
    fun shouldGetNoAreaWhenNoCoantainsZipcode(){
        var areaList = arrayListOf<AreaProperty>(AreaProperty("uno", arrayListOf<String>("uno", "dos")))
        val areas = Areas(areaList)

        var area = areas.getAreaByZipcode("tres")

        assertEquals("noarea", area.toString())
    }
}