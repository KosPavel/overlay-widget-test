package com.kospavel.overlay_widget_test

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class OverlayWidgetService : Service(),
//    View.OnTouchListener,
    View.OnClickListener {

    private var topLeftView: View? = null
    private lateinit var overlayedButton: Button
    private var offsetX = 0f
    private var offsetY = 0f
    private var originalXPos = 0
    private var originalYPos = 0
    private var moving = false
    private lateinit var wm: WindowManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        overlayedButton = Button(this)
        overlayedButton.text = "Companion widget"
        overlayedButton.setBackgroundColor(Color.BLACK)
        overlayedButton.setTextColor(Color.WHITE)
        overlayedButton.setOnClickListener(this)
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
//        params.gravity = Gravity.START or Gravity.TOP
//        params.x = 0
//        params.y = 0
        wm.addView(overlayedButton, params)
//        topLeftView = View(this)
//        val topLeftParams = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//            PixelFormat.TRANSLUCENT
//        )
//        topLeftParams.gravity = Gravity.LEFT or Gravity.TOP
//        topLeftParams.x = 0
//        topLeftParams.y = 0
//        topLeftParams.width = 0
//        topLeftParams.height = 0
//        wm!!.addView(topLeftView, topLeftParams)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("qwerty", "service destroyed")
        wm.removeView(overlayedButton)
//            wm!!.removeView(topLeftView)
//            topLeftView = null
    }

//    override fun onTouch(v: View?, event: MotionEvent): Boolean {
//        Log.i("qwerty", "onTouch")
//        if (event.action == MotionEvent.ACTION_DOWN) {
//            v?.performClick()
//            Log.i("qwerty", "click performed")
//            val x = event.rawX
//            val y = event.rawY
//            moving = false
//            val location = IntArray(2)
//            overlayedButton?.getLocationOnScreen(location)
//            originalXPos = location[0]
//            originalYPos = location[1]
//            offsetX = originalXPos - x
//            offsetY = originalYPos - y
//        } else if (event.action == MotionEvent.ACTION_MOVE) {
//            val topLeftLocationOnScreen = IntArray(2)
////            topLeftView?.getLocationOnScreen(topLeftLocationOnScreen)
//            println("topLeftY=" + topLeftLocationOnScreen[1])
//            println("originalY=$originalYPos")
//            val x = event.rawX
//            val y = event.rawY
//            val params: WindowManager.LayoutParams =
//                overlayedButton?.layoutParams as WindowManager.LayoutParams
//            val newX = (offsetX + x).toInt()
//            val newY = (offsetY + y).toInt()
//            if (Math.abs(newX - originalXPos) < 1 && Math.abs(newY - originalYPos) < 1 && !moving) {
//                return false
//            }
//            params.x = newX - topLeftLocationOnScreen[0]
//            params.y = newY - topLeftLocationOnScreen[1]
//            wm!!.updateViewLayout(overlayedButton, params)
//            moving = true
//        } else if (event.action == MotionEvent.ACTION_UP) {
////            v?.performClick()
//            if (moving) {
//                return true
//            }
//        }
//        return false
//    }

    private fun getScreenshot() {
        val mpm = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val w = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Overlay button click event", Toast.LENGTH_SHORT).show()
    }
}