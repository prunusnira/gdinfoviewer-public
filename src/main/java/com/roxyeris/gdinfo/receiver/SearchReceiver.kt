/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.SearchContact
import com.roxyeris.gdinfo.data.PatternData
import com.roxyeris.gdinfo.data.RecentData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SearchReceiver(val ptdata:ArrayList<PatternData>, val userdata:ArrayList<RecentData>,
                     val type:Int, val view:SearchContact.View)
    : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        ptdata.clear()
        userdata.clear()
        when(type) {
            0 -> {
                try {
                    val json = JSONObject(msg.data.getString("data"))
                    val mdata = json.getString("userList")
                    val musicarr = JSONArray(mdata)
                    val size = musicarr.length()

                    for(i in 0..size - 1) {
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

                        ptdata.add(PatternData(mid, mname, gbsc, gadv, gext, gmas,
                                bbsc, badv, bext, bmas, dbsc, dadv, dext, dmas))
                    }
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
            }
            else -> {
                try {
                    val json = JSONObject(msg.data.getString("data"))
                    val udata = json.getString("userList")
                    val user = JSONArray(udata)

                    // 최근 데이터 분해
                    val size = user.length()
                    for(i in 0..size - 1) {
                        val obj = user.getJSONObject(i)
                        val id = obj.getInt("id")
                        val title = obj.getString("titletower")
                        val name = obj.getString("name")
                        val gskill = obj.getString("gskill")
                        val dskill = obj.getString("dskill")
                        val uptime = obj.getString("updatetime")

                        userdata.add(RecentData(id, title, name, gskill, dskill, uptime))
                    }
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        view.updateUI()
    }
}