package com.kospavel.overlay_widget_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
//        }

        val intent = Intent(this, OverlayWidgetService::class.java)
        startService(intent)
//        finish()
    }
}