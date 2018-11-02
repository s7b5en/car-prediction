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
import com.example.esen.carprediction.data.remote.models.Brands

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [BrandsFragment.OnListFragmentInteractionListener] interface.
 */
class BrandsFragment : Fragment() {
    private var columnCount = 1
    private var brands = ArrayList<String>()

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            brands = it.getStringArrayList(BRANDS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_brands_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = BrandsRecyclerViewAdapter(brands, listener)
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onBrandListInteraction(brand: String)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val BRANDS = "brands"

        @JvmStatic
        fun newInstance(brands: ArrayList<String>?): BrandsFragment {
            val fragment = BrandsFragment()
            val args = Bundle()
            args.putStringArrayList(BRANDS, brands)
            fragment.arguments = args
            return fragment
        }
    }
}
