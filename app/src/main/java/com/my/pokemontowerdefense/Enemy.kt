package com.my.pokemontowerdefense

import android.widget.ImageView

open abstract class Enemy {
    open var hp: Int = 0
    open var level: Int = 0
    open var amount: Int = 0

    abstract fun spawnEnemies()
}