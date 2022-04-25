package com.my.pokemontowerdefense

import Giratina
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.res.Resources
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
    override var towerRange = 500


    init {
        imageString="@drawable/bulbasaur1";
        level = 1;
        if (difficulty == "easy") {
            cost = 200;
            damage = 30;
        } else if (difficulty == "medium") {
            cost = 300;
            damage = 20;
        } else {
            cost = 400;
            damage = 10;
        }
    }
    override fun upgrade() : Boolean {
        if (level == 1) {
            imgResId = R.drawable.ivysaur
            imageString = "@drawable/ivysaur"
            damage += 10
            cooldownTime -= 100
            level += 1
            return true
        } else if (level == 2) {
            imgResId = R.drawable.venusaur
            imageString = "@drawable/venusaur"
            damage += 10
            cooldownTime -= 100
            level += 1
            return true
        } else {
            return false
        }
    }

    override fun shootEnemy(
        enemy: ImageView,
        enemyClass: Enemy,
        anim: ObjectAnimator,
        context: Context,
        location: Location,
        gameScreen: ConstraintLayout,
        player: Player,
        monument : Monument,
    stats: Stats
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
        if (distance < towerRange) {
            towerCombat(enemy, enemyClass, player, context, gameScreen, location, monument, stats)
        }

    }
    override fun towerCombat(
        enemyView: ImageView,
        enemyClass: Enemy,
        player : Player,
        context: Context,
        gameScreen: ConstraintLayout,
        location: Location,
        monument : Monument,
    stats: Stats
    ) {
        val density = Resources.getSystem().displayMetrics.density
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
                    intent.putExtra("STATS", stats)
                    context.startActivity(intent)
                    System.exit(0)
                }
                stats.enemiesKilled += 1
                player.addMoney(enemyClass.awardMoney)
                Enemy.removeEnemy(enemyView)
            }
        }
    }
}
