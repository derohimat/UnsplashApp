package net.derohimat.unsplashapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.derohimat.unsplashapi.R
import net.derohimat.unsplashapi.utils.setSystemBarColor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.title = resources.getString(R.string.app_name)
        setSystemBarColor(this)
    }

    private fun setupViewPager() {
        val adapterVp = TabPagerAdapter(baseContext, supportFragmentManager)
        viewPager.adapter = adapterVp
        tabLayout.setupWithViewPager(viewPager)
    }

}
