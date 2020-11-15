package com.codecool.applicationa

class MainActivityPresenter {

    private var view : MainActivityPresenterView? = null

    fun onAttachView(view : MainActivityPresenterView){
        this.view = view
    }

    fun onDetachView ( view : MainActivityPresenterView){
        this.view = null
    }


}