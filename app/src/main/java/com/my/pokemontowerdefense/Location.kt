package com.my.pokemontowerdefense

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout

class Location(var button: Button, var layout: RelativeLayout, attackHorizontal : Boolean, attackVertical : Boolean, special : Int, left : Float, top : Float,
right : Float, bottom : Float) {

    var hasTower: Boolean = false
    lateinit var towerReference: Tower
    var buttonLocation: Button = button
    var layoutSpot: RelativeLayout = layout
    var attackH : Boolean = false
    var attackV : Boolean = false
    var timeStamp = System.currentTimeMillis()

    init {
        if (attackHorizontal) {
            attackH = true
        }
        if (attackVertical) {
            attackV = true
        }
    }

    fun setVisible () {
        button.visibility = View.VISIBLE
    }

    // turns off visibility of all tower placement buttons
    companion object {
        fun allVisibilityOff(locations: ArrayList<Location>) {
            for (location in locations) {
                location.button.visibility = View.INVISIBLE
            }
        }
    }
}