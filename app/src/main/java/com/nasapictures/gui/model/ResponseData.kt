package com.nasapictures.gui.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
    data class ResponseDataItem(
        val copyright: String,
        val date: String,
        val explanation: String,
        val hdurl: String,
        @Json(name = "media_type")
        val mediaType: String,
        @Json(name = "service_version")
        val serviceVersion: String,
        val title: String,
        val url: String
    )