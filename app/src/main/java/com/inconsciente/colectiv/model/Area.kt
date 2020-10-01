package com.inconsciente.colectiv.model

class Area constructor(override var name: String, var zipcodes: List<String>):Areainterface {
    override fun toString(): String {
        return this.name
}
}