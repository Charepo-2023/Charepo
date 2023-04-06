package com.example.charepo

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        val homeFragment: Fragment = HomeFragment(this)

        //Make the navigation bar start on the home tab

        val homeView = LayoutInflater.from(this).inflate(R.layout.home_page,null)
        val navigationBar = homeView.findViewById<BottomNavigationView>(R.id.navigation_bar)
        val addFolderBtn = homeView.findViewById<ImageButton>(R.id.add_folder_btn)

        navigationBar.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when(item.itemId){
                R.id.home_tab -> fragment = homeFragment
            }
            fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
            true
        }
        navigationBar.selectedItemId = R.id.home_tab


        addFolderBtn.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
        }

    }



}

