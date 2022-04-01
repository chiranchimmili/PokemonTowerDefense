package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd

class GrimerEnemy (difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    var path = Path();

    init {
        level = 1
        hp = 3
        damage = 10

        if (difficulty == "easy") {
            awardMoney = 75
            amount = 1
        } else if (difficulty == "medium") {
            awardMoney = 15
            amount = 2
        } else {
            awardMoney = 3
            amount = 3
        }



    }

    override fun spawnEnemies(monument: Monument, context: Context) {

        path.moveTo(-250F, 185F)
        path.lineTo(885F, 185F)
        path.lineTo(885F, 1010F)
        path.lineTo(1640F, 1010F)
        path.lineTo(1640F, 620F)
        path.lineTo(2500F, 620F)

        val animationList = ArrayList<Animator>()
        for (enemy in enemyList) {
            enemy.x = -250F
            enemy.y = 100F
            enemy.visibility = View.VISIBLE
            val animation = ObjectAnimator.ofFloat(enemy, "translationX","translationY", path).apply {
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