package com.axion.news.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper

import com.axion.news.databinding.FragmentHomeBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.network.responses.Content
import com.axion.news.services.AppExecutors
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.home.FeatureViewModel
import com.axion.news.views.adapter.FeatureImageAdapter
import timber.log.Timber
import javax.inject.Inject


class HomeFragment: Fragment(), Injectable {

    var mBinding by autoCleared<FragmentHomeBinding>()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FeatureViewModel
    var imageAdapter by autoCleared<FeatureImageAdapter>()
    @Inject lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupListView()
        setupViewModel()
        setupObserver()
    }

    private fun setupListView() {
        imageAdapter = FeatureImageAdapter(appExecutors, object : FeatureImageAdapter.ClickCallback{
            override fun onClick(data: Content, position: Int) {
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeToDetail(data))
            }
        })
        with(mBinding.bottom) {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            //addItemDecoration(LinePagerIndicatorDecoration())
            adapter = imageAdapter
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.bottom)
    }

    private fun setupObserver() {
        viewModel.getAllContent().observe(this, Observer { networkResponse ->
            when (networkResponse?.status) {
                Status.SUCCESS -> {
                    imageAdapter.submitList(networkResponse.data)
                }
                else -> {
                    Timber.i("There is some problem to load content")}
            }
        })
    }

    private fun setupViewModel() {
        viewModel = createViewModel()
    }

    private fun createViewModel(): FeatureViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeatureViewModel::class.java)
}