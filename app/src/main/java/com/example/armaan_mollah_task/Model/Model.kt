package com.example.armaan_mollah_task.Model

import android.graphics.Color
import android.util.Log
import com.example.armaan_mollah_task.Api_Data.MarkerData
import com.example.armaan_mollah_task.Contract
import com.google.android.gms.maps.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model : Contract.MapModel {

    private var ApiClient : Contract.ApiInterface? = null

    init {
        ApiClient = com.example.armaan_mollah_task.ApiClient.client.create(Contract.ApiInterface::class.java)
    }

    /**
     * Function to consume a RESTful web service in this case an API
     * using a Retrofit APIclient from the APIClient class and
     * extracting data and converting them to markers and polygons for respective Data bodies(features)
     */
    override fun getListFeatures(presenter: Contract.MapPresenter) {
        val data = ApiClient?.getData()
        var resp : MarkerData? = null
        data?.enqueue(object : Callback<MarkerData?> {
            override fun onResponse(call: Call<MarkerData?>, response: Response<MarkerData?>) {
                resp = response.body()!!
                Log.d("success",resp.toString())
                val polygonlist = getPolygons(resp!!)
                val markerlist = getMarkers(resp!!)
                presenter.uiAutoUpdate(polygonlist,markerlist)
            }
            override fun onFailure(call: Call<MarkerData?>, t: Throwable) {
                Log.d("failure",t.toString())
            }
        })
    }

    /**
     * function to convert data acquired from API to polygons
     */
    private fun getPolygons(
        Data: MarkerData?
    ): List<PolygonOptions> {
        val listofpolygons = mutableListOf<PolygonOptions>()
        var polygon = PolygonOptions()
        if (Data != null) {
            for (feature in Data.features) {
                for (point in feature.points) {
                    polygon.add(LatLng(point.latitude, point.longitude))
                }
                polygon.strokeColor(Color.MAGENTA)
                polygon.fillColor(Color.CYAN)
                listofpolygons.add(polygon)
                polygon = PolygonOptions()
            }
        }
        val p = listofpolygons
        return p
    }

    /**
     * function to convert data acquired from API to markers
     */
    private fun getMarkers(
    Data: MarkerData?
    ): List<MarkerOptions> {
        val listofmarkers = mutableListOf<MarkerOptions>()
        if (Data != null) {
            for (feature in Data.features) {
                for (point in feature.points) {
                    val accuracy = point.accuracy
                    val options = MarkerOptions().position(LatLng(point.latitude, point.longitude))
                        .title("${feature.id}")
                    when {
                        accuracy in 0.0..1.5 -> options.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )
                        accuracy in 1.5..2.0 -> options.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_YELLOW
                            )
                        )
                        accuracy > 2.0 -> options.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_ORANGE
                            )
                        )
                    }
                    listofmarkers.add(options)
                }
            }
        }
        return  listofmarkers
    }

}