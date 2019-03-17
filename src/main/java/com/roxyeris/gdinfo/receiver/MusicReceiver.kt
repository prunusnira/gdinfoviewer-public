/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.data.MusicData
import com.roxyeris.gdinfo.data.MusicPatternData
import com.roxyeris.gdinfo.tool.Const
import org.json.JSONException
import org.json.JSONObject
import com.roxyeris.gdinfo.contact.MusicContact
import java.util.*

class MusicReceiver(val data:ArrayList<MusicData>, val view:MusicContact.View)
    : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))

            val music = json.getJSONObject("music")
            val mid = music.getInt("id")
            val mname = music.getString("name")
            val comp = music.getString("composer")
            val ver = music.getInt("version")
            val gbsc = music.getInt("gbsc")
            val gadv = music.getInt("gadv")
            val gext = music.getInt("gext")
            val gmas = music.getInt("gmas")
            val bbsc = music.getInt("bbsc")
            val badv = music.getInt("badv")
            val bext = music.getInt("bext")
            val bmas = music.getInt("bmas")
            val dbsc = music.getInt("dbsc")
            val dadv = music.getInt("dadv")
            val dext = music.getInt("dext")
            val dmas = music.getInt("dmas")

            // UI 정보 갱신
            val jacket = Const.ADDR_MUSIC + mid + ".jpg"
            view.updateUI(jacket, mname, comp)

            /////////////////////////////////////////
            // 데이터 정보 수집
            /////////////////////////////////////////
            val skill = json.getJSONObject("skill")

            val gdata = ArrayList<MusicPatternData>()
            val bdata = ArrayList<MusicPatternData>()
            val ddata = ArrayList<MusicPatternData>()

            // SKILL 구성: s1~s12로 각각 데이터가 들어있음
            // 비어있는 경우로 레벨정보 입력이 필요하므로 추가
            for (i in 1..12) {
                var level = 0
                when (i) {
                    1 -> level = gbsc
                    2 -> level = gadv
                    3 -> level = gext
                    4 -> level = gmas
                    5 -> level = bbsc
                    6 -> level = badv
                    7 -> level = bext
                    8 -> level = bmas
                    9 -> level = dbsc
                    10 -> level = dadv
                    11 -> level = dext
                    12 -> level = dmas
                }

                val d: MusicPatternData

                if (skill.has("s" + i.toString())) {
                    val ds = skill.getJSONObject("s" + i.toString())
                    d = MusicPatternData.getPattern(ds, level)
                } else {
                    d = MusicPatternData.getEmpty(level, i)
                }

                if (i <= 4)
                    gdata.add(d)
                else if (i >= 9)
                    ddata.add(d)
                else
                    bdata.add(d)
            }

            data.add(MusicData("g", mname, comp, ver, gdata))
            data.add(MusicData("b", mname, comp, ver, bdata))
            data.add(MusicData("d", mname, comp, ver, ddata))

            view.updateData()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}