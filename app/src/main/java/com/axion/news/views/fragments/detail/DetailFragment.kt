package com.axion.news.views.fragments.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.axion.news.R
import com.axion.news.databinding.FragmentDetailsBinding
import com.axion.news.di.Injectable
import com.axion.news.network.api.Status
import com.axion.news.util.fragment.autoCleared
import com.axion.news.viewmodel.detail.DetailViewModel
import com.axion.news.views.fragments.BaseFragment
import timber.log.Timber
import javax.inject.Inject

//If any item click its will open details.
class DetailFragment: BaseFragment(), Injectable {
    var mBinding by autoCleared<FragmentDetailsBinding>()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    val content by lazy { arguments?.let {  DetailFragmentArgs.fromBundle(it).content} }
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRestOfUI()
    }

    //Actionbar title
    private fun setupRestOfUI() {
        mBinding.content = content

        Timber.i("Saurabh loading content .. $content")
        viewModel.getAllAuthors().observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    Timber.i("All authors are ${it.data}")
                    mBinding.author = it.data?.first()
                }
                Status.LOADING -> {}
                Status.ERROR -> {Timber.i("Can't load authors..")}
            }
        })

        setupContent()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
       mBinding.authorDetail.setOnClickListener {
           val extras = FragmentNavigatorExtras(
               mBinding.userImage to "imageView"
           )
           findNavController().navigate(R.id.action_detail_to_author, null, null, extras)
       }
    }

    private fun setupContent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.contentView.text = Html.fromHtml(content?.html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            mBinding.contentView.text = Html.fromHtml(content?.html)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}