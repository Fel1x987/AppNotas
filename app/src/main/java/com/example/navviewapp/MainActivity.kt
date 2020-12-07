package com.example.navviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(appbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_action_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { menuItem ->
            var fragmentTransformation = false
            var fragment : Fragment? = null

            when (menuItem.itemId){
                R.id.menuopcion1 ->{
                    fragment = fragment1()
                    fragmentTransformation = true
                }
                R.id.menuopcion2 ->{
                    fragment = fragment2()
                    fragmentTransformation = true
                }
            }
            //TODO Este fragmento es para reemplazar el fragment
            if (fragmentTransformation){
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment!!).commit()

                menuItem.isChecked = true
                supportActionBar!!.title = menuItem.title

            }

            drawer_layout.closeDrawers()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) //Lo que hace es cargar el menu que habiamos creado, PONE LOS 3 PUNTITOS
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                drawer_layout.openDrawer(Gravity.START) //Cuando presionas "home" o el menu hamburguesa
                return true
            }
            R.id.settings ->{
                Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}