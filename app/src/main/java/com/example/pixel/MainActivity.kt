package com.example.pixel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.os.Handler
import kotlin.system.exitProcess

import java.io.DataOutputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.set_pixel)
        button.setOnClickListener {
            val su = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(su.outputStream)
            val cmdOverrideSoli = "setprop pixel.oslo.allowed_override true "+
                    "&& setprop persist.pixel.oslo.allowed_override true "+
                    "&& setprop ctl.restart zygote"
            outputStream.writeBytes(cmdOverrideSoli)
            outputStream.flush()
            Handler().postDelayed({
                exitProcess(-1)},1500)
        }
    }
}
