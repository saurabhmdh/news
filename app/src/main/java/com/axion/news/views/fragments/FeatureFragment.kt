package com.axion.news.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axion.news.databinding.FragmentFeaturesBinding
import com.axion.news.di.Injectable
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.home.FeatureViewModel
import timber.log.Timber
import javax.inject.Inject

class FeatureFragment: Fragment(), Injectable {
    var mBinding by autoCleared<FragmentFeaturesBinding>()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FeatureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentFeaturesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        Timber.i("FeatureFragment view started.")
    }

    private fun createViewModel(): FeatureViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeatureViewModel::class.java)
}