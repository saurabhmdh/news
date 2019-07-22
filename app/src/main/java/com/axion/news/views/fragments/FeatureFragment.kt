package com.axion.news.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axion.news.databinding.FragmentFeaturesBinding
import com.axion.news.di.Injectable
import com.axion.news.util.fragment.autoCleared

class FeatureFragment: Fragment(), Injectable {
    var mBinding by autoCleared<FragmentFeaturesBinding>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentFeaturesBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}