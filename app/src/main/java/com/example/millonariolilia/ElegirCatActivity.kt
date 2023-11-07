package com.example.millonariolilia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.millonariolilia.databinding.ActivityElegirCatBinding

class ElegirCatActivity : AppCompatActivity() {
    companion object{
        var mate = ""
        var histo = ""
        var espa = ""
    }
    lateinit var binding: ActivityElegirCatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElegirCatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categorias()
    }
    fun categorias(){
        binding.jugar.setOnClickListener {
            if(binding.mate.isChecked)
            {
                mate="si"
            }
            if(binding.espa.isChecked)
            {
                espa="si"
            }
            if(binding.histo.isChecked)
            {
                histo="si"
            }
            startActivity(Intent(this@ElegirCatActivity,PlayActivity::class.java))
        }
    }
}