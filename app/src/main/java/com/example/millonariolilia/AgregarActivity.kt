package com.example.millonariolilia

import android.content.Intent
import com.example.millonariolilia.Entitys.Model
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.millonariolilia.databinding.ActivityAgregarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AgregarActivity : AppCompatActivity() {
    lateinit var binding: ActivityAgregarBinding
    lateinit var DB: FirebaseDatabase
    lateinit var refEspañol: DatabaseReference
    lateinit var refMatematicas: DatabaseReference
    lateinit var refHistoria: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        DB= FirebaseDatabase.getInstance()
        refMatematicas = DB.getReference("Matematicas")
        refEspañol = DB.getReference("Español")
        refHistoria = DB.getReference("Historia")
        setContentView(binding.root)
        spinner()
        guardar()
    }
    fun spinner(){
        val lista= listOf<String>("Español","Historia","Matematicas")
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista)
        binding.spinner.adapter = adapter
    }
    fun guardar(){
        binding.save.setOnClickListener {
            if(binding.spinner.selectedItem=="Español")
            {
                refEspañol.child(refEspañol.push().key.
                toString()).setValue(Model(binding.editTextText.text.toString(),binding.editTextText2.text.toString()
                    ,binding.editTextText3.text.toString(),binding.editTextText4.text.toString(),
                    binding.editTextText5.text.toString(),binding.editTextText6.text.toString()))
            }
            else if(binding.spinner.selectedItem=="Historia")
            {
                refHistoria.child(refHistoria.push().key.
                toString()).setValue(Model(binding.editTextText.text.toString(),binding.editTextText2.text.toString()
                    ,binding.editTextText3.text.toString(),binding.editTextText4.text.toString(),
                    binding.editTextText5.text.toString(),binding.editTextText6.text.toString()))
            }
            else if(binding.spinner.selectedItem=="Matematicas")
            {
                refMatematicas.child(refMatematicas.push().key.
                toString()).setValue(Model(binding.editTextText.text.toString(),binding.editTextText2.text.toString()
                    ,binding.editTextText3.text.toString(),binding.editTextText4.text.toString(),
                    binding.editTextText5.text.toString(),binding.editTextText6.text.toString()))
            }
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@AgregarActivity,OptionesActivity::class.java))
        }
    }
}