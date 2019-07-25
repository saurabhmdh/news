package com.axion.news.views.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.axion.news.databinding.FragmentFeaturesBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.home.FeatureViewModel
import com.axion.news.views.fragments.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class FeatureFragment: BaseFragment(), Injectable {
    var mBinding by autoCleared<FragmentFeaturesBinding>()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(FeatureViewModel::class.java) }


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
                Status.LOADING -> {}
                Status.ERROR -> {Timber.i("There is some problem to load content")}
            }
        })

        mBinding.container.setOnClickListener {
            mBinding.content?.let {
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeToDetail(it.title, it))
            }
        }
    }
}