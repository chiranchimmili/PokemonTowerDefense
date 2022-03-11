package com.my.pokemontowerdefense

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout

class Location(var button: Button, var layout: RelativeLayout) {

    var hasTower: Boolean = false
    var buttonLocation: Button = button
    var layoutSpot: RelativeLayout = layout

    // turns off visibility of all tower placement buttons
    fun visibilityOff(locations: ArrayList<Location>) {
        for (location in locations) {
            location.button.visibility = View.INVISIBLE
        }
    }
}