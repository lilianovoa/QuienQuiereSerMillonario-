package com.example.millonariolilia

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.millonariolilia.ElegirCatActivity.Companion.biol
import com.example.millonariolilia.ElegirCatActivity.Companion.depo
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
    lateinit var refDeporte: DatabaseReference
    lateinit var refBiologia: DatabaseReference
    var lista:MutableList<Model> = mutableListOf()
    var r=0
    var premio=0
    lateinit var rpt:String
    lateinit var musica: MediaPlayer
    lateinit var musica1:MediaPlayer
    lateinit var musica2:MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayBinding.inflate(layoutInflater)
        DB= FirebaseDatabase.getInstance()
        refMatematicas = DB.getReference("Matematicas")
        refEspañol = DB.getReference("Español")
        refHistoria = DB.getReference("Historia")
        refDeporte = DB.getReference("Deporte")
        refBiologia = DB.getReference("Biologia")
        setContentView(binding.root)
        musica = MediaPlayer.create(this ,R.raw.sonidojuego)
        musica1=MediaPlayer.create(this,R.raw.correcta)
        musica2=MediaPlayer.create(this,R.raw.incorrecta)
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
        if(biol =="si")
        {
            refBiologia.addValueEventListener(object : ValueEventListener {
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
        if(depo =="si")
        {
            refDeporte.addValueEventListener(object : ValueEventListener {
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
        biol =""
        depo =""

        Handler().postDelayed({
                if(lista.size!=0)
                {
                    musica.start()
                    binding.textView2.setBackgroundResource(R.drawable.white )
                    binding.textView3.setBackgroundResource(R.drawable.white )
                    binding.textView4.setBackgroundResource(R.drawable.white )
                    binding.textView5.setBackgroundResource(R.drawable.white )
                    var size=lista.size
                    r=Random.nextInt(0,size)
                    binding.textView.text=lista[r].pregunta
                    binding.textView2.text=lista[r].a
                    binding.textView3.text=lista[r].b
                    binding.textView4.text=lista[r].c
                    binding.textView5.text=lista[r].d
                    rpt=lista[r].respuesta
                }
                else
                {
                    musica.pause()
                    Toast.makeText(this, "Your Winner", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                }

            },4000)
    }
    fun validar(){

        binding.textView2.setOnClickListener {
            if(binding.textView2.text==rpt)
            {    musica1.start()
                binding.textView2.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {
                musica.pause()
                musica2.start()
                binding.textView2.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)

            }
        }
        binding.textView3.setOnClickListener {
            if(binding.textView3.text==rpt)
            {   musica1.start()
                binding.textView3.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {   musica2.start()
                musica.pause()
                binding.textView3.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView4.setOnClickListener {
            if(binding.textView4.text==rpt)
            {   musica1.start()
                binding.textView4.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {   musica2.start()
                musica.pause()
                binding.textView4.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView5.setOnClickListener {
            if(binding.textView5.text==rpt)
            {   musica1.start()
                binding.textView5.setBackgroundResource(R.drawable.green )
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()

            }
            else
            {   musica2.start()
                musica.pause()
                binding.textView5.setBackgroundResource(R.drawable.red )
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
    }
}