package com.linusu.flutter_web_auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class CallbackActivity: Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val url = intent?.data
    val scheme = url?.scheme

    if (scheme != null) {
      FlutterWebAuthPlugin.callbacks.remove(scheme)?.success(url.toString())
    }

    finish()

    val activity = FlutterWebAuthPlugin.activity!!
    val newIntent = Intent(activity, activity.javaClass)
    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    activity.startActivity(newIntent)
  }
}
