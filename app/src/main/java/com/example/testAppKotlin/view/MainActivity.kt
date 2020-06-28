package com.example.testAppKotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.testAppKotlin.model.Constants
import org.koin.android.viewmodel.ext.android.viewModel
import android.arch.lifecycle.ViewModelProviders
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CanadaDetailFragment.DetailsContract {

    private val mCanadaDetailsListModel: CanadaDetailsViewModel by viewModel()
    private lateinit var mProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressBar = findViewById(R.id.progress_circular)
        callServiceRefresh()
        (application as MainApplication).networkComponent.inject(this)
        mViewModel.getData()
        observeDetails()
        observeError()

    }

    private fun setFragment(canadaDetails: CanadaDetails)
    {
        mProgressBar.visibility = View.GONE
            val newFragment = CanadaDetailFragment.newInstance(canadaDetails)
            val transaction = supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.frag_container, newFragment)
            transaction.commit()
            newFragment.setDetailsContract(this)
    }

    /*
    * to set title from service response
    * */
    override fun setTitle(title: String) {
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.title = title
    }

    /*
   * call to service
   * */
    override fun callServiceRefresh() {
        if (isNetworkAvailable()) {
            mCanadaDetailsListModel.getData()
        } else {
            mProgressBar.visibility = View.GONE
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


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: CanadaDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[CanadaDetailsViewModel::class.java]
    }


    private fun observeError() {
        mViewModel.getErrors().observe(this, Observer {
            it?.let {Toast.makeText(this, it, Toast.LENGTH_SHORT).show()}
        })
    }


    private fun observeDetails() {
        mViewModel.getDetails().observe(this, Observer {
            it?.let {
                when (it) {
                    is CanadaDetails -> setFragment(it)
                    is ErrorDetails -> mViewModel.getErrors().postValue(it.msg)
                }
            }
        })
    }

    private fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE
}