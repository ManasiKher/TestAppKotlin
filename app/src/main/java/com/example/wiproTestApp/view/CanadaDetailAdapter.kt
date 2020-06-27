package com.example.wiproTestApp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wiproTestApp.CanadaDetails
import com.example.wiproTestApp.R
import kotlinx.android.synthetic.main.item_canada_details.view.*

/*
* canada about details adapter to show list details
* */
class CanadaDetailAdapter(private val canadaDetails: CanadaDetails, private val context: Context) :
    RecyclerView.Adapter<CanadaDetailAdapter.ViewHolder>() {
    private val mContext = context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_canada_details, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return canadaDetails.rows.size;
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = canadaDetails.rows[position].title
        viewHolder.description.text = canadaDetails.rows[position].description
        val imageUrl = canadaDetails.rows[position].imageHref
                Glide.with(mContext).load(imageUrl).into(viewHolder.imageView).
                onLoadFailed(mContext.getDrawable(R.drawable.ic_placeholder))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.tvTitle
        val description : TextView = itemView.tvDescription
        val imageView: ImageView = itemView.imageView;
    }
}