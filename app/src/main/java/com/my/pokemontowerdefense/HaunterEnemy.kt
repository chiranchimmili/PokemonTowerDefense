package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd

class HaunterEnemy(difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    var path = Path();


    init {
        level = 1
        hp = 2000
        damage = 10

        if (difficulty == "easy") {
            awardMoney = 50
            amount = 1
        } else if (difficulty == "medium") {
            awardMoney = 10
            amount = 2
        } else {
            awardMoney = 2
            amount = 3
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>) {

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
            animation.addUpdateListener {
                for (location in locations) {
                    if (location.attackH && !location.attackV) {
                        if (enemy.x > location.xStart && enemy.x < location.xEnd && location.hasTower) {
                            if (hp >= 0) {
                                reduceEnemyHealth()
                            } else {
                                enemy.visibility = View.INVISIBLE
                            }
                        }
                    }
                    else if (!location.attackH && location.attackV) {
                        if (enemy.y > location.yStart && enemy.y < location.yEnd && location.hasTower) {
                            if (hp >= 0) {
                                reduceEnemyHealth()
                            } else {
                                enemy.visibility = View.INVISIBLE
                            }
                        }
                    }
                    else {
                        if (enemy.x > location.xStart && enemy.x < location.xEnd && enemy.y >
                            location.yStart && enemy.y < location.yEnd && location.hasTower) {
                            if (hp >= 0) {
                                reduceEnemyHealth()
                            } else {
                                enemy.visibility = View.INVISIBLE
                            }
                        }

                    }
                }
            }
            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    monument.reduceMonumentHealth(context)
                }
            }
        }
    }
    override fun reduceEnemyHealth() {
        hp -= 5
    }
}