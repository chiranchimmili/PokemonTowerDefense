package com.my.pokemontowerdefense

open abstract class Tower {
    open var cost: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value;
        }
    open var level: Int = 1
        get() {
            return field
        }
        set(value) {
            field = value;
        }
    open var damage: Int = 0
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    open var imageString: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    open var location: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    abstract fun update();
}