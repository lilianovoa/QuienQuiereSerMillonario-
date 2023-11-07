package com.example.millonariolilia

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.millonariolilia.ElegirCatActivity.Companion.espa
import com.example.millonariolilia.ElegirCatActivity.Companion.histo
import com.example.millonariolilia.ElegirCatActivity.Companion.mate
import com.example.millonariolilia.Entitys.Model
import com.example.millonariolilia.databinding.ActivityPlayBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlin.properties.Delegates
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    lateinit var DB: FirebaseDatabase
    lateinit var refEspañol: DatabaseReference
    lateinit var refMatematicas: DatabaseReference
    lateinit var refHistoria: DatabaseReference
    var lista:MutableList<Model> = mutableListOf()
    var r=0
    var premio=0
    lateinit var rpt:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayBinding.inflate(layoutInflater)
        DB= FirebaseDatabase.getInstance()
        refMatematicas = DB.getReference("Matematicas")
        refEspañol = DB.getReference("Español")
        refHistoria = DB.getReference("Historia")
        setContentView(binding.root)
        getData()
        jugar()
        validar()
    }

    fun getData(){
        if(mate=="si")
        {
            refMatematicas.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        if(histo=="si")
        {
            refHistoria.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        if(espa=="si")
        {
            refEspañol.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun jugar(){
        mate=""
        histo=""
        espa=""
        Handler().postDelayed({
            binding.textView2.setBackgroundResource(R.drawable.white )
            binding.textView3.setBackgroundResource(R.drawable.white )
            binding.textView4.setBackgroundResource(R.drawable.white )
            binding.textView5.setBackgroundResource(R.drawable.white )
            var size=lista.size
            r=Random.nextInt(1,size)
            binding.textView.text=lista[r].pregunta
            binding.textView2.text=lista[r].a
            binding.textView3.text=lista[r].b
            binding.textView4.text=lista[r].c
            binding.textView5.text=lista[r].d
            rpt=lista[r].respuesta
        },3000)
    }
    fun validar(){
        binding.textView2.setOnClickListener {
            if(binding.textView2.text==rpt)
            { binding.textView2.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            { binding.textView2.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)

            }
        }
        binding.textView3.setOnClickListener {
            if(binding.textView3.text==rpt)
            {  binding.textView3.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {   binding.textView3.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView4.setOnClickListener {
            if(binding.textView4.text==rpt)
            {
                binding.textView4.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {   binding.textView4.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView5.setOnClickListener {
            if(binding.textView5.text==rpt)
            {  binding.textView5.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()

            }
            else
            {  binding.textView5.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
    }
}