package com.example.winwin

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class geolocation : AppCompatActivity() {
    private lateinit var tvv: TextView
    private lateinit var btnn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_geolocation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val latitude=9.8823703
        val longitude=78.08335563
        btnn=findViewById(R.id.btnn)
        btnn.setOnClickListener {

            tvv=findViewById(R.id.tvv)
            tvv.text=findlocation(latitude,longitude)
        }
    }
    private fun findlocation(lat: Double, lng: Double):String?{
        return try{
        val geo=Geocoder(this, Locale.getDefault())
        val adrss=geo.getFromLocation(lat,lng,1)
        if(!adrss.isNullOrEmpty()){
            val adr=adrss[0]
            """
                "address:${adr.getAddressLine(0)}"+
                "location:${adr.subLocality}"+
                "area:${adr.postalCode}"+
                "subadimn:${adr.countryName}"
            """.trimIndent()
        }
            else{
                "failed to retreive"
        }
        }catch(e:Exception)
        {
            e.printStackTrace()
            "failed to get"
        }


    }
}