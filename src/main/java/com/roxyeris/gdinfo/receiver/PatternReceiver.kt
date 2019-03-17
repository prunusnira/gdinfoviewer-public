/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.PatternContact
import com.roxyeris.gdinfo.data.PatternData
import org.json.JSONException
import org.json.JSONObject

class PatternReceiver(val data:ArrayList<PatternData>, val view:PatternContact.View) : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val musicarr = json.getJSONArray("musiclist")
            val size = musicarr.length()
            val pages = json.getInt("pages")

            for (i in 0 until size) {
                val cur = musicarr.getJSONObject(i)
                val mid = cur.getInt("id")
                val mname = cur.getString("name")
                val gbsc = cur.getInt("gbsc")
                val gadv = cur.getInt("gadv")
                val gext = cur.getInt("gext")
                val gmas = cur.getInt("gmas")
                val bbsc = cur.getInt("bbsc")
                val badv = cur.getInt("badv")
                val bext = cur.getInt("bext")
                val bmas = cur.getInt("bmas")
                val dbsc = cur.getInt("dbsc")
                val dadv = cur.getInt("dadv")
                val dext = cur.getInt("dext")
                val dmas = cur.getInt("dmas")

                data.add(PatternData(mid, mname, gbsc, gadv, gext, gmas,
                        bbsc, badv, bext, bmas, dbsc, dadv, dext, dmas))
            }

            view.updateUI(pages)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}