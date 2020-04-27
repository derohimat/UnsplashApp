package net.derohimat.unsplashapi.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("total")
        val total: Int = 0,
        @SerializedName("total_pages")
        val totalPages: Int = 0,
        @SerializedName("results")
        val results: List<ImageResponse>? = null
)