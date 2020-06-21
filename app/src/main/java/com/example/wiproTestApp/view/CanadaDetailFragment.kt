package com.example.wiproTestApp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.wiproTestApp.view.CanadaDetailAdapter


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
        arguments?.let { mCanadaDetails = it.getSerializable(KEY_DETAILS) as CanadaDetails}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDetailsContract.setTitle(mCanadaDetails.title)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        val canadaDetailAdapter: CanadaDetailAdapter = CanadaDetailAdapter(mCanadaDetails)
        recyclerView.adapter = canadaDetailAdapter

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh_items)
        swipeRefreshLayout.setOnRefreshListener {
            mDetailsContract.callServiceRefresh()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    interface DetailsContract {
        fun setTitle(title: String)
        fun callServiceRefresh()
    }
}