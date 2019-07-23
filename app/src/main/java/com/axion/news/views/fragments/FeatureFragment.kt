package com.axion.news.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.PagerSnapHelper
import com.axion.news.databinding.FragmentFeaturesBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.services.AppExecutors
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.home.FeatureViewModel
import com.axion.news.views.adapter.FeatureImageAdapter
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
        setupUI()
        Timber.i("FeatureFragment view started.")
    }

    private fun setupUI() {
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getAllContent().observe(this, Observer { networkResponse ->
            when (networkResponse?.status) {
                Status.SUCCESS -> {
                    val content = networkResponse.data?.first { it.featured && it.featureImage.isNotEmpty() }
                    mBinding.content = content
                    Timber.i("loading following data = $content")
                }
                else -> {Timber.i("There is some problem to load content")}
            }
        })
    }

    private fun setupViewModel() {
        viewModel = createViewModel()
    }

    private fun createViewModel(): FeatureViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeatureViewModel::class.java)
}