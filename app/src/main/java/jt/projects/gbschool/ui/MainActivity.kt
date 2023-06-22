package jt.projects.gbschool.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import jt.projects.gbschool.R
import jt.projects.gbschool.databinding.ActivityMainBinding
import jt.projects.gbschool.ui.classes.ClassesFragment
import jt.projects.gbschool.ui.home.HomeFragment
import jt.projects.gbschool.utils.USER_NAME

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_classes, R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label?.equals("Classes") == true) {
                binding.layoutToolbar.toolbarTitle.text = "Classes"
            }
            if (destination.label?.equals("Home") == true) {
                binding.layoutToolbar.toolbarTitle.text = "Hi, ${USER_NAME}"
            }
        }

    }
}