package com.axion.news.views.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.axion.news.R
import com.axion.news.databinding.ActivityMainBinding
import com.axion.news.views.fragments.HomeFragmentDirections
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeButtonEnabled(false)

        NavigationUI.setupActionBarWithNavController(this, Navigation.findNavController(this, R.id.home_navigation_fragment))

        binding.navigation.setOnNavigationItemSelectedListener{menuItem ->

            when(menuItem.itemId) {
                R.id.nav_features-> {
                    Navigation.findNavController(this, R.id.home_navigation_fragment).navigate(R.id.navigation_home)
                }
                R.id.nav_browse-> {
                    Navigation.findNavController(this, R.id.home_navigation_fragment).navigate(R.id.navigation_browse)
                }
            }
            true
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    //Add menu as profile
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}