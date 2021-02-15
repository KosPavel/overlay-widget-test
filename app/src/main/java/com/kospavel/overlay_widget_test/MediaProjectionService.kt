package com.kospavel.overlay_widget_test

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.IBinder

class MediaProjectionService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}