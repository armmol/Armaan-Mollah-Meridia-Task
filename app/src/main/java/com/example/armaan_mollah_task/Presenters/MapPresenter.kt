package com.example.armaan_mollah_task.Presenters

import com.example.armaan_mollah_task.Contract
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions

/**
 * Presenter Class for Map Activity View
 */
class MapPresenter(mainview : Contract.MapView,
                   model : Contract.MapModel)
    : Contract.MapPresenter {

    private var view : Contract.MapView = mainview
    private var modeln : Contract.MapModel = model

    /**
     * LoadData function to access the function in model class to get List of Features
     */
    override fun loadData() {
        modeln.getListFeatures(this)
    }

    /**
     * uiAutoUpdate to call the view function to update map with markers and polygons
     */
    override fun uiAutoUpdate(Polygons: List<PolygonOptions>, Markers: List<MarkerOptions>) {
        view.setMap(Polygons,Markers)
    }
}