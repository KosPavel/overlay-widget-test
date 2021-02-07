package com.kospavel.overlay_widget_test

import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class DrawActivity : AppCompatActivity() {

    private lateinit var mediaProjectionManager: MediaProjectionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_draw)

        mediaProjectionManager =
            getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_CODE)
    }

    init {
        Log.i("qwerty", "DrawActivity init")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //TODO result from screenshotActivity
        Log.i("qwerty", "result came, data = ${data.toString()}")
        val mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data!!)
        mediaProjection.
    }

    companion object {
        private const val REQUEST_CODE = 123
    }

}