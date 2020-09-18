package com.inconsciente.colectiv.model

class Area constructor(name:String, zipcodes:List<String>):Areainterface {
    override var name = name
    var zipcodes = zipcodes
    override fun toString(): String {
        return this.name
}
}