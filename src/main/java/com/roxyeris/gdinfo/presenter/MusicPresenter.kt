/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner
import android.content.Context
import com.roxyeris.gdinfo.contact.MusicContact
import com.roxyeris.gdinfo.data.MusicData
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.receiver.MusicReceiver
import com.roxyeris.gdinfo.receiver.RecentOneUserReceiver

class MusicPresenter(): MusicContact.Presenter {
    lateinit override var data: ArrayList<MusicData>
    lateinit override var userdata: RecentData
    lateinit override var view: MusicContact.View
    override var mid: Int = 0
    override var uid: Int = 0

    override fun requestData(ctx:Context) {
        val receiver = MusicReceiver(data, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    override fun requestUserData(ctx: Context) {
        val receiver = RecentOneUserReceiver(userdata, view)
        Thread(ThreadRunner(getUserUrl(), receiver, ctx)).start()
    }

    fun getUserUrl(): String {
        return Const.MAIN + Const.ADDR_USER_WITH_ID + "/" + uid
    }

    fun getUrl(): String {
        return Const.MAIN + Const.ADDR_MUSICPAGE + "/" + mid + "/" + uid
    }
}