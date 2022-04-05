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
        hp = 100
        damage = 10

        if (difficulty == "easy") {
            awardMoney = 25
        } else if (difficulty == "medium") {
            awardMoney = 5
        } else {
            awardMoney = 1
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player) {

        for (i in 1..amount) {
            var newEnemyView = ImageView(context)
            newEnemyView.layoutParams =
                LinearLayout.LayoutParams((100 * density).toInt(), (100 * density).toInt())
            newEnemyView.setImageResource(R.drawable.rattata8bit)
            newEnemyView.id = View.generateViewId()
            gameScreen.addView(newEnemyView)
            enemyList.add(newEnemyView)
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