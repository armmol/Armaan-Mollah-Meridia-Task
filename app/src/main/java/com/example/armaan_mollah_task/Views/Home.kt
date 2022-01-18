package com.example.armaan_mollah_task.Views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.armaan_mollah_task.Contract
import com.example.armaan_mollah_task.Model.Model
import com.example.armaan_mollah_task.Presenters.HomePresenter
import com.example.armaan_mollah_task.R

/**
 * Home activity which is View class for the Home screen
 */
class Home : AppCompatActivity(), Contract.HomeView {

    lateinit var btn : Button
    lateinit var prgbr : ProgressBar
    var presenter : HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn = findViewById(R.id.btn_openmap)
        prgbr = findViewById(R.id.progressBar_home)
        presenter = HomePresenter(this, Model())
        this.btn.setOnClickListener{
            presenter?.onButtonClick()
        }
    }

    /**
     * Function to go to google maps activity
     */
    override fun navigateToMapActivity() {

        Toast.makeText(this, "Task Completed", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MapsActivity::class.java))
    }

    /**
     * Function to show progessbar
     */
    override fun showProgressbar() {
        this.prgbr.visibility = View.VISIBLE
    }

    /**
     * Function to hide Progressbar
     */
    override fun hideProgressbar() {
        this.prgbr.visibility = View.INVISIBLE
    }
}