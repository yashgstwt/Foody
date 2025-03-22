package com.example.foody.fregments

import android.os.Bundle
import android.os.StatFs
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.foody.R
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.databinding.FragmentMainBinding


class MainFragment : Fragment() {
private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container , false)

    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner1,ScaleTypes.FIT),
            SlideModel(R.drawable.banner1, ScaleTypes.FIT)
        )
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setItemClickListener(object :ItemClickListener{
            override fun doubleClick(position: Int) {
            }

            override fun onItemSelected(position: Int) {

                val itemPosition = imageList[position]
                val msg = "$position item is clicked"
                Toast.makeText(requireContext(), msg , Toast.LENGTH_SHORT).show()

            }
        })
    }
}