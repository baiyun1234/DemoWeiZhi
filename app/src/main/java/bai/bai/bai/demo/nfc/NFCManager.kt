package bai.bai.bai.demo.nfc

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareClassic
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import java.io.UnsupportedEncodingException

class NFCManager {

    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    private var mFilters: Array<IntentFilter>? = null
    private var mTechLists: Array<Array<String>>? = null
    private var mNFCListener: NFCListener? = null
    private var mActivity: Activity? = null
    private var mIsoDep: IsoDep? = null
    private var isWorking = false

    val isEnabled: Boolean
        get() = mNfcAdapter != null && mNfcAdapter!!.isEnabled


    companion object {
        private const val TAG = "NFCManager"
        var instance: NFCManager? = null
            get() {
                if (field == null) field = NFCManager()
                return field
            }
            private set
    }

    fun onCreate(context: Context, listener: NFCListener): NFCManager {
        Log.d(TAG, "onCreate")
        try {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(context)
            // 判断2
            if (mNfcAdapter == null) {
                // 如果手机不支持NFC，或者NFC没有打开就直接返回
                Log.d(TAG, "手机不支持NFC功能！")
                listener.onError("手机不支持NFC功能")
                return this
            }
            Log.d(TAG, "手机支持NFC功能")

            // 三种Activity NDEF_DISCOVERED ,TECH_DISCOVERED,TAG_DISCOVERED
            // 指明的先后顺序非常重要， 当Android设备检测到有NFC Tag靠近时，会根据Action申明的顺序给对应的Activity
            // 发送含NFC消息的 Intent.
            val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
            val tech = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)
            val tag = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
            ndef.addDataType("*/*")
            mFilters = arrayOf(ndef, tech, tag)
            mTechLists = arrayOf(arrayOf(Ndef::class.java.name, MifareClassic::class.java.name, NfcA::class.java.name))
            if (!mNfcAdapter!!.isEnabled) {
                Log.d(TAG, "手机NFC功能没有打开！")
                listener.onError("手机NFC功能没有打开")
//                enableDialog(context)
            } else {
                Log.d(TAG, "手机NFC功能已打开")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onError(e.message!!)
            mNfcAdapter = null
        }
        mNFCListener = listener
        return this
    }

    private fun enableDialog(context: Context) {
        val ab = AlertDialog.Builder(context)
        ab.setTitle("提醒")
        ab.setMessage("手机NFC开关未打开，是否现在去打开？")
        ab.setNeutralButton("否") { dialog, which -> dialog.dismiss() }
        ab.setNegativeButton("是") { dialog, which ->
            context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            dialog.dismiss()
        }
        ab.create().show()
    }

    fun onPause(activity: Activity?) {
        if (mNfcAdapter != null) {
            //恢复默认状态，对应onResume
            Log.d(TAG, "onPause|disableForegroundDispatch")
            mNfcAdapter!!.disableForegroundDispatch(activity)
        }
        mActivity = null
    }

    fun onResume(activity: Activity) {
        Log.d(TAG, "onResume")
        if (mNfcAdapter != null) {
            Log.d(TAG, "onResume|手机支持NFC功能")
            if (mActivity == null) {
                Log.d(TAG, "onResume|mActivity == null")
                mActivity = activity
                mPendingIntent = PendingIntent.getActivity(mActivity, 0, Intent(mActivity, mActivity!!.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
                //设置处理优于所有其他NFC的处理
                mNfcAdapter!!.enableForegroundDispatch(activity, mPendingIntent, mFilters, mTechLists)
            } else {
                Log.d(TAG, "onResume|mActivity != null")
                if (mActivity!!.javaClass != activity.javaClass) {
                    Log.d(TAG, "onResume|mActivity!!.javaClass != activity.javaClass")
                    mActivity = activity
                    mPendingIntent = PendingIntent.getActivity(mActivity, 0, Intent(mActivity, mActivity!!.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
                    mNfcAdapter!!.enableForegroundDispatch(activity, mPendingIntent, mFilters, mTechLists)
                }
            }
        } else {
            Log.d(TAG, "onResume|手机不支持NFC功能")
        }

    }

    fun onDestroy() {
        mNFCListener = null
        mActivity = null
    }

    /**
     * 读取内容(需要卡芯片是NEDF类型的，否则返回为空)
     */
    fun readFromTag(intent: Intent): String {
        Log.d(TAG, "readFromTag|intent为空? = ${intent == null}")
        val rawArray: Array<Parcelable> = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
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

    /**
     * 读取内容（卡片类型是IsoDep，常见是公交卡）
     */
    fun processNFCIntent(intent: Intent) {
        Log.d(TAG, "processNFCIntent")
        try {
            if (isWorking) {
                Log.d(TAG, "processNFCIntent|读取太快，正在处理前一个任务，请稍等")
                return
            }
            if (isEnabled) {
                //获取tag对象, 可打印出来tag的数据类型，可参看网络文档：https://blog.csdn.net/qq_36135335/article/details/82463179
                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                Log.d(TAG, "processNFCIntent|tag = $tag")//公交卡是android.nfc.tech.IsoDep，所以下面要用IsoDep解析
                mIsoDep = IsoDep.get(tag)
                if (mIsoDep != null) {
                    mIsoDep!!.timeout = 2000
                    isWorking = true
                    Log.d(TAG, "processNFCIntent|开始连接 IsoDep")
                    mIsoDep!!.connect()
                    if (mNFCListener != null) {
                        mNFCListener!!.onReceiveDataOffline(mIsoDep)
                    }
                } else {
                    Log.d(TAG, "processNFCIntent|The IsoDep is null!")
                }
                isWorking = false
                Log.d(TAG, "processNFCIntent|work end")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            isWorking = false
        } finally {
            mIsoDep?.close()
        }
    }

    /**
     * 将字节数组转换为字符串
     */
    fun ByteArrayToHexString(inarray: ByteArray): String? {
        var i: Int
        var j: Int
        var `in`: Int
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var out = ""
        j = 0
        while (j < inarray.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }

    interface NFCListener {

        fun onReceiveDataOffline(isoDep: IsoDep?)

        fun onError(msg: String)

    }

}