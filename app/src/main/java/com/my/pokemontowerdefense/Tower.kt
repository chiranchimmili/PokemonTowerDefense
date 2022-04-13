package com.my.pokemontowerdefense

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Path
import android.icu.number.IntegerWidth
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd

open abstract class Tower {
    abstract val imgResId: Int
    open var cooldownTime = 0
    val density = Resources.getSystem().displayMetrics.density
    abstract val atkResId: Int
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
    open var description: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    abstract fun update();
    abstract fun shootEnemy(enemy: ImageView, enemyClass: Enemy, anim: ObjectAnimator, context: Context, location : Location, gameScreen: ConstraintLayout, player : Player);

    fun towerCombat(enemyView: ImageView, enemyClass: Enemy, player : Player, context: Context, gameScreen: ConstraintLayout, location: Location) {
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
                player.addMoney(enemyClass.awardMoney)
                enemyView.visibility = View.INVISIBLE
            }
        }

    }
}