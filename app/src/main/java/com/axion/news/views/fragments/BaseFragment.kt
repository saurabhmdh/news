package com.axion.news.views.fragments

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu()
    }
}