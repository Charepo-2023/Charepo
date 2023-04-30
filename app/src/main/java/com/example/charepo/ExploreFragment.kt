package com.example.charepo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

        val searchInput = view.findViewById<EditText>(R.id.searchInput)

        searchInput.addTextChangedListener (object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Fetcher.searchExploreList(s.toString(),
                        publicRV.adapter as ExploreFragmentAdapter
                    )
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        return view
}}