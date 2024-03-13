package com.mithilakshar.mithilapanchang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 * Use the [mayfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mayfragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var calendarRecycler: RecyclerView? = null
    private var dataqueue: ArrayList<calendardatamodel?>? = null
    private var db: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mayfragment, container, false)
        dataqueue = ArrayList()
        val adapter = calendaradapter(dataqueue!!)
        db = FirebaseFirestore.getInstance()
        db!!.collection("May").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(calendardatamodel::class.java)
                dataqueue!!.add(obj)
            }
            adapter.notifyDataSetChanged()
        }
        calendarRecycler = view.findViewById(R.id.calendarRecycler)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 7, LinearLayoutManager.HORIZONTAL, false)

        var screenWidth = resources.displayMetrics.widthPixels
        screenWidth = screenWidth - 293
        val itemWidth = screenWidth / 5 // Number of columns is 5
        adapter.setItemWidth(itemWidth)
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mayfragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): mayfragment {
            val fragment = mayfragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}