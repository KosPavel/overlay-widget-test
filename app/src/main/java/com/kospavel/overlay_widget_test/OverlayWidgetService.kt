package com.kospavel.overlay_widget_test

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class OverlayWidgetService : Service(), View.OnClickListener {

    private lateinit var widget: Button
    private lateinit var wm: WindowManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        widget = Button(this).apply {
            text = "Companion widget"
            setBackgroundColor(Color.BLACK)
            setTextColor(Color.WHITE)
            setOnClickListener(this@OverlayWidgetService)
        }

        val params = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
            )
        } else {
            WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
            )
        }.apply {
            gravity = Gravity.START or Gravity.TOP
            x = 0
            y = 0
        }

        wm.addView(widget, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        wm.removeView(widget)
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Overlay button click event", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MediaProjectionActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}