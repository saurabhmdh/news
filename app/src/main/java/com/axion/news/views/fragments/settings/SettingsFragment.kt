package com.axion.news.views.fragments.settings


import android.os.Bundle
import com.axion.news.di.Injectable
import androidx.preference.PreferenceFragmentCompat
import com.axion.news.R


class SettingsFragment: PreferenceFragmentCompat(), Injectable {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }


    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu()
    }
}