/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.TowerDetailContact
import com.roxyeris.gdinfo.data.MusicPatternData
import com.roxyeris.gdinfo.data.TowerData
import com.roxyeris.gdinfo.data.TowerFloorData
import org.json.JSONException
import org.json.JSONObject

class TowerDetailReceiver(val view: TowerDetailContact.View):BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))

            val tData = json.getJSONObject("tower")
            val tLevels = tData.getInt("levels")
            val tName = tData.getString("name")
            val tSkill = tData.getInt("skill")

            val tower = TowerData(tName, tLevels, tSkill)

            val tList = Array(tLevels, { _ -> ArrayList<TowerFloorData>() })

            val floordata = json.getJSONArray("towerlist")
            for(i in 0..floordata.length() - 1) {
                val current = floordata.getJSONObject(i)

                val t = current.getJSONObject("tower")
                val floor = t.getInt("floor")
                val mid = t.getInt("musicid")
                val name = t.getString("mname")
                val ptcode = t.getInt("ptcode")
                val level = t.getInt("level")
                val score = t.getInt("score")
                val rate = t.getInt("rate")
                val isfc = t.getBoolean("fc")
                val combo = t.getInt("combo")
                val desc = t.getString("description")

                val isclear = current.getBoolean("clear")
                val skill = current.getJSONObject("skill")

                val skillConv = MusicPatternData(
                        0,
                        0,
                        skill.getInt("playtime"),
                        skill.getInt("cleartime"),
                        skill.getString("rank"),
                        skill.getInt("rate"),
                        skill.getInt("ratetb"),
                        skill.getInt("ratetbre"),
                        skill.getInt("ratemx"),
                        skill.getInt("score"),
                        skill.getInt("combo"),
                        0,
                        skill.getString("checkfc"),
                        ""
                )

                // 해당 층에 대응하는 리스트에 추가 (must도 고려함)
                tList[floor].add(TowerFloorData(floor, mid, name, ptcode,
                        level, score, rate, isfc, combo, desc, skillConv, isclear))
            }

            for(i in 0..tLevels - 1) {
                tower.list[i] = tList[i]
            }

            view.updateData(tower)

            // 정리된 데이터를 view로 보내서 화면에 표시함
        } catch(e:JSONException) {
            e.printStackTrace()
        }
    }
}