/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import android.content.SharedPreferences
import com.roxyeris.gdinfo.adapter.RecentAdapter
import com.roxyeris.gdinfo.contact.RecentContact
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.receiver.RecentReceiver
import com.roxyeris.gdinfo.receiver.SelfReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class RecentPresenter(): RecentContact.Presenter {
    lateinit override var view: RecentContact.View
    lateinit override var data: ArrayList<RecentData>

    override fun requestData(ctx: Context, adapter:RecentAdapter) {
        val receiver = RecentReceiver(data, adapter)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    override fun requestSelf(ctx:Context, pref:SharedPreferences) {
        val receiver = SelfReceiver(pref)
        Thread(ThreadRunner(getUrlSelf(pref), receiver, ctx)).start()
    }

    fun getUrl(): String {
        return Const.MAIN + Const.ADDR_INDEX
    }

    fun getUrlSelf(pref:SharedPreferences): String {
        return Const.MAIN + Const.ADDR_USER_WITH_ID + "/" + pref.getInt("numid", -1)
    }
}