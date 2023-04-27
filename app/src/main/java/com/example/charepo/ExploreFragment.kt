package com.example.charepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExploreFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.explore_page,container,false)

        val publicRV = view.findViewById<RecyclerView>(R.id.publicCharacterRV)
        publicRV.adapter = ExploreFragmentAdapter(view.context,Fetcher.itemList)
        publicRV.layoutManager = LinearLayoutManager(view.context)

        return view
}}