/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.MusicContact
import com.roxyeris.gdinfo.data.RecentData
import org.json.JSONException
import org.json.JSONObject

class RecentOneUserReceiver(val userdata:RecentData, val view:MusicContact.View): BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val rec = json.getJSONObject("mydata")
            val uid = rec.getInt("id")
            val uname = rec.getString("name")
            val gskill = rec.getString("gskill")
            val dskill = rec.getString("dskill")
            val date = rec.getString("updatetime")

            userdata.id = uid
            userdata.name = uname
            userdata.gf = gskill
            userdata.dm = dskill
            userdata.date = date

            view.updateUserData()
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }
}