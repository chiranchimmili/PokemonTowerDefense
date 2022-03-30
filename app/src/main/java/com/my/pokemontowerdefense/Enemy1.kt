package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import kotlinx.coroutines.delay

class Enemy1(difficulty: String, var enemyList: ArrayList<ImageView>):Enemy() {
//class Enemy1(difficulty: String, var enemy: ImageView):Enemy() {

    init {
        level = 1
        hp = 1

        if (difficulty == "easy") {
            amount = 3
        } else if (difficulty == "medium") {
            amount = 5
        } else {
            amount = 7
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

        var delayCounter = 0L

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

        /*
                enemyList[0].x = -130F
                enemyList[0].y = 160F


                //animate(enemyList[0])
                enemyList[1].x = -200F
                enemyList[1].y = 160F

                enemyList[0].visibility = View.VISIBLE
                enemyList[1].visibility = View.VISIBLE

                val animation1 = ObjectAnimator.ofFloat(enemyList[0], "translationX",725F).apply {
                    duration = 7000
                }
                val animation2 = ObjectAnimator.ofFloat(enemyList[1], "translationX",725F).apply {
                    duration = 7000
                }

                val animationSet = AnimatorSet()
                animationSet.playSequentially(animation1, animation2)
                animationSet.start()
         */



        //animate(enemyList[1])
        //animate(enemyList[1])
     /*//for (enemy in enemyList) {
            enemy.x = -130F
            enemy.y = 160F
            enemy.visibility = View.VISIBLE
            animate(enemy)
            Thread.sleep(1_0)*/
        //}

    }

    private fun animate(image: ImageView) {
        val animationSet = AnimatorSet()
        animationSet.apply {
            ObjectAnimator.ofFloat(image, "translationX",725F).apply {
                duration = 7000
                start()
            }
        }

    }

}

