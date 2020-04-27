package net.derohimat.unsplashapi.di

import dagger.Module
import dagger.Provides
import net.derohimat.unsplashapi.network.UnsplashApi
import net.derohimat.unsplashapi.network.UnsplashService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://api.unsplash.com/"

    @Provides
    fun provideCountriesApi(): UnsplashApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UnsplashApi::class.java)
    }

    @Provides
    fun provideCountriesService(): UnsplashService {
        return UnsplashService()
    }
}