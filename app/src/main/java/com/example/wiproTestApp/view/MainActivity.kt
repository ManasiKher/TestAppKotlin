package com.example.wiproTestApp

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val canadaDetailsListModel: CanadaDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        canadaDetailsListModel.getDetails()
        canadaDetailsListModel.candaDetailsResposne.observe(
            this,
            Observer(function = fun(canadaDetails: CanadaDetails?) {
                canadaDetails?.let {
                    val newFragment = CanadaDetailFragment.newInstance(canadaDetails)
                    val transaction = supportFragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frag_container, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            })
        )
    }
}
