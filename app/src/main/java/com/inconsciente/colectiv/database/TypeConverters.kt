package com.inconsciente.colectiv.database

import androidx.room.TypeConverter

val SEPARATOR = "|"

class ZipcodesConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun zipcodesToString(zipcodes: List<String>?): String? =
            zipcodes?.map { it }?.joinToString(separator = SEPARATOR)

        @TypeConverter
        @JvmStatic
        fun stringToZipcodes(zipcodesString: String?): List<String>? =
            zipcodesString?.split(SEPARATOR)?.map { it }?.toList()
    }
}