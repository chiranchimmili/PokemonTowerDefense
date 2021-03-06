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
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_game_screen.*

class HaunterEnemy(difficulty: String, numberOfEnemies: Int) :Enemy() {

    var path = Path();
    override var amount = numberOfEnemies
    var imgResId = R.drawable.haunter8bit

    init {
        level = 1
        hp = 30
        damage = 15
        dead = 0
        bad = 0

        if (difficulty == "easy") {
            awardMoney = 60
        } else if (difficulty == "medium") {
            awardMoney = 40
        } else {
            awardMoney = 20
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player, stats: Stats) {

        val density = Resources.getSystem().displayMetrics.density
        enemyListHealth.clear()

        for (i in 1..amount) {
            var newEnemyView3 = ImageView(context)
            newEnemyView3.layoutParams =
                LinearLayout.LayoutParams((73 * density).toInt(), (73 * density).toInt())
            newEnemyView3.setImageResource(R.drawable.haunter8bit)
            newEnemyView3.id = View.generateViewId()
            gameScreen.addView(newEnemyView3)
            enemyList.add(newEnemyView3)
            enemyListHealth[newEnemyView3.id] = hp
        }

        path.moveTo(-250F, 100F)
        path.lineTo(850F, 100F)
        path.lineTo(850F, 925F)
        path.lineTo(1600F, 925F)
        path.lineTo(1600F, 550F)
        path.lineTo(2500F, 550F)

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

            scanForDamage(enemy, this, animation, context, locations, gameScreen, player, monument, stats)

            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    bad += 1
                    monument.reduceMonumentHealth(context, damage, stats)
                }
            }
        }
    }
    override fun reduceEnemyHealth() {
        hp -= 5
    }
}