package com.example.wiproTestApp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wiproTestApp.CanadaDetails
import com.example.wiproTestApp.R


class CanadaDetailAdapter(private val canadaDetails: CanadaDetails) :
    RecyclerView.Adapter<CanadaDetailAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_canada_details, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return canadaDetails.rows.size;
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name?.text = canadaDetails.rows[position].title
        viewHolder.count?.text = canadaDetails.rows[position].description
        val imageUrl = canadaDetails.rows[position].imageHref
        Glide.with(viewHolder.imageView.context).load(imageUrl).into(viewHolder.imageView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvTitle)
        val count = itemView.findViewById<TextView>(R.id.tvDescription)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView);

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, 0)
            }
        }
    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}