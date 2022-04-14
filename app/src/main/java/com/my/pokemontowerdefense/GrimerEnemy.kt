package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.core.animation.doOnStart
import kotlinx.android.synthetic.main.activity_game_screen.*

class GrimerEnemy (difficulty: String, var numberOfEnemies: Int) :Enemy() {

    var path = Path();
    override var amount = numberOfEnemies

    init {
        level = 1
        hp = 1
        damage = 10
        dead = 0
        bad = 0

        if (difficulty == "easy") {
            awardMoney = 75
        } else if (difficulty == "medium") {
            awardMoney = 15
        } else {
            awardMoney = 3
        }


    }
    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player) {

        val density = Resources.getSystem().displayMetrics.density
        enemyListHealth.clear()

        for (i in 1..amount) {
            var newEnemyView2 = ImageView(context)
            newEnemyView2.layoutParams =
                LinearLayout.LayoutParams((60 * density).toInt(), (60 * density).toInt())
            newEnemyView2.setImageResource(R.drawable.grimer8bit)
            newEnemyView2.id = View.generateViewId()
            gameScreen.addView(newEnemyView2)
            enemyList.add(newEnemyView2)
            enemyListHealth[newEnemyView2.id] = hp
        }

        path.moveTo(-250F, 185F)
        path.lineTo(885F, 185F)
        path.lineTo(885F, 1010F)
        path.lineTo(1640F, 1010F)
        path.lineTo(1640F, 620F)
        path.lineTo(2500F, 620F)

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

            scanForDamage(enemy, this, animation, context, locations, gameScreen, player)

            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    bad += 1
                    monument.reduceMonumentHealth(context, damage)
                }
            }
        }
    }

    override fun reduceEnemyHealth() {
        hp -= 10
    }
}