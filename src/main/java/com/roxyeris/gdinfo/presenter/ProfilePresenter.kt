/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.ProfileContact
import com.roxyeris.gdinfo.receiver.ProfileReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class ProfilePresenter(): ProfileContact.Presenter {
    lateinit override var view: ProfileContact.View
    override var userid: Int = 0

    override fun requestData(ctx:Context) {
        val receiver = ProfileReceiver(ctx, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    fun getUrl(): String {
        return Const.MAIN + Const.ADDR_USER_WITH_ID + "/" + userid
    }

}