package com.example.on_activity_result_test

import android.content.Intent
import android.os.Bundle
import android.util.Log

import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    private var resultIntent: Intent? = null
    private var result: MethodChannel.Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("flutter android", "enter onCreate");
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        MethodChannel(
            this.flutterView,
            "sampleapp/main"
        ).setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
            if (methodCall.method == "openOtherScreen") {
                val count = methodCall.arguments<Int>()
                openOtherScreen(count)
                this.result = result
            } else if (methodCall.method == "getActivityResult") {
                val newCount = resultIntent?.getIntExtra("COUNT_RESULT", -1) ?: -1
                result.success(newCount)
            }
        }
        Log.i("flutter android", "exit onCreate");
    }

    private fun openOtherScreen(count: Int) {
        val intent = Intent(this, Main2Activity::class.java).apply {
            putExtra("COUNT", count)
        }
        startActivityForResult(intent, 999)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("flutter android", "enter onActivityResult");
        super.onActivityResult(requestCode, resultCode, data)
        resultIntent = data
        result?.success(null)
        Log.i("flutter android", "exit onActivityResult");
    }
}
