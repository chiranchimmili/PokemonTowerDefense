package com.my.pokemontowerdefense.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.pokemontowerdefense.R
import kotlinx.android.synthetic.main.activity_config_screen.*


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

        hardButton.setOnClickListener {
            hardClicked = true
            hardButton.isSelected = true
            if (medClicked) {
                medClicked = false
                mediumButton.isSelected = false
            } else if (easyClicked) {
                easyClicked = false
                easyButton.isSelected = false
            }
        }

        mediumButton.setOnClickListener {
            medClicked = true
            mediumButton.isSelected = true
                if (hardClicked) {
                    hardClicked = false
                    hardButton.isSelected = false
                } else if (easyClicked) {
                    easyClicked = false
                    easyButton.isSelected = false
                }
        }

        easyButton.setOnClickListener {
            easyClicked = true
            easyButton.isSelected = true
            if (hardClicked) {
                hardClicked = false
                hardButton.isSelected = false
            } else if (medClicked) {
                medClicked = false
                mediumButton.isSelected = false
            }
        }


        startButton.setOnClickListener {
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