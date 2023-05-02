package com.example.charepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(LoginTracker.getUserLoggedInVal()){
            setContentView(R.layout.activity_main)
            val fragmentManager : FragmentManager = supportFragmentManager
            val homeFragment : Fragment = HomeFragment()
            val exploreFragment : Fragment = ExploreFragment()
            val profileFragment : Fragment = ProfileFragment()

            if(Fetcher.itemList.isEmpty()){
                Fetcher.itemList = Fetcher.getItems()
            }


            val navagationBar = findViewById<BottomNavigationView>(R.id.btmNavBar)
            navagationBar.setOnItemSelectedListener { item ->
                lateinit var fragment: Fragment
                when(item.itemId){
                    R.id.home_tab -> fragment = homeFragment
                    R.id.explore_tab -> fragment = exploreFragment
                    R.id.profile_tab -> fragment = profileFragment
                }
                fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
                true
            }
            navagationBar.selectedItemId = R.id.home_tab

        }else{
            setContentView(R.layout.inital_menu_page)
            val loginBtn = findViewById<Button>(R.id.login_button)
            val registerBtn = findViewById<Button>(R.id.register_button)
            loginBtn.setOnClickListener {
                val intent = Intent(this,LoginPage::class.java)
                startActivity(intent)
            }

            registerBtn.setOnClickListener {
                val intent = Intent(this, RegistrationPage::class.java)
                startActivity(intent)
            }
        }



    }



}
