package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd

class HaunterEnemy(difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    init {
        level = 1
        hp = 1

        if (difficulty == "easy") {
            amount = 1
        } else if (difficulty == "medium") {
            amount = 2
        } else {
            amount = 3
        }

    }

    override fun spawnEnemies(monument: Monument, context: Context) {
        var path = Path();
        path.moveTo(-250F, 100F)
        path.lineTo(850F, 100F)
        path.lineTo(850F, 925F)
        path.lineTo(1600F, 925F)
        path.lineTo(1600F, 550F)
        path.lineTo(2500F, 550F)

        val animationList = ArrayList<Animator>()
        for (enemy in enemyList) {
            enemy.x = -250F
            enemy.y = 100F
            enemy.visibility = View.VISIBLE
            val animation =
                ObjectAnimator.ofFloat(enemy, "translationX", "translationY", path).apply {
                    duration = 10000
                    startDelay = delayCounter
                    interpolator = null
                }
            delayCounter += 650L;
            animation.start()
            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    monument.reduceMonumentHealth(context)
                }
            }
        }
    }
}