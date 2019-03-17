/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.SRContact
import com.roxyeris.gdinfo.data.SRData
import com.roxyeris.gdinfo.receiver.SRReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class SRPresenter(): SRContact.Presenter {
    lateinit override var data: ArrayList<SRData>
    lateinit override var view: SRContact.View
    override var gtype: String = ""
    override var page: Int = 0

    override fun requestData(ctx:Context, data:ArrayList<SRData>) {
        val receiver = SRReceiver(data, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    fun getUrl(): String {
        return Const.MAIN + Const.ADDR_RANK + "/" + gtype + "/" + page
    }

}