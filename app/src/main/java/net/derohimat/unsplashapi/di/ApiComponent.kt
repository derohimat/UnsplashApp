package net.derohimat.unsplashapi.di

import dagger.Component
import net.derohimat.unsplashapi.network.UnsplashService
import net.derohimat.unsplashapi.viewmodel.MainViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(services: UnsplashService)

    fun inject(viewModel: MainViewModel)
}