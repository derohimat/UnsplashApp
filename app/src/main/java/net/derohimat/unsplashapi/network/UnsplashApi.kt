package net.derohimat.unsplashapi.network

import io.reactivex.Single
import net.derohimat.unsplashapi.BuildConfig
import net.derohimat.unsplashapi.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/search/photos/?client_id=${BuildConfig.CLIENT_ID}&per_page=15")
    fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<ApiResponse>
}