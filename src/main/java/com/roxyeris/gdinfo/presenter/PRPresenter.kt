/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.PRContact
import com.roxyeris.gdinfo.data.PRData
import com.roxyeris.gdinfo.receiver.PRReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class PRPresenter(): PRContact.Presenter {
    lateinit override var view: PRContact.View
    lateinit override var data: ArrayList<PRData>
    override var mid: Int = 0
    override var page: Int = 0
    override var pages: Int = 0
    override var ptcode: Int = 0
    override var ver: Int = 0

    override fun requestData(ctx:Context) {
        val receiver = PRReceiver(data, view, ptcode, mid)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    fun getUrl(): String {
        return Const.MAIN + Const.ADDR_PATTERNDETAIL + "/" + mid + "/" + ptcode + "/" + page + "/" + ver
    }

}