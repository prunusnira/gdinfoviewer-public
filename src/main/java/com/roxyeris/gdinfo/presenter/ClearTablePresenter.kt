/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.ClearTableContract
import com.roxyeris.gdinfo.receiver.ClearTableReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class ClearTablePresenter(): ClearTableContract.Presenter {
    lateinit override var view: ClearTableContract.View
    lateinit override var gfData:Array<Array<Int>>
    lateinit override var dmData:Array<Array<Int>>

    override fun requestData(ctx:Context, userid:Int) {
        val receiver = ClearTableReceiver(gfData, dmData, view)
        Thread(ThreadRunner(getUrl(userid), receiver, ctx)).start()
    }

    fun getUrl(userid:Int): String {
        return Const.MAIN + Const.ADDR_CTABLE + "/" + userid
    }
}