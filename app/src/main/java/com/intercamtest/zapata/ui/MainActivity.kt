package com.intercamtest.zapata.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intercamtest.zapata.databinding.ActivityMainBinding
/**
 * Created by Alberto Zapata on ago, 2021
 */
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}