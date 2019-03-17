/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.content.SharedPreferences
import android.os.Message
import org.json.JSONException
import org.json.JSONObject

class SelfReceiver(val pref:SharedPreferences):BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val me = json.getJSONObject("mydata")
            val id = me.getInt("id")
            val title = me.getString("title")
            val name = me.getString("name")
            val gskill = me.getString("gskill")
            val dskill = me.getString("dskill")

            val editor = pref.edit()
            editor.putInt("numid", id)
            editor.putString("title", title)
            editor.putString("name", name)
            editor.putString("gskill", gskill)
            editor.putString("dskill", dskill)
            editor.commit()
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}