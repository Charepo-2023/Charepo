package com.example.charepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        val usernameHolder = view.findViewById<TextView>(R.id.username_view)
        usernameHolder.text = LoginTracker.getCurrentUserName()
        return view
    }


}