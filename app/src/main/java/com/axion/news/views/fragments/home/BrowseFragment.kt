package com.axion.news.views.fragments.home

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.axion.news.R
import com.axion.news.databinding.FragmentBrowseBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.network.responses.Content
import com.axion.news.services.AppExecutors
import com.axion.news.util.fragment.autoCleared
import com.axion.news.util.kotlin.orZero
import com.axion.news.viewmodel.home.BrowseViewModel

import com.axion.news.views.adapter.FeatureImageAdapter
import com.axion.news.views.adapter.TagAdapter
import com.axion.news.views.custom.LinePagerIndicatorDecoration
import com.axion.news.views.fragments.BaseFragment

import timber.log.Timber
import javax.inject.Inject
import kotlin.math.min

class BrowseFragment: BaseFragment(), Injectable {
    var mBinding by autoCleared<FragmentBrowseBinding>()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(BrowseViewModel::class.java) }
    private var treadingAdapter by autoCleared<FeatureImageAdapter>()
    private var magazineAdapter by autoCleared<FeatureImageAdapter>()
    var tagAdapter by autoCleared<TagAdapter>()
    @Inject lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentBrowseBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return mBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_browse, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTreading()
        setupMagazineList()
        setupTagInfo()
        setupObserver()
    }

    private fun setupTagInfo() {
        tagAdapter = TagAdapter(appExecutors)
        with (mBinding.infoTags) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tagAdapter
        }
    }

    private fun setupMagazineList() {
        magazineAdapter = FeatureImageAdapter(appExecutors, object : FeatureImageAdapter.ClickCallback{
            override fun onClick(data: Content, position: Int) {
                view?.findNavController()?.navigate(BrowseFragmentDirections.actionBrowseToDetail(data.title, data))
            }
        })

        with(mBinding.magazine) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(LinePagerIndicatorDecoration())
            adapter = magazineAdapter
        }
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(mBinding.magazine)
    }

    private fun setupTreading() {
        treadingAdapter = FeatureImageAdapter(appExecutors, object : FeatureImageAdapter.ClickCallback{
            override fun onClick(data: Content, position: Int) {
                view?.findNavController()?.navigate(BrowseFragmentDirections.actionBrowseToDetail(data.title, data))
            }
        })

        with(mBinding.trading) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(LinePagerIndicatorDecoration())
            adapter = treadingAdapter
        }
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(mBinding.trading)
    }

    private fun setupObserver() {
        viewModel.getTrendingNews().observe(this, Observer { networkResponse ->
            when (networkResponse?.status) {
                Status.SUCCESS -> {
                    if (networkResponse.data?.size.orZero() > 10) {
                        val min = min(20, networkResponse.data?.size.orZero())
                        val list = networkResponse.data?.subList(10, min).orEmpty().toMutableList()
                        treadingAdapter.submitList(list)
                    }
                }
                else -> {
                    Timber.i("There is some problem to load content")}
            }
        })

        viewModel.getMagazines().observe(this, Observer {networkResponse ->
            when (networkResponse?.status) {
                Status.SUCCESS -> {
                    if (networkResponse.data?.size.orZero() > 20) {
                        val min = min(30, networkResponse.data?.size.orZero())
                        val list = networkResponse.data?.subList(20, min).orEmpty().toMutableList()
                        list.shuffle()
                        magazineAdapter.submitList(list)
                    }
                }
                else -> {
                    Timber.i("There is some problem to load content")}
            }
        })

        viewModel.getGenres().observe(this, Observer {
            tagAdapter.submitList(it)
        })
    }
}