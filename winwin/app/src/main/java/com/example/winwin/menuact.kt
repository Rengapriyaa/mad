package com.example.winwin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menuact : AppCompatActivity() {
private lateinit var ttv:TextView
private lateinit var but: Button
private lateinit var pr:Button
private lateinit var ab:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menuact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        but=findViewById(R.id.pb)
        ttv=findViewById(R.id.texv)
        but.setOnClickListener{view->
            popfunc(view)
    }
        registerForContextMenu(ttv)
        //progress bar
        pr=findViewById(R.id.progb)
        pr.setOnClickListener {
            val pb=ProgressDialog(this)
            pb.setTitle("food")
            pb.setMessage("full")
            pb.show()
        }
        //alertdialog
        ab=findViewById(R.id.alb)
        ab.setOnClickListener {
            val al=AlertDialog.Builder(this)
            al.setTitle("vanakam")
            al.setMessage("hi guys")
            al.setCancelable(false)
            al.setNegativeButton("No"){
                    dialog,which->dialog.cancel()
            }
            al.setPositiveButton("Yes"){
                    doalog,which->finish()
            }
            val bu=al.create()
            bu.show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.m1->{
                makeText(this,"item1 selected", LENGTH_SHORT).show()
                return true
            }
            R.id.m2->{
                val intent=Intent(this,intentactivity::class.java)
                startActivity(intent)
                return true
            }

            else -> {return false}
        }

    }
    private fun popfunc(view: View){
        val ppm=PopupMenu(this,view)
        ppm.menuInflater.inflate(R.menu.menu,ppm.menu)
        ppm.setOnMenuItemClickListener { item:MenuItem->
            when(item.itemId){
                R.id.m1->{
                    Toast.makeText(this,"menu selected", LENGTH_SHORT).show()
                    true
                }

                else -> {false}
            }
        }
        ppm.show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.m3->{
                Toast.makeText(this,"item3 selected",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {return false}
        }
        return super.onContextItemSelected(item)
    }
    //alertdialog
    override fun onBackPressed() {
        super.onBackPressed()
        val al=AlertDialog.Builder(this)
        al.setTitle("vanakam")
        al.setMessage("hi guys")
        al.setCancelable(false)
        al.setNegativeButton("No"){
            dialog,which->dialog.cancel()
        }
        al.setPositiveButton("Yes"){
            dialog,which->finish()
        }
        val bu=al.create()
        bu.show()
    }
}