package com.axion.news.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axion.news.databinding.FragmentFeaturesBinding
import com.axion.news.databinding.FragmentHomeBinding
import com.axion.news.di.Injectable
import com.axion.news.util.fragment.autoCleared


class HomeFragment: Fragment(), Injectable {

    var mBinding by autoCleared<FragmentHomeBinding>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}