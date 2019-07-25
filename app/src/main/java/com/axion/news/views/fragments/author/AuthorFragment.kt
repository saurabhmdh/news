package com.axion.news.views.fragments.author

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.axion.news.databinding.FragmentAuthorBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.author.AuthorViewModel

import javax.inject.Inject

class AuthorFragment: Fragment(), Injectable {
    var mBinding by autoCleared<FragmentAuthorBinding>()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(AuthorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentAuthorBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewModel.getAllAuthors().observe(this, Observer {result->
            when (result?.status) {
                Status.SUCCESS -> {mBinding.author = result.data?.first()}
            }

        })
    }
}