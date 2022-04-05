package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
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

    init {
        level = 1
        hp = 250
        damage = 10

        if (difficulty == "easy") {
            awardMoney = 50
        } else if (difficulty == "medium") {
            awardMoney = 10
        } else {
            awardMoney = 2
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player) {

        for (i in 1..amount) {
            var newEnemyView3 = ImageView(context)
            newEnemyView3.layoutParams =
                LinearLayout.LayoutParams((73 * density).toInt(), (73 * density).toInt())
            newEnemyView3.setImageResource(R.drawable.haunter8bit)
            newEnemyView3.id = View.generateViewId()
            gameScreen.addView(newEnemyView3)
            enemyList.add(newEnemyView3)
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
            animation.addUpdateListener {
                for (location in locations) {
                    if (location.hasTower) {
                        if (location.attackH && !location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                combat(enemy, player)
                            }
                        } else if (!location.attackH && location.attackV) {
                            if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, player)
                            }
                        } else if (location.attackH && location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd && enemy.y >
                                location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, player)
                            }
                        } else if (!location.attackV && !location.attackH && location.isSpecial == 0) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                combat(enemy, player)
                            } else if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, player)
                            }
                        } else {
                            if (location.isSpecial == 1) {
                                if (enemy.x > 820F && enemy.x < 900F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    combat(enemy, player)
                                } else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    combat(enemy, player)
                                }
                            } else if (location.isSpecial == 2) {
                                if (enemy.x > 1550F && enemy.x < 1650F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    combat(enemy, player)
                                }
                                else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    combat(enemy, player)
                                }
                            }
                        }
                    }
                }
            }
            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    monument.reduceMonumentHealth(context, damage)
                }
            }
        }
    }
    override fun reduceEnemyHealth() {
        hp -= 5
    }
}