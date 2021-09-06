package com.example.sightreadtrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip


class MainActivity : AppCompatActivity() {
    var faBool: Boolean = false;
    var solBool: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // If a chip is clicked, invert the value of the correspondent sol/fa bool
    fun setBool(view: View){
        if(view.id == R.id.clefSolChip) solBool = !solBool; else faBool = !faBool
    }

    //starts the training activity
    fun startTraining(view: View){
        if(!(faBool || solBool)){
            val toast = Toast.makeText(this.applicationContext, "Please select at least one clef", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        val intent = Intent(this, Training::class.java).apply {
            putExtra("FA", faBool)
            putExtra("SOL", solBool)
        }
        startActivity(intent)
    }
}