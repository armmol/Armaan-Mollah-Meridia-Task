package com.example.armaan_mollah_task
import retrofit2.Call
import com.example.armaan_mollah_task.Api_Data.MarkerData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import retrofit2.http.GET

/**
 * Data Contract For Application
 * Includes Presenter, View, Model, and API Interface Contracts and functions for respective interfaces
 */
interface Contract {
    interface MapView{
       fun setMap(polygons: List<PolygonOptions>, Markers : List<MarkerOptions>)
    }
    interface MapPresenter{
        fun loadData()
        fun uiAutoUpdate(Polygons : List<PolygonOptions>, Markers : List<MarkerOptions>)
    }
    interface HomeView{
        fun navigateToMapActivity()
        fun showProgressbar()
        fun hideProgressbar()
    }
    interface HomePresenter{
        fun onButtonClick()
    }
    interface MapModel{
        fun getListFeatures(presenter: MapPresenter)

    }
    interface ApiInterface{
        @GET( ".json")
        fun getData() : Call<MarkerData>
    }
}