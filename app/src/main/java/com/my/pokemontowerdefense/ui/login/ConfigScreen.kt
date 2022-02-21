package com.my.pokemontowerdefense.ui.login

import android.app.AlertDialog
import android.content.DialogInterface
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
        var hardClicked = false
        var medClicked = false
        var easyClicked = false

        // Initialize dialogue alert box
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setPositiveButton("Okay") { dialog, _ ->
            dialog.cancel()
        }

        hardButton.setOnClickListener() {
            hardClicked = true
            hardButton.setSelected(true);
            hardButton.setTextColor(getResources().getColor(R.color.silver))
            if (medClicked) {
                medClicked = false
                mediumButton.setSelected(false)
                mediumButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (easyClicked) {
                easyClicked = false
                easyButton.setSelected(false)
                easyButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }

        mediumButton.setOnClickListener() {
            medClicked = true
            mediumButton.setSelected(true)
            mediumButton.setTextColor(getResources().getColor(R.color.silver))
            if (hardClicked) {
                hardClicked = false;
                hardButton.setSelected(false)
                hardButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (easyClicked) {
                easyClicked = false;
                easyButton.setSelected(false)
                easyButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }

        easyButton.setOnClickListener() {
            easyClicked = true;
            easyButton.setSelected(true)
            easyButton.setTextColor(getResources().getColor(R.color.silver))
            if (hardClicked) {
                hardClicked = false;
                hardButton.setSelected(false)
                hardButton.setTextColor(getResources().getColor(R.color.dark_black))
            } else if (medClicked) {
                medClicked = false;
                mediumButton.setSelected(false)
                mediumButton.setTextColor(getResources().getColor(R.color.dark_black))
            }
        }


        startButton.setOnClickListener() {
            if (nameField.text.toString().isNullOrBlank()) { // if the name is invalid
                dialogBuilder.setMessage("Name is Invalid!")
                val alert = dialogBuilder.create()
                alert.show()
            } else if (!hardClicked && !medClicked && !easyClicked) { // If no difficulty is selected
                dialogBuilder.setMessage("Select a difficulty!")
                val alert = dialogBuilder.create()
                alert.show()
            } else {
                // TODO: If everything goes right
            }
        }
    }


}