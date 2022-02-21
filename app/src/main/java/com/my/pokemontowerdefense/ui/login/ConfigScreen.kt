package com.my.pokemontowerdefense.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.my.pokemontowerdefense.R
import kotlinx.android.synthetic.main.activity_config_screen.*
import java.util.*


class ConfigScreen : AppCompatActivity() {

    //val hardButton: Button = findViewById<View>(R.id.hardButton) as Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_screen)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar())?.hide();

        var hardClicked = false
        var medClicked = false
        var easyClicked = false

        hardButton.setOnClickListener() {
            hardClicked = true
            hardButton.setBackgroundColor(getResources().getColor(R.color.light_black))
            hardButton.setTextColor(getResources().getColor(R.color.silver))
            if (medClicked) {
                medClicked = false
                mediumButton.setBackgroundColor(getResources().getColor(R.color.brown))
                mediumButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (easyClicked) {
                easyClicked = false
                easyButton.setBackgroundColor(getResources().getColor(R.color.brown))
                easyButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }

        mediumButton.setOnClickListener() {
            medClicked = true
            mediumButton.setBackgroundColor(getResources().getColor(R.color.light_black))
            mediumButton.setTextColor(getResources().getColor(R.color.silver))
            if (hardClicked) {
                hardClicked = false;
                hardButton.setBackgroundColor(getResources().getColor(R.color.brown))
                hardButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (easyClicked) {
                easyClicked = false;
                easyButton.setBackgroundColor(getResources().getColor(R.color.brown))
                easyButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }

        easyButton.setOnClickListener() {
            easyClicked = true;
            easyButton.setBackgroundColor(getResources().getColor(R.color.light_black))
            easyButton.setTextColor(getResources().getColor(R.color.silver))
            if (hardClicked) {
                hardClicked = false;
                hardButton.setBackgroundColor(getResources().getColor(R.color.brown))
                hardButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (medClicked) {
                medClicked = false;
                mediumButton.setBackgroundColor(getResources().getColor(R.color.brown))
                mediumButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }
    }


}