package com.example.armaan_mollah_task.Presenters

import com.example.armaan_mollah_task.Contract

/**
 * Presenter Class for Home Screen
 */
class HomePresenter(
    private var mainview : Contract.HomeView,
    private val model : Contract.MapModel) : Contract.HomePresenter {

    private var view : Contract.HomeView = mainview
    private var modeln : Contract.MapModel = model

    /**
     * onButtonClick function to navigate to Map Activity from Home Activity
     */
    override fun onButtonClick() {
        view.showProgressbar()
        view.navigateToMapActivity()
        view.hideProgressbar()
    }

}