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

class RattataEnemy(difficulty: String, numberOfEnemies: Int) :Enemy() {

    var path = Path();
    override var amount = numberOfEnemies

    init {

        level = 1
        hp = 50
        damage = 10
        dead = 0
        bad = 0

        if (difficulty == "easy") {
            awardMoney = 25
        } else if (difficulty == "medium") {
            awardMoney = 5
        } else {
            awardMoney = 1
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player, stats : Stats) {

        val density = Resources.getSystem().displayMetrics.density
        enemyListHealth.clear()
        for (i in 1..amount) {
            var newEnemyView = ImageView(context)
            newEnemyView.layoutParams =
                LinearLayout.LayoutParams((100 * density).toInt(), (100 * density).toInt())
            newEnemyView.setImageResource(R.drawable.rattata8bit)
            newEnemyView.id = View.generateViewId()
            gameScreen.addView(newEnemyView)
            enemyList.add(newEnemyView)
            enemyListHealth[newEnemyView.id] = hp
        }


        path.moveTo(-250F, 100F)
        path.lineTo(825F, 100F)
        path.lineTo(825F, 925F)
        path.lineTo(1575F, 925F)
        path.lineTo(1575F, 580F)
        path.lineTo(2500F, 580F)

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