package com.example.json_parser.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.json_parser.databinding.ActivityGsonTestBinding

class GsonTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGsonTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGsonTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gsonTest()
    }

    private fun gsonTest() {

    }
}