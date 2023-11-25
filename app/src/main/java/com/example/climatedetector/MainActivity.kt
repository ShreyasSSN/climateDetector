package com.example.climatedetector

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var climateButton : Button
    lateinit var resultArea: TextView
    var handler = Handler()
    var arrayString = arrayOf("Connecting to Cloud", "Might be rainy or sunny\nbut checking", "Weather report on the way", "Getting the data", "Here it is..!!!!")
    var i =0
    var updateTextRunnable = object : Runnable{
        override fun run() {
            resultArea.text = arrayString[i]
            i++
            if (i<5){
                handler.postDelayed(this, 3000)
            }
            else{
                showAlertDialog()
                i=0
                resultArea.text = ""
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        climateButton = findViewById(R.id.climateButton)
        resultArea = findViewById(R.id.result)
        climateButton.setOnClickListener {
            resultArea.post(updateTextRunnable)
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(updateTextRunnable)
        super.onDestroy()
    }
    fun showAlertDialog(){
        var alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Climate")
            .setMessage("Go Check Outside")
            .setIcon(R.drawable.baseline_thunderstorm_24)
            .setPositiveButton("Ok", DialogInterface.OnClickListener{ dialog, _ ->  })
        alertDialog.create().show()
    }
}