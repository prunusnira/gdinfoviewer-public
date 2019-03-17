/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.adapter.RecentAdapter
import com.roxyeris.gdinfo.data.RecentData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecentReceiver(val list:ArrayList<RecentData>, val adt: RecentAdapter): BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val rec = json.getString("recent")

            // recent data
            val recentArray = JSONArray(rec)
            for(i in 0..recentArray.length() - 1) {
                val obj = recentArray.getJSONObject(i)
                // 항목별 분해
                val id = obj.getInt("id")
                val towertitle = obj.getString("titletower")
                val name = obj.getString("name")
                val gskill = obj.getString("gskill")
                val dskill = obj.getString("dskill")
                val uptime = obj.getString("updatetime")

                list.add(RecentData(id, towertitle, name, gskill, dskill, uptime))
            }
            adt.notifyDataSetChanged()
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }
}