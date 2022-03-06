package com.my.pokemontowerdefense

open abstract class Tower : GameScreen() {
    open var cost: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value;
        }



}