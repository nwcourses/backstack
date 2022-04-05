package com.example.backstack

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class StyleFragment(val callback: (Boolean) -> Unit): Fragment(R.layout.map_choose) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnRegular = view.findViewById<Button>(R.id.btnRegular)
        val btnOpenTopoMap = view.findViewById<Button>(R.id.btnOpenTopoMap)

        btnRegular.setOnClickListener {
            chooseMap(false)
        }

        btnOpenTopoMap.setOnClickListener {
            chooseMap(true)
        }
    }

    fun chooseMap(openTopo: Boolean) {
        callback(openTopo)
    }
}