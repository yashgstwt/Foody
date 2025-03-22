package com.example.foody

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.viewModal.FoodyViewModal


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding :ActivityMainBinding
    private val viewModal : FoodyViewModal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_Container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.lifecycleOwner = this
        binding.viewModal = viewModal

        viewModal.showBottomNavBar.observe(this) { isVisible ->
            binding.bottomAppBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        }





        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isOnboardingDone = sharedPreferences.getBoolean("OnboardingDone", false)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        setupNav()

        if (isOnboardingDone) {
            navController.navigate(R.id.mainFragment)
        }else{
            navController.navigate(R.id.onboardingFragment)
        }
    }


    private fun setupNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> viewModal.updateShowBottomNavBar(true)
                else -> viewModal.updateShowBottomNavBar(false)
            }

            when(destination.id){
                R.id.onboardingFragment -> viewModal.updateShowTopAppBar(false)
                else -> viewModal.updateShowTopAppBar(true)
            }
        }
    }
}