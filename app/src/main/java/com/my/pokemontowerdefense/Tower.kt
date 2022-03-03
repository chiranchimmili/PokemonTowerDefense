package com.my.pokemontowerdefense

abstract class Tower {
    val difficulty = GameScreen().difficulty
    open var cost: Int = 0
        get() {
            return field
        }

    abstract fun determineCost(): Int


}