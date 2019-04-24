package com.android.wordgame.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object ActivityUtils {

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun addFragmentToActivity(
        fragmentSupportManager: FragmentManager,
        frameId: Int,
        fragment: Fragment,
        fragmentTag: String
    ) {
        fragmentSupportManager.beginTransaction()
            .add(frameId, fragment, fragmentTag)
            .commit()
    }

    /**
     * The `fragment` is replaced in the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun replaceFragmentInsideActivity(
        supportFragmentManager: FragmentManager,
        frameId: Int,
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager.beginTransaction()
            .replace(frameId, fragment, fragmentTag)
            .addToBackStack(fragmentTag)
            .commit()

    }


}