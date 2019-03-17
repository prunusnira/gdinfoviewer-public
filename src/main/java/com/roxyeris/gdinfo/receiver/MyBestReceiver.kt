/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.MyBestContact
import com.roxyeris.gdinfo.data.MyBestData
import com.roxyeris.gdinfo.tool.Converter
import org.json.JSONException
import org.json.JSONObject

class MyBestReceiver(val type:Int, val view:MyBestContact.View) : BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val self = json.getJSONObject("p")
            val username = self.getString("name")

            var data = when(type) {
                0 -> json.getJSONArray("mybestm")
                1 -> json.getJSONArray("mybestp")
                2 -> json.getJSONArray("mybestpg")
                3 -> json.getJSONArray("mybestpd")
                else -> null
            }

            val bestdata = ArrayList<MyBestData>()
            for(i in 0 until data!!.length()) {
                val cur = data.getJSONObject(i)
                val mid = cur.getInt("id")
                val mname = cur.getString("name")
                val cnt = cur.getInt("count")
                var pat = ""
                if(type > 0)
                    pat = Converter.ptcodeConvert(cur.getInt("pattern"))

                bestdata.add(MyBestData(mid, mname, pat, cnt))
            }

            view.updateUI(username, bestdata, type)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}