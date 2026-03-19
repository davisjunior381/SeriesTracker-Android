package com.example.seriestracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TvShowDto(
    val id: Int,
    val name: String,
    val permalink: String,
    @SerializedName("start_date") val startDate: String?,
    @SerializedName("end_date") val endDate: String?,      // ← ajouté (API réelle)
    val country: String,
    val network: String,
    val status: String,
    @SerializedName("image_thumbnail_path") val imageThumbnailPath: String
)