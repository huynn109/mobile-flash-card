package com.huynn109.integratenative

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class IntegrateFragment : Fragment() {

    lateinit var rootView: View
    companion object {
        @JvmStatic
        fun newInstance() = IntegrateFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = LayoutInflater.from(this.requireContext()).inflate(R.layout.fragment_integrate, null, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    // Issues when attach activity to flutter engine
    private fun setupRecyclerView() {
        rootView.findViewById<ListView>(R.id.recyclerView).apply { 
//            adapter = MovieAdapter(context = this.context)
        }
    }

}