package com.axion.news.views.fragments.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.axion.news.R

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

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(FeatureViewModel::class.java) }
    var imageAdapter by autoCleared<FeatureImageAdapter>()
    @Inject lateinit var appExecutors: AppExecutors

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupUI()
    }

    private fun setupUI() {
        setupListView()
        setupObserver()
    }

    private fun setupListView() {
        imageAdapter = FeatureImageAdapter(appExecutors, object : FeatureImageAdapter.ClickCallback{
            override fun onClick(data: Content, position: Int) {
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeToDetail(data.title,data))
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.activity_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.profile -> view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeToAuthor())
        }

        return super.onOptionsItemSelected(item)
    }
}