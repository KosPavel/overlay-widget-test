package com.kospavel.overlay_widget_test

import android.content.Context
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class ScreenshotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        setResult(
            RESULT_OK,

        )
    }

    private fun getScreenshot() {
        val mpm = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val w = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

}