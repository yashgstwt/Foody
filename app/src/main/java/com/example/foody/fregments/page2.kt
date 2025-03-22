package com.example.foody.fregments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.foody.R



class page2(private val viewPager: ViewPager2) : Fragment() {

        private lateinit var Btn_next: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_page2, container, false)

        Btn_next = view.findViewById(R.id.btnNext_to_login)

        Btn_next.setOnClickListener { viewPager.currentItem++ }

        return view
    }

}