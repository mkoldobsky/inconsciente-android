package com.inconsciente.colectiv.model

import com.inconsciente.colectiv.network.AreaProperty

class Areas constructor(areaList:List<AreaProperty>) {
    val areas = areaList.asModel()

    fun getAreaByZipcode(zipcode:String):Areainterface{
        return  areas.firstOrNull() { area -> area.zipcodes.contains(zipcode) } ?: NoArea()
    }


}
fun List<AreaProperty>.asModel(): List<Area>{
    return map {
        Area(
                name = it.name,
                zipcodes = it.zipcodes
        )
    }
}