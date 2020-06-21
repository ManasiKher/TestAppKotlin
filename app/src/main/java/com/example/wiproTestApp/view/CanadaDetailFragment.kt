package com.example.wiproTestApp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.wiproTestApp.view.CanadaDetailAdapter


class CanadaDetailFragment : Fragment() {

    lateinit var canadaDetails: CanadaDetails

    companion object {
        const val KEY_DETAILS = "KEY_DETAILS"

        fun newInstance(canadaDetails: CanadaDetails): CanadaDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_DETAILS, canadaDetails)
            val fragment = CanadaDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { canadaDetails = it.getSerializable(KEY_DETAILS) as CanadaDetails }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view?.findViewById<TextView>(R.id.title_about_canada);
        title.setText(canadaDetails.title)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)
        var canadaDetailAdapter: CanadaDetailAdapter = CanadaDetailAdapter(canadaDetails)
        recyclerView.adapter = canadaDetailAdapter

    }
}