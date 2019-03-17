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
import com.roxyeris.gdinfo.contact.LoginContact
import org.json.JSONException
import org.json.JSONObject

class LoginReceiver(val ctx:Context, val view:LoginContact.View): BaseRecvHandler() {
    override fun handleMessage(msg: Message) {
        try {
            val json = JSONObject(msg.data.getString("data"))
            val me = json.getJSONObject("mydata")
            view.updateLoginValue(me)
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }
}