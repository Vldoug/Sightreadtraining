package com.example.sightreadtrain

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import java.lang.Thread.sleep
import java.util.*
import java.util.Collections.shuffle
import kotlin.math.round

class Training : AppCompatActivity() {
    private var list = List(0) { i -> i}
    var listIterator = 0
    var random = Random()
    var currentNote = 0
    var bothClefs = false
    var currentClef = true  //true-> treble, false->bass
    var maxNum = 0
    val noteMap = mapOf<String, Int>("D" to 0, "E" to 1, "F" to 2, "G" to 3, "A" to 4, "B" to 5, "C" to 6)

    private fun clefVisibility(bool: Boolean): Int{
        if(bool) return View.VISIBLE; else return View.INVISIBLE
    }

    //if(bool) then shows treble, if(!bool) shows bass
    private fun showClef(bool: Boolean){
        findViewById<ImageView>(R.id.trebleClef).visibility = clefVisibility(bool)
        findViewById<ImageView>(R.id.bassClef).visibility = clefVisibility(!bool)
        currentClef = bool
    }


    fun nextNote(){
        if(listIterator == maxNum){
            listIterator = 0
            shuffle(list, random)
        }
        val note = findViewById<ImageView>(R.id.note)
        var nextVal = list[listIterator]
        if(bothClefs){
            if(nextVal > 28){
                nextVal -= 29
                showClef(false)
            } else {
                showClef(true)
            }
        }
        note.setImageResource(resources.getIdentifier("note$nextVal", "drawable", this.packageName))
        currentNote = nextVal
        listIterator++
    }

    fun checkAnswer(view: View){
        val but: Button = view as Button
        var offset = 0
        if(currentClef) offset = -2
        if((currentNote+offset).mod(7) == noteMap.getOrDefault(but.text, 1)){
            // TODO() //Yay, it's right
            //put in like a green checkmark or smth
            nextNote()
        } else {
            //Nooo, it's wrong! Put in like a red checkmark or smth
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        val faBool = intent.getBooleanExtra("FA" , false)
        val solBool = intent.getBooleanExtra("SOL", false)

        if(!(faBool && solBool)){
            findViewById<ImageView>(R.id.bassClef).visibility = clefVisibility(faBool)
            findViewById<ImageView>(R.id.trebleClef).visibility = clefVisibility(solBool)
            currentClef = solBool
            bothClefs = false
        } else bothClefs = true

        if(bothClefs) maxNum = 58 else maxNum = 29

        listIterator = maxNum

        list = List(maxNum-1) {i -> i}

        nextNote()
    }

}
