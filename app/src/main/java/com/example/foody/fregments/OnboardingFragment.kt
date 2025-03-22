package com.example.foody.fregments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.foody.R
import androidx.navigation.findNavController
import com.example.foody.viewModal.FoodyViewModal


class OnboardingFragment : Fragment() {


    private lateinit var viewPager: ViewPager2
    private lateinit var nextButton: Button
    private lateinit var skipButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val viewModal: FoodyViewModal  by activityViewModels()
        val view =  inflater.inflate(R.layout.fragment_onboarding, container, false)

            viewPager = view.findViewById(R.id.viewPager)
            nextButton = view.findViewById(R.id.btnNext)
            skipButton = view.findViewById(R.id.btnSkip)

        val fragments = listOf(
            page1(),
            page2(viewPager),
            login()
        )

        val adapter = OnboardingPagerAdapter(this, fragments)
        viewPager.adapter = adapter



        nextButton.setOnClickListener {
            if (viewPager.currentItem < fragments.size - 1) {
                viewPager.currentItem += 1
            } else {
                finishOnboarding(view)
            }
        }

        skipButton.setOnClickListener {
            finishOnboarding(view)
            view.findNavController().navigate(R.id.btnSkip_to_mainFrag)

        }

        return view
    }


    private fun finishOnboarding(view: View) {
        val sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("OnboardingDone", true)
            apply()
        }

    }

}

class OnboardingPagerAdapter(fragment: Fragment, private val fragments: List<Fragment>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}