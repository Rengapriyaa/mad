package com.example.winwin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class intentactivity : AppCompatActivity() {
    private lateinit var e1:EditText
    private lateinit var e2:EditText
    private lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intentactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        e1=findViewById(R.id.et1)
        e2=findViewById(R.id.et2)
        btn=findViewById(R.id.btnn)
        btn.setOnClickListener {
            val num1:String=e1.text.toString()
            val num2:String =e2.text.toString()
            val n1=Integer.parseInt(num1)
            val n2=Integer.parseInt(num2)
            val sum=n1+n2
            val intent= Intent(this,intenthelp::class.java)
            intent.putExtra("sumval",sum)
            startActivity(intent)
        }
    }
}