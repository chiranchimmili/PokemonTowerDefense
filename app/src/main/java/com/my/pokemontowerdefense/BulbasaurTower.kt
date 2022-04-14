package com.my.pokemontowerdefense

import Giratina
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import kotlin.math.sqrt

class BulbasaurTower(difficulty: String): Tower() {
    constructor() : this("easy")

    override var imgResId = R.drawable.bulbasaur1
    override var cooldownTime = 500
    override var atkResId = R.drawable.razor_leaf

    init {
        imageString="@drawable/bulbasaur1";
        level = 1;
        if (difficulty == "easy") {
            cost = 200;
            damage = 10;
        } else if (difficulty == "medium") {
            cost = 300;
            damage = 20;
        } else {
            cost = 400;
            damage = 30;
        }
    }
    override fun update() {

    }

    override fun shootEnemy(
        enemy: ImageView,
        enemyClass: Enemy,
        anim: ObjectAnimator,
        context: Context,
        location: Location,
        gameScreen: ConstraintLayout,
        player: Player
    ) {
        var point = IntArray(2)
        location.buttonLocation.getLocationOnScreen(point)
        val (buttonX, buttonY) = point

        point = IntArray(2)
        enemy.getLocationOnScreen(point)
        val (enemyX, enemyY) = point

        // If the distance between the button and the bulbasaur is small enough
        val _xdif = (buttonX-enemyX)
        val _ydif = (buttonY-enemyY)
        val distance = sqrt(((_xdif*_xdif) + (_ydif*_ydif)).toDouble())

        println("The distance is $distance")
        if (distance < 500) {
            towerCombat(enemy, enemyClass, player, context, gameScreen, location)
        }

    }
    override fun towerCombat(
        enemyView: ImageView,
        enemyClass: Enemy,
        player : Player,
        context: Context,
        gameScreen: ConstraintLayout,
        location: Location
    ) {
        var healthVal = enemyClass.enemyListHealth.getValue(enemyView.id)
        var point = IntArray(2)
        location.buttonLocation.getLocationOnScreen(point)
        val (towerX, towerY) = point


        if (healthVal > 0) {
            if (System.currentTimeMillis() - location.timeStamp > cooldownTime) {
                var bullet = ImageView(context)
                bullet.layoutParams = LinearLayout.LayoutParams((30 * density).toInt(), (30 * density).toInt())
                bullet.setImageResource(atkResId)
                bullet.id = View.generateViewId()
                bullet.x = towerX.toFloat();
                bullet.y = towerY.toFloat();
                gameScreen.addView(bullet)


                point = IntArray(2)
                enemyView.getLocationOnScreen(point)
                val (enemyX, enemyY) = point
                var path = Path();
                path.moveTo(towerX.toFloat(), towerY.toFloat())
                path.lineTo(enemyX.toFloat(), enemyY.toFloat())
                val animation = ObjectAnimator.ofFloat(bullet, "translationX", "translationY", path).apply {
                    duration = 100
                    interpolator = null
                }

                animation.start()
                animation.doOnEnd {
                    gameScreen.removeView(bullet)
                }

                enemyClass.enemyListHealth[enemyView.id] = healthVal - damage
                location.timeStamp = System.currentTimeMillis()
            }

        } else {
            if (enemyView.visibility == View.VISIBLE) {
                if (enemyClass is Giratina) {
                    val intent = Intent(context, WinScreen::class.java)
                    context.startActivity(intent)
                    System.exit(0)
                }
                player.addMoney(enemyClass.awardMoney)
                enemyView.visibility = View.INVISIBLE
            }
        }
    }
}
