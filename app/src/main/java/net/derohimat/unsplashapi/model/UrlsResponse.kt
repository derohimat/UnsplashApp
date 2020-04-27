package net.derohimat.unsplashapi.model

import com.google.gson.annotations.SerializedName

data class UrlsResponse(
        @SerializedName("regular")
        val regular: String?,
        @SerializedName("small")
        val small: String?
)