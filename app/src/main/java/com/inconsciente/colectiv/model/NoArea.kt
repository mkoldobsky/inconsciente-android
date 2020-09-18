package com.inconsciente.colectiv.model

class NoArea(override var name: String = "noarea") :Areainterface {
    override fun toString(): String {
        return this.name
    }
}