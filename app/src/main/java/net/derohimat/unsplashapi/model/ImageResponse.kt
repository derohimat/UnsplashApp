package net.derohimat.unsplashapi.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
        @SerializedName("id")
        val id: String?,
        @SerializedName("alt_description")
        val description: String?,
        @SerializedName("urls")
        val urls: UrlsResponse?
)