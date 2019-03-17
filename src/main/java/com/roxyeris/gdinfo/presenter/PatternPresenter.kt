/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.PatternContact
import com.roxyeris.gdinfo.data.PatternData
import com.roxyeris.gdinfo.receiver.PatternReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class PatternPresenter():PatternContact.Presenter {
    lateinit override var view: PatternContact.View
    lateinit override var data: ArrayList<PatternData>
    override var order: String = ""
    override var ver: String = ""
    override var hot: String = ""
    override var page: Int = 0
    override var pages: Int = 0

    override fun requestData(ctx:Context) {
        val receiver = PatternReceiver(data, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    fun getUrl(): String {
        var extendUrl:String = ""
        if(hot != "") extendUrl = "?hot="+hot
        return Const.MAIN + Const.ADDR_PATTERN + "/" + ver + "/" + order + "/" + page + extendUrl
    }
}