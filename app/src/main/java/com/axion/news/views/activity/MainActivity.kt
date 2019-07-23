package com.axion.news.views.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.axion.news.R
import com.axion.news.databinding.ActivityMainBinding

import com.google.android.material.navigation.NavigationView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        val topLevelDestinations = setOf(R.id.navigation_home, R.id.navigation_browse)

        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawerLayout).build()
        navController = Navigation.findNavController(this, R.id.home_navigation_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

//        binding.navigation.setupWithNavController(navController)
//
//        binding.navigation.setOnNavigationItemSelectedListener{menuItem ->
//            when(menuItem.itemId) {
//                R.id.nav_features -> {
//                    navController.navigate(R.id.navigation_home)
//                }
//                R.id.nav_browse -> {
//                    navController.navigate(R.id.navigation_browse)
//
//                }
//            }
//            NavigationUI.onNavDestinationSelected(menuItem, navController)
//        }


        with(binding.navView) {
            setupWithNavController(navController)
            setNavigationItemSelectedListener(NavigationListener())
        }
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector

    //Add menu as profile
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    internal inner class NavigationListener : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            Timber.i("saurabh onNavigationItemSelected = ${item.itemId}")
            when (item.itemId) {

            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
}