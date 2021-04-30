package dev.luchonetvv.android12poc.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Inicio El Receiver")
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }

        intent.extras.let {
            val state = it?.getString(TelephonyManager.EXTRA_STATE)
            Log.w("MY_DEBUG_TAG", state!!)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                val phoneNumber = it.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.w("MY_DEBUG_TAG", phoneNumber!!)
            }
        }
    }

    companion object {
        private const val TAG = "MyBroadcastReceiver"
    }
}
