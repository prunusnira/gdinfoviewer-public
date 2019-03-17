/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.SRContact
import com.roxyeris.gdinfo.data.SRData
import org.json.JSONException
import org.json.JSONObject

class SRReceiver(val data:ArrayList<SRData>, val view:SRContact.View) : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val rankarr = json.getJSONArray("allUserList")
            val size = rankarr.length()

            for (i in 0 until size) {
                val cur = rankarr.getJSONObject(i)
                val ruid = cur.getInt("id")
                val runame = cur.getString("name")
                val gskill = java.lang.Float.valueOf(cur.getString("gskill"))!!
                val dskill = java.lang.Float.valueOf(cur.getString("dskill"))!!
                val gskillall = java.lang.Float.valueOf(cur.getString("gskillall"))!!
                val dskillall = java.lang.Float.valueOf(cur.getString("dskillall"))!!

                data.add(SRData(ruid, runame, gskill, dskill, gskillall, dskillall))
            }

            view.updateUI()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}