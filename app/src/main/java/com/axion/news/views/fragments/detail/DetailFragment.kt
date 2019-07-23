package com.axion.news.views.fragments.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import com.axion.news.R
import com.axion.news.databinding.FragmentDetailsBinding
import com.axion.news.di.Injectable
import com.axion.news.util.fragment.autoCleared
import timber.log.Timber

//If any item click its will open details.
class DetailFragment: Fragment(), Injectable {
    var mBinding by autoCleared<FragmentDetailsBinding>()

    val content by lazy { arguments?.let {  DetailFragmentArgs.fromBundle(it).content} }
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
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}