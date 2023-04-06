package com.example.charepo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class HomeFragment(private val context: Context): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page,container,false)

        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                showFiles(context)
            }

        if (!Environment.isExternalStorageManager()) {
            val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")
            val i = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri)
            startForResult.launch(i)
        } else {
            showFiles(context)
        }
        return view
    }

    fun showFiles(context: Context) {
        val intent = Intent(context, FileList::class.java)
        val path = Environment.getExternalStorageDirectory().path
        intent.putExtra("path", path)
        startActivity(intent)

    }


}