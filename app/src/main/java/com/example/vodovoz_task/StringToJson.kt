package com.example.vodovoz_task

import android.content.ContentResolver
import com.google.gson.JsonObject
import com.google.gson.JsonParser


fun StringToJson(contentResolver: ContentResolver): JsonObject? {
    val jsonObject: JsonObject = JsonParser.parseString(getFileText(contentResolver)).getAsJsonObject()

    if(jsonObject.get("status").asString == "Success"){
        return jsonObject
    }
    else{
        return null
    }
}