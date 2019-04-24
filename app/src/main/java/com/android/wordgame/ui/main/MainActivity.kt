package com.android.wordgame.ui.main

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.android.wordgame.R
import com.android.wordgame.ui.game.GameFragment
import com.android.wordgame.ui.intro.IntroFragment
import com.android.wordgame.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(),OnMainActivityCallback{


    override fun navigateToGamePage() {
        if(isFinishing)return
        ActivityUtils.replaceFragmentInsideActivity(
            supportFragmentManager,
            base_container.id,
            GameFragment.newInstance(),
            GameFragment.FRAGMENT_NAME
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                base_container.id,
                IntroFragment.newInstance(),
                IntroFragment.FRAGMENT_NAME
            )
        }

    }
}
