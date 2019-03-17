/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.PRContact
import com.roxyeris.gdinfo.data.PRData
import org.json.JSONException
import org.json.JSONObject

class PRReceiver(val data:ArrayList<PRData>, val view:PRContact.View,
                 val ptcode:Int, val mid:Int) : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val music = json.getJSONObject("music")
            var pages = json.getInt("pages")

            val mname = music.getString("name")
            val comp = music.getString("composer")
            var level = 0
            var pattern = ""
            when (ptcode) {
                1 -> {
                    level = music.getInt("gbsc")
                    pattern = "BAS-G"
                }
                2 -> {
                    level = music.getInt("gadv")
                    pattern = "ADV-G"
                }
                3 -> {
                    level = music.getInt("gext")
                    pattern = "EXT-G"
                }
                4 -> {
                    level = music.getInt("gmas")
                    pattern = "MAS-G"
                }
                5 -> {
                    level = music.getInt("bbsc")
                    pattern = "BAS-B"
                }
                6 -> {
                    level = music.getInt("badv")
                    pattern = "ADV-B"
                }
                7 -> {
                    level = music.getInt("bext")
                    pattern = "EXT-B"
                }
                8 -> {
                    level = music.getInt("bmas")
                    pattern = "MAS-B"
                }
                9 -> {
                    level = music.getInt("dbsc")
                    pattern = "BAS-D"
                }
                10 -> {
                    level = music.getInt("dadv")
                    pattern = "ADV-D"
                }
                11 -> {
                    level = music.getInt("dext")
                    pattern = "EXT-D"
                }
                12 -> {
                    level = music.getInt("dmas")
                    pattern = "MAS-D"
                }
            }

            val ptarr = json.getJSONArray("list")
            val size = ptarr.length()

            for (i in 0 until size) {
                val cur = ptarr.getJSONObject(i)
                val userid = cur.getInt("userid")
                val name = cur.getString("name")
                val rate = cur.getInt("rate")
                val checkfc = cur.getString("checkfc")
                val rank = cur.getString("rank")
                val skill = Math.floor((rate * level * 20 / 10000).toDouble()).toInt();

                data.add(PRData(userid, name, skill, rate, checkfc, rank))
            }
            view.updateUI(mname, comp, pattern, level, pages)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}