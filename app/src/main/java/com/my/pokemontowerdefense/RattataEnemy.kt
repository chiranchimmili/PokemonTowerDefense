package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd

class RattataEnemy(difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    var path = Path();


    init {

        level = 1
        hp = 1
        damage = 10

        if (difficulty == "easy") {
            awardMoney = 25
            amount = 1
        } else if (difficulty == "medium") {
            awardMoney = 5
            amount = 2
        } else {
            amount = 3
            awardMoney = 1
        }



    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>) {

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
            animation.addUpdateListener {
//                if (enemy.x > 500F) {
//                    if (hp >= 0) {
//                        reduceEnemyHealth()
//                    }
//                    else {
//                        enemy.visibility = View.INVISIBLE
//                    }
//                }

                for (location in locations) {
                    if (location.hasTower && location.attackH && !location.attackV) {
                        if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                            enemy.visibility = View.INVISIBLE
//                            if (hp >= 0) {
//                                reduceEnemyHealth()
//                            } else {
//                                enemy.visibility = View.INVISIBLE
//                            }
                        }
                    }
                    else if (location.hasTower && !location.attackH && location.attackV) {
                        if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                            if (hp >= 0) {
                                reduceEnemyHealth()
                            } else {
                                enemy.visibility = View.INVISIBLE
                            }
                        }
                    }
                    else {
                        if (location.hasTower && enemy.x > location.xStart && enemy.x < location.xEnd && enemy.y >
                            location.yStart && enemy.y < location.yEnd) {
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