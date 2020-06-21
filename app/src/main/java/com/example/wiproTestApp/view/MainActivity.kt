package com.example.wiproTestApp

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.ConnectivityManager
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.wiproTestApp.model.Constants
import com.example.wiproTestApp.model.ResponseBundle
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), CanadaDetailFragment.DetailsContract {

    private val canadaDetailsListModel: CanadaDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        callServiceRefresh()
        canadaDetailsListModel.mCanadaDetailsResposne.observe(
            this,
            Observer(function = fun(responseBundle: ResponseBundle?) {
                responseBundle?.let {
                    setFragment(responseBundle,progressBar)
                }
            })

        )
    }

    fun setFragment(responseBundle : ResponseBundle,progressBar:ProgressBar)
    {
        if (responseBundle.responseStatus.equals(Constants.SUCCESS)) {
            val newFragment = CanadaDetailFragment.newInstance(responseBundle.canadaDetails)
            val transaction = supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.frag_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            newFragment.setDetailsContract(this)
            progressBar.visibility = View.GONE
        } else {
            Toast.makeText(this, Constants.TRY_AGAIN, Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
        }
    }

    /*
    * to set title from service response
    * */
    override fun setTitle(title: String) {
        val textViewTitle = findViewById<TextView>(R.id.title_about_canada);
        textViewTitle.setText(title)
    }

    /*
   * call to service
   * */
    override fun callServiceRefresh() {
        if (isNetworkAvailable()) {
            canadaDetailsListModel.getDetails()
        } else {
            Toast.makeText(this, Constants.NO_NETWORK, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    /*
    * To check the network connectivity
    * */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}