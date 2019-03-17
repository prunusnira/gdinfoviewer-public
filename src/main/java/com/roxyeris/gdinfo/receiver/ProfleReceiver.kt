/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.content.Context
import android.os.Message
import com.roxyeris.gdinfo.contact.ProfileContact
import com.roxyeris.gdinfo.data.ProfileData
import org.json.JSONException
import org.json.JSONObject

class ProfileReceiver(val ctx:Context, val view:ProfileContact.View): BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val profile = json.getJSONObject("mydata")

            // 상세 프로필 정보
            val userid = profile.getInt("id")
            val title = profile.getString("title")
            val name = profile.getString("name")
            val comment = profile.getString("comment")
            val gskill = profile.getString("gskill")
            val dskill = profile.getString("dskill")
            val gskilltbre = profile.getString("gskilltbre")
            val dskilltbre = profile.getString("dskilltbre")
            val gskilltb = profile.getString("gskilltb")
            val dskilltb = profile.getString("dskilltb")
            val gskillall = profile.getString("gskillall")
            val dskillall = profile.getString("dskillall")
            val gclearlv = profile.getString("gclearlv")
            val dclearlv = profile.getString("dclearlv")
            val gclearnum = profile.getString("gclearnum")
            val dclearnum = profile.getString("dclearnum")
            val gfclv = profile.getString("gfclv")
            val dfclv = profile.getString("dfclv")
            val gfcnum = profile.getString("gfcnum")
            val dfcnum = profile.getString("dfcnum")
            val gexclv = profile.getString("gexclv")
            val dexclv = profile.getString("dexclv")
            val gexcnum = profile.getString("gexcnum")
            val dexcnum = profile.getString("dexcnum")
            val opencnt = profile.getString("opencount")
            val countgf = profile.getInt("countgf")
            val countdm = profile.getInt("countdm")
            val updatetime = profile.getString("updatetime")

            val profileData = ProfileData(
                    userid, title, name, comment, gskill, dskill, gskilltbre, dskilltbre,
                    gskilltb, dskilltb, gskillall, dskillall, gclearlv, dclearlv,
                    gclearnum, dclearnum, gfclv, dfclv, gfcnum, dfcnum, gexclv, dexclv,
                    gexcnum, dexcnum, opencnt, countgf, countdm, updatetime
            )
            view.updateUI(profileData)
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }
}