package com.example.json_parser.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.json_parser.databinding.ActivityFragileTestBinding

class FragileTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragileTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragileTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}