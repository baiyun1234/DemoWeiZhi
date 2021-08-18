package bai.bai.bai.demo.activity

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.tech.IsoDep
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bai.bai.bai.demo.R
import bai.bai.bai.demo.nfc.NFCManager
import bai.bai.bai.demo.utils.HexUtil
import kotlinx.android.synthetic.main.activity_nfc_read.*
import java.io.UnsupportedEncodingException

class NfcReadActivity : AppCompatActivity() {

    private val TAG = "NfcReadActivity -> "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc_read)
        NFCManager.instance?.onCreate(this, object : NFCManager.NFCListener {
            override fun onReceiveDataOffline(isoDep: IsoDep?) {
                Log.d(TAG, "onReceiveDataOffline")
                isoDep?.let {
                    val nfcA: ByteArray? = it.historicalBytes
                    Log.d(TAG, "onReceiveDataOffline|nfcA = $nfcA")
                    nfcA?.let {
                        val nfcAStr = HexUtil.byte2hex(nfcA)
                        Log.d(TAG, "onReceiveDataOffline|nfcAStr = $nfcAStr")
                        tv_nfcA.text = "NFC-A = $nfcAStr"
                    }
                }
                isoDep?.let {
                    val nfcB: ByteArray? = it.hiLayerResponse
                    Log.d(TAG, "onReceiveDataOffline|nfcB = $nfcB")
                    nfcB?.let {
                        val nfcBStr = HexUtil.byte2hex(nfcB)
                        Log.d(TAG, "onReceiveDataOffline|nfcBStr = $nfcBStr")
                        tv_nfcB.text = "NFC-B = $nfcBStr"
                    }
                }
//                isoDep?.let {
//                    it.transceive()
//                }

            }

            override fun onError(msg: String) {
                Log.d(TAG, "onReceiveDataOffline|${msg}")
            }

        })
    }

    override fun onResume() {
        super.onResume()
        NFCManager.instance?.onResume(this)
    }

    /**
     * 读取内容
     */
    fun readFromTag(intent: Intent?): String {
        Log.d(TAG, "readFromTag|intent为空? = ${intent == null}")
        val rawArray: Array<Parcelable> = intent!!.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        val mNdefMsg: NdefMessage = rawArray[0] as NdefMessage
        val mNdefRecord: NdefRecord = mNdefMsg.records[0];
        try {
            if (mNdefRecord != null) {
                val readResult = String(mNdefRecord.payload)
                Log.d(TAG, "readFromTag|readResult = $readResult")
                return readResult;
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace();
        };
        return ""
    }

    override fun onPause() {
        super.onPause()
        NFCManager.instance?.onPause(this)
    }

    override fun onDestroy() {
        NFCManager.instance?.onDestroy()
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent")
        NFCManager.instance?.processNFCIntent(intent)
//        NFCManager.instance?.readFromTag(intent)
    }

}