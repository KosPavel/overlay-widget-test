package com.kospavel.overlay_widget_test

import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MediaProjectionActivity : AppCompatActivity() {

    private lateinit var mediaProjectionManager: MediaProjectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("qwerty", "DrawActivity onCreate")
        setContentView(R.layout.activity_main)
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
        startService(
            Intent(this, MediaProjectionService::class.java)
        )
//        mediaProjection.
    }

    companion object {
        private const val REQUEST_CODE = 123
    }

}