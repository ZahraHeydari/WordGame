package com.android.wordgame.ui.intro

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.wordgame.R
import com.android.wordgame.ui.main.OnMainActivityCallback
import com.android.wordgame.databinding.FragmentIntroBinding
import dagger.android.support.DaggerFragment
import java.lang.ClassCastException

class IntroFragment : DaggerFragment() {

    private lateinit var fragmentIntroBinding: FragmentIntroBinding
    private var mCallback: OnMainActivityCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMainActivityCallback) {
            mCallback = context
        } else throw ClassCastException("${context.toString()} must implement OnMainActivityCallback!")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentIntroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        fragmentIntroBinding.callback = mCallback

        return fragmentIntroBinding.root
    }


    override fun onDetach() {
        super.onDetach()
        mCallback = null
    }

    companion object {

        val FRAGMENT_NAME = IntroFragment::class.java.name


        fun newInstance() =
            IntroFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}