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
import com.roxyeris.gdinfo.contact.MyBestContact
import com.roxyeris.gdinfo.receiver.MyBestReceiver

class MyBestPresenter(): MyBestContact.Presenter {
    lateinit override var view: MyBestContact.View
    override var userid: Int = 0

    override fun requestData(ctx:Context, type:Int) {
        val receiver = MyBestReceiver(type, view)
        Thread(ThreadRunner(getUrl(userid), receiver, ctx)).start()
    }

    fun getUrl(userid:Int): String {
        return Const.MAIN + Const.ADDR_MYBEST + "/" + userid
    }
}