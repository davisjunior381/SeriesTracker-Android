package com.example.seriestracker.model

import com.example.seriestracker.data.remote.dto.TvShowDto
data class TvShow(
    val id: Int,
    val name: String,
    val network: String,
    val country: String,
    val status: String,
    val imageUrl: String
)
fun TvShowDto.toDomain(): TvShow {
    return TvShow(
        id = id,
        name = name,
        network = network,
        country = country,
        status = status,
        imageUrl = imageThumbnailPath
    )
}