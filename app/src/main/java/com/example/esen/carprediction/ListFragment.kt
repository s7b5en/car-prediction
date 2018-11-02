package com.example.esen.carprediction

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf

typealias Callback = () -> Unit

class ListFragment : Fragment() {
    private var columnCount = 1
    private var values = ArrayList<String>()
    private lateinit var field: String

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            values = it.getStringArrayList(VALUES_KEY)!!
            field = it.getString(FIELD_KEY).orEmpty()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_brands_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ListRecyclerViewAdapter(field, values, listener)
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


     interface OnListFragmentInteractionListener {
        fun onItemListInteraction(field:String, value: String)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val VALUES_KEY = "values"
        const val FIELD_KEY = "field"

        @JvmStatic
        fun newInstance(field: String, values: ArrayList<String>?): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putStringArrayList(VALUES_KEY, values)
            args.putStringArrayList(FIELD_KEY, values)
            fragment.arguments = args
            return fragment
        }
    }
}
