package net.derohimat.unsplashapi

import androidx.multidex.MultiDexApplication
import glimpse.core.Glimpse

class MyApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Glimpse.init(this)
    }
}