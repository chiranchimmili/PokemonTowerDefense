package com.my.pokemontowerdefense


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Handler
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.TextView
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.android.synthetic.main.activity_game_screen.monumentHealth
import org.junit.Test
import org.junit.Rule
import org.junit.Before
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

class M4UnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private var handler = Mockito.mock(GameScreen::class.java)

    lateinit var viewModel: GameScreen

    @Before
    fun setUp() {
        viewModel = GameScreen()
    }

    @Test
    fun monumentHealthDecreases() {
        var monumentEasy = Monument(viewModel.findViewById<TextView>(R.id.monumentHealth), "easy")
        viewModel.startWave();
        assertNotEquals(200, monumentEasy.health)
//
//        var monumentMed = Monument(gamescreen.findViewById<TextView>(R.id.monumentHealth), "medium")
//        gamescreen.startWave();
//        assertNotEquals(100, monumentMed.health)
//
//        var monumentHard = Monument(gamescreen.findViewById<TextView>(R.id.monumentHealth), "hard")
//        gamescreen.startWave();
//        assertNotEquals(50, monumentHard.health)
    }



}