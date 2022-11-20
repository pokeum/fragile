package com.example.json_parser.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.json_parser.R
import com.example.json_parser.databinding.ActivityMainBinding
import com.example.json_parser.util.startActivity
import com.google.android.material.navigation.NavigationView

/* Annotation Processor Test */
//import com.example.annotations.annotation.java.Version
//@Version(major = -1)
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        initNavigationView()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initNavigationView() {
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar, 0, 0)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_gson_test -> { startActivity(GsonTestActivity::class.java) }
        }
        return true
    }
}