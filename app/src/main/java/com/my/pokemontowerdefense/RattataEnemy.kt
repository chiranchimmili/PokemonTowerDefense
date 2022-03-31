package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.coroutines.delay

class RattataEnemy(difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    init {
        level = 1
        hp = 1
        var gameScreen = GameScreen()

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
        path.lineTo(825F, 100F)
        path.lineTo(825F, 925F)
        path.lineTo(1575F, 925F)
        path.lineTo(1575F, 580F)
        path.lineTo(2500F, 580F)

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