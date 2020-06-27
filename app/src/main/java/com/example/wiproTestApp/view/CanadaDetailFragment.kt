package com.example.wiproTestApp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.wiproTestApp.view.CanadaDetailAdapter


/*
* contains the list of canada details items
* */

class CanadaDetailFragment : Fragment() {

    lateinit var mCanadaDetails: CanadaDetails
    lateinit var mDetailsContract: DetailsContract

    companion object {
        const val KEY_DETAILS = "KEY_DETAILS"

        fun newInstance(canadaDetails: CanadaDetails?): CanadaDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_DETAILS, canadaDetails)
            val fragment = CanadaDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun setDetailsContract(detailsContract: DetailsContract) {
        mDetailsContract = detailsContract
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { mCanadaDetails = it.getSerializable(KEY_DETAILS) as CanadaDetails }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDetailsContract.setTitle(mCanadaDetails.title)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager =
            LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        checkIfEmptyTextInResponse()
        val canadaDetailAdapter =
            activity?.applicationContext?.let { CanadaDetailAdapter(mCanadaDetails, it) }
        recyclerView.adapter = canadaDetailAdapter

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh_items)
        swipeRefreshLayout.setOnRefreshListener {
            mDetailsContract.callServiceRefresh()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    //It will remove the details which has no description
    private fun checkIfEmptyTextInResponse() {
        val aboutCanadaDetails: List<AboutCanadaDetails> = mCanadaDetails.rows
        val aboutCanadaDetailsFormatted: ArrayList<AboutCanadaDetails> = ArrayList()
        for (aboutCanada: AboutCanadaDetails in aboutCanadaDetails) {
            if (!TextUtils.isEmpty(aboutCanada.description)) {
                aboutCanadaDetailsFormatted.add(aboutCanada)
            }
        }
        mCanadaDetails.rows = aboutCanadaDetailsFormatted
    }

    /*
    * Fragment activity contract for service response handling
    * */
    interface DetailsContract {
        fun setTitle(title: String)
        fun callServiceRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }
}