package nyxdev.hackatren.taralrt1.global.module.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log

class NfcManagerImpl : NfcManager.Cycle {
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var onTagReadListener: NfcManager.CallBack? = null

    private val writeText: String? = null


    override fun setOnTagReadListener(onTagReadListener: NfcManager.CallBack?) {
        this.onTagReadListener = onTagReadListener
    }


    override fun onCreate(activityContext:Context): Boolean {
        nfcAdapter = NfcAdapter.getDefaultAdapter(activityContext)
        pendingIntent = PendingIntent.getActivity(activityContext, 0,
                Intent(activityContext, activityContext.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
        return nfcAdapter != null
    }


    override fun onResume(activityContext:Context) {
        if (nfcAdapter != null) {
            nfcAdapter!!.enableForegroundDispatch(activityContext as Activity, pendingIntent, null, null)
        }
    }


    override fun onPause(activityContext: Context) {
        if (nfcAdapter != null) {
            nfcAdapter!!.disableForegroundDispatch(activityContext as Activity)
        }
    }


    override fun onNewIntent(intent: Intent) {
        if (writeText == null)
            readTagFromIntent(intent)

    }


    private fun readTagFromIntent(intent: Intent) {
        val action = intent.action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == action ||
                NfcAdapter.ACTION_TAG_DISCOVERED == action ||
                NfcAdapter.ACTION_TECH_DISCOVERED == action) {
            val rawMsgss= intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) as NdefMessage?
            val myTag:Tag?= intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) as Tag?
            if (myTag != null) {
                val sb = StringBuilder()
                for (b in myTag.id) {
                    sb.append(String.format("%02X", b))
                }
                try {
                    myTag.techList.forEach { Log.e("NFC","TAG: $it") }
                    Log.e("NFC","TAG: $sb")
                    val  ndef:Ndef?= Ndef.get(myTag)
                    if (ndef!=null ){
                        Log.e("NFC","MSG: ${ndef.ndefMessage}")
                        Log.e("NFC","ISWRITABLE: ${Ndef.get(myTag).isWritable}")
                    }
                    onTagReadListener!!.onTagRead(sb.toString())
                } catch (e:Exception){}

                if (rawMsgss != null) {
                    val sb1 = StringBuilder()
                    for ( x in 0 until rawMsgss.records.size)
                        sb1.append(String.format("%02X",  rawMsgss.records[x].payload))
                    Log.e("NFC","MESSAGE: $sb1")
                }
            }
        }
            else onTagReadListener!!.onTagRead("error")
    }


    fun ndefRecordToString(record: NdefRecord): String {
        val payload = record.payload
        return String(payload)
    }


}