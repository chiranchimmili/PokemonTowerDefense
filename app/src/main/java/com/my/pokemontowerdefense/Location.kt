package com.my.pokemontowerdefense

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout

class Location(var button: Button, var layout: RelativeLayout, attackHorizontal : Boolean) {

    var hasTower: Boolean = false
    var buttonLocation: Button = button
    var layoutSpot: RelativeLayout = layout
    var attackH : Boolean = false
    var attackV : Boolean = false
    var xStart = layout.left
    var yStart = layout.top
    var xEnd = layout.right
    var yEnd = layout.bottom

    init {
        if (attackHorizontal) {
            attackH = true
        } else {
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