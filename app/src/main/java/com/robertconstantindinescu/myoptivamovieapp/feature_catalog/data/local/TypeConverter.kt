package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Attachment
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response


class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToResponse(data:String): List<Attachment> {
        val lisType = object: TypeToken<List<Attachment>>() {}.type
        return gson.fromJson(data, lisType)
    }

    @TypeConverter
    fun resultToString(result: List<Attachment>): String{
        return gson.toJson(result)
    }
}