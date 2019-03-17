/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.app.Activity
import android.content.Context
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.SkillContact
import com.roxyeris.gdinfo.data.SkillData
import com.roxyeris.gdinfo.tool.Const
import kotlinx.android.synthetic.main.lo_skill.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Long.parseLong
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SkillReceiver(val ctx:Context, val gtype:String, val ptype:Int,
                    val data:ArrayList<SkillData>, val view:SkillContact.View) : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))

            val pages = json.getInt("pages")

            // 유저정보
            val userjson = json.getJSONObject("user")
            val name = userjson.getString("name")
            val gskill = userjson.getString("gskill")
            val dskill = userjson.getString("dskill")
            val utime = userjson.getString("updatetime")
            val utimeLong = Date(parseLong(utime))
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val updatetime = formatter.format(utimeLong)

            val act = ctx as Activity

            when(gtype) {
                "gf" -> {
                    (ctx as AppCompatActivity).supportActionBar!!.setSubtitle("GF "+ctx.getString(R.string.skill_title) + " "  + name)
                    act.tv_skill_title.text = "GF Skill"
                    act.tv_skill_value.text = gskill
                    Const.horizontalColorSetter(gskill.toFloat(), act.tv_skill_value)
                }
                "dm" -> {
                    (ctx as AppCompatActivity).supportActionBar!!.setSubtitle("DM "+ctx.getString(R.string.skill_title) + " "  + name)
                    act.tv_skill_title.text = "DM Skill"
                    act.tv_skill_value.text = dskill
                    Const.horizontalColorSetter(dskill.toFloat(), act.tv_skill_value)
                }
            }
            act.tv_skill_updatetime.text = updatetime.toString()

            val array = json.getJSONArray("skill")
            for(i in 0..array.length() - 1) {
                val cur = array.getJSONObject(i)

                val rate = when(ptype) {
                    0, 1, 2 -> cur.getInt("rate")
                    3, 5 -> cur.getInt("ratetbre")
                    4, 6 -> cur.getInt("ratetb")
                    else -> 0
                }

                val lv = cur.getInt("level")

                // 스킬 데이터 처리
                // 순서대로 musicid, mname, ishot, ptcode, rank, rate, ratetbre, ratetb, ver,
                // skill, playtime, level, checkfc
                val skillData = SkillData(cur.getInt("musicid"), cur.getString("mname"),
                        cur.getString("ishot"), cur.getInt("patterncode"),
                        cur.getString("rank"), rate,  cur.getInt("version"), rate*lv*20,
                        cur.getInt("playtime"), lv, cur.getString("checkfc"))
                data.add(skillData)
            }
            view.updateUI(pages)
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
        catch(e: ParseException) {
            e.printStackTrace()
        }
    }
}