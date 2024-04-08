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
 * Use the [Decemberfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Decemberfragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var calendarRecycler: RecyclerView? = null
    private var dataqueue: ArrayList<calendardatamodel?>? = null
    private var db: FirebaseFirestore? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_decemberfragment, container, false)
        dataqueue = ArrayList()

        db = FirebaseFirestore.getInstance()
        db!!.collection("December").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(calendardatamodel::class.java)
                dataqueue!!.add(obj)
            }

        }
        calendarRecycler = view.findViewById(R.id.calendarRecycler)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 7, LinearLayoutManager.HORIZONTAL, false)

        var screenWidth = resources.displayMetrics.widthPixels
        screenWidth = screenWidth - 293
        val itemWidth = screenWidth / 5 // Number of columns is 5

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
         * @return A new instance of fragment Decemberfragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): Decemberfragment {
            val fragment = Decemberfragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}