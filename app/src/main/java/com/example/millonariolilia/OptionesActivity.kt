package com.example.millonariolilia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.millonariolilia.databinding.ActivityOptionesBinding

class OptionesActivity : AppCompatActivity() {
    lateinit var binding:ActivityOptionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        botones()
    }
    fun botones(){
        binding.button.setOnClickListener {
            startActivity(Intent(this@OptionesActivity,ElegirCatActivity::class.java))
        }
        binding.button2.setOnClickListener {
            startActivity(Intent(this@OptionesActivity,AgregarActivity::class.java))
        }
    }
}