package com.android.wordgame.ui.main


/**
 * To make an interaction between [MainActivity] & its child
 * */
interface OnMainActivityCallback {

    /**
     * To go to [GameFragment]
     * */
    fun navigateToGamePage()
}