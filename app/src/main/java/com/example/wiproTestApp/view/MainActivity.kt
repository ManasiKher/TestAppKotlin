package com.example.wiproTestApp

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        callServiceRefresh()
        canadaDetailsListModel.mCanadaDetailsResposne.observe(
            this,
            Observer(function = fun(responseBundle: ResponseBundle?) {
                responseBundle?.let {

                    if (responseBundle.responseStatus.equals(Constants.SUCCESS)) {
                        val newFragment = CanadaDetailFragment.newInstance(responseBundle.canadaDetails)
                        val transaction = supportFragmentManager!!.beginTransaction()
                        transaction.replace(R.id.frag_container, newFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                        newFragment.setDetailsContract(this)
                    } else {
                        Toast.makeText(this, Constants.TRY_AGAIN, Toast.LENGTH_SHORT).show()
                    }
                }
            })

        )
    }

    override fun setTitle(title: String) {
        val textViewTitle = findViewById<TextView>(R.id.title_about_canada);
        textViewTitle.setText(title)
    }

    /*
   * To check the network connectivity
   * */
    override fun callServiceRefresh() {
        if (isNetworkAvailable()) {
            canadaDetailsListModel.getDetails()
        } else {
            Toast.makeText(this, Constants.NO_NETWORK, Toast.LENGTH_SHORT).show()
        }
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