package com.my.pokemontowerdefense

import android.widget.TextView
import java.io.Serializable

class Player (textView: TextView?, difficulty: String) : Serializable {

    var money: Int = 0
    var moneySpent: Int = 0
    var score: Int = 0
    var name: String = ""
    var moneyView = textView

    init {
        if (difficulty == "hard") {
            money = 500;
        } else if (difficulty == "medium") {
            money = 1000;
        } else {
            money = 2000;
        }
    }

    fun moneyViewUpdate() {
        moneyView!!.text = money.toString()
    }
    fun addMoney(amount : Int) {
        money += amount
        moneyView!!.text = money.toString()
    }
    fun subtractMoney(amount : Int, stats: Stats) {
        money -= amount
        moneySpent += amount
        stats.moneySpent += amount
        moneyView!!.text = money.toString()
    }
}