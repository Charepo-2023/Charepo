package com.example.charepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager : FragmentManager = supportFragmentManager
        val homeFragment : Fragment = HomeFragment()


        val navagationBar = findViewById<BottomNavigationView>(R.id.btmNavBar)
        navagationBar.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when(item.itemId){
                R.id.home_tab -> fragment = homeFragment
            }
            fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
            true
        }
        navagationBar.selectedItemId = R.id.home_tab

    }



}
