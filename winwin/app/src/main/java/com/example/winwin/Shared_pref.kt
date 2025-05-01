package com.example.winwin

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Shared_pref : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var et: EditText
    private lateinit var tb: TextView
    private lateinit var btn: Button

    private val pref_name = "my_pref"
    private val smood = "mymood"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_pref)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sp = getSharedPreferences(pref_name, MODE_PRIVATE)
        sp.edit().remove(smood).apply()
        et = findViewById(R.id.editt)
        btn = findViewById(R.id.bun)
        tb = findViewById(R.id.t2)

        val sm = sp.getInt(smood, 0)
        tb.text = "$sm"
        btn.setOnClickListener {
            val ans = et.text.toString()
            val num=ans.toIntOrNull()
            if(num!=null) {
                val old = sp.getInt(smood, 0)
                val ns = maxOf(old, num)
                sp.edit().putInt(smood, ns).apply()
                tb.text = "$ns"
            }
        }

    }
}
