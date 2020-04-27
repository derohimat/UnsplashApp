package net.derohimat.unsplashapi.network

import io.reactivex.Single
import net.derohimat.unsplashapi.di.DaggerApiComponent
import net.derohimat.unsplashapi.model.ApiResponse
import javax.inject.Inject

class UnsplashService {

    @Inject
    lateinit var api: UnsplashApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImages(query: String, page: Int): Single<ApiResponse> {
        return api.getImages(query, page)
    }

}