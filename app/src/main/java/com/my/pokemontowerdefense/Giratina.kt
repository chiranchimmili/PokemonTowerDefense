import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.my.pokemontowerdefense.*
import kotlinx.android.synthetic.main.activity_game_screen.*

class Giratina(difficulty: String, numberOfEnemies: Int) :
    Enemy() {

    var path = Path();
    override var amount = numberOfEnemies

    init {
        level = 1
        hp = 1000
        damage = 1000000

        if (difficulty == "easy") {
            awardMoney = 50
        } else if (difficulty == "medium") {
            awardMoney = 10
        } else {
            awardMoney = 2
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout) {

        var newEnemyView = ImageView(context)
        newEnemyView.layoutParams = LinearLayout.LayoutParams((80 * density).toInt(), (80 * density).toInt())
        newEnemyView.setImageResource(R.drawable.giratina)
        newEnemyView.id = View.generateViewId()
        gameScreen.addView(newEnemyView)
        enemyList.add(newEnemyView)

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
                    startDelay = 0
                    interpolator = null
                }
            animation.start()
            animation.addUpdateListener {
                for (location in locations) {
                    if (location.hasTower) {
                        if (location.attackH && !location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                combat(enemy, context, monument)
                            }
                        } else if (!location.attackH && location.attackV) {
                            if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, context, monument)
                            }
                        } else if (location.attackH && location.attackV) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd && enemy.y >
                                location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, context, monument)
                            }
                        } else if (!location.attackV && !location.attackH && location.isSpecial == 0) {
                            if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                combat(enemy, context, monument)
                            } else if (enemy.y > location.yStart && enemy.y < location.yEnd) {
                                combat(enemy, context, monument)
                            }
                        } else {
                            if (location.isSpecial == 1) {
                                if (enemy.x > 820F && enemy.x < 900F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    combat(enemy, context, monument)
                                } else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    combat(enemy, context, monument)
                                }
                            } else if (location.isSpecial == 2) {
                                if (enemy.x > 1550F && enemy.x < 1650F && enemy.y > location.yStart && enemy.y < location.yEnd) {
                                    combat(enemy, context, monument)
                                }
                                else if (enemy.x > location.xStart && enemy.x < location.xEnd) {
                                    combat(enemy, context, monument)
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

    fun combat(enemy: ImageView, context : Context?, monument: Monument) {
        if (hp >= 0) {
            reduceEnemyHealth()
        } else {
            enemy.visibility = View.INVISIBLE
            monument.gameIsOver = true
            if (context != null) {
                val intent = Intent(context, WinScreen::class.java)
                context.startActivity(intent)
                System.exit(0)
            }
        }
    }
}
