package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response


class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToResponse(data:String): Response {
        val lisType = object: TypeToken<Response>() {}.type
        return gson.fromJson(data, lisType)
    }

    @TypeConverter
    fun resultToString(result: Response): String{
        return gson.toJson(result)
    }
}