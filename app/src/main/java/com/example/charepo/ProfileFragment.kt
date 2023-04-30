package com.example.charepo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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

        val icon = view.findViewById<ImageView>(R.id.profile_image_view)
        icon.setImageResource(R.drawable.profile)

        val logoutButton = view.findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {

            LoginTracker.setCurrentUserName("")
            LoginTracker.setUserLoggedInVal(false)
            val intent = Intent(view.context, MainActivity::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(intent)

        }


        return view
    }


}