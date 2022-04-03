package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.core.animation.doOnStart

class GrimerEnemy (difficulty: String, var enemyList: ArrayList<ImageView>) :Enemy() {

    var path = Path();

    init {
        level = 1
        hp = 50
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
    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>) {

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
                    if (location.hasTower) {
                        if (location.attackH && !location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                monument.reduceMonumentHealth(context)
//                            if (hp >= 0) {
//                                reduceEnemyHealth()
//                            } else {
//                                enemy.visibility = View.INVISIBLE
//                            }
                            }
                        } else if (!location.attackH && location.attackV) {
                            if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                monument.reduceMonumentHealth(context)
//                            if (hp >= 0) {
//                                reduceEnemyHealth()
//                            } else {
//                                enemy.visibility = View.INVISIBLE
//                            }
                            }
                        } else if (location.attackH && location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd && enemy.y >
                                location.yStart && enemy.y < location.yEnd) {
                                monument.reduceMonumentHealth(context)
                            }
                        } else if (!location.attackV && !location.attackH && location.isSpecial == 0) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                monument.reduceMonumentHealth(context)
//                            if (hp >= 0) {
//                                reduceEnemyHealth()
//                            } else {
//                                enemy.visibility = View.INVISIBLE
//                            }
                            } else if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                monument.reduceMonumentHealth(context)
                            }
                        } else {
                            if (location.isSpecial == 1) {
                                if (enemy.x > 820F && enemy.x < 900F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    monument.reduceMonumentHealth(context)
                                } else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    monument.reduceMonumentHealth(context)
                                }
                            } else if (location.isSpecial == 2) {
                                if (enemy.x > 1550F && enemy.x < 1650F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    monument.reduceMonumentHealth(context)
                                }
                                else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    monument.reduceMonumentHealth(context)
                                }
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
        hp -= 10
    }
}