package nyxdev.hackatren.taralrt1.global.module.nfc

import android.content.Context
import android.content.Intent

interface NfcManager {
    interface CallBack  {
        fun onTagRead(tagRead: String)
    }

    interface Cycle {
        fun onCreate(activityContext:Context): Boolean
        fun onResume(activityContext:Context)
        fun onPause(activityContext:Context)
        fun onNewIntent(intent: Intent)
        fun setOnTagReadListener(onTagReadListener: CallBack?)
    }
}