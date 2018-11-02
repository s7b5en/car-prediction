package com.example.esen.carprediction

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.esen.carprediction.BrandsFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_brands.view.*

class BrandsRecyclerViewAdapter(
        private val mValues: ArrayList<String>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<BrandsRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as String
            mListener?.onBrandListInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_brands, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("The size is ${mValues.size} and first value ${mValues[0]}")
        val item = mValues[position]
        holder.mIdView.text = item

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
