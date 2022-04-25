import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Path
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.my.pokemontowerdefense.*
import kotlinx.android.synthetic.main.activity_game_screen.*

class Giratina(difficulty: String, numberOfEnemies: Int) : Enemy() {

    var path = Path();
    override var amount = numberOfEnemies

    init {
        level = 1
        hp = 300
        damage = 1000000

        if (difficulty == "easy") {
            awardMoney = 50
        } else if (difficulty == "medium") {
            awardMoney = 10
        } else {
            awardMoney = 2
        }
    }

    override fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player, stats: Stats) {

        val density = Resources.getSystem().displayMetrics.density
        enemyListHealth.clear()

        var newEnemyView = ImageView(context)
        newEnemyView.layoutParams = LinearLayout.LayoutParams((80 * density).toInt(), (80 * density).toInt())
        newEnemyView.setImageResource(R.drawable.giratina)
        newEnemyView.id = View.generateViewId()
        gameScreen.addView(newEnemyView)
        enemyList.add(newEnemyView)
        enemyListHealth[newEnemyView.id] = hp

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
            scanForDamage(enemy, this, animation, context, locations, gameScreen, player, monument, stats)
            animation.doOnEnd {
                if (enemy.visibility == View.VISIBLE) {
                    monument.reduceMonumentHealth(context, damage, stats)
                } else {
                        val intent = Intent(context, GameOverScreen::class.java)
                        context.startActivity(intent)
                        System.exit(0)
                }
            }
        }
    }
    override fun reduceEnemyHealth() {
        hp -= 5
    }

}
