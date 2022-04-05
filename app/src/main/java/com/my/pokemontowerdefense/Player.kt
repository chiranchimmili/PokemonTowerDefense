package com.my.pokemontowerdefense

import android.widget.TextView

class Player (textView: TextView?, difficulty: String) {

    var money: Int = 0
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
    fun subtractMoney(amount : Int) {
        money -= amount
        moneyView!!.text = money.toString()
    }
}