/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.SearchContact
import com.roxyeris.gdinfo.data.PatternData
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.receiver.SearchReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class SearchPresenter():SearchContact.Presenter {
    lateinit override var view: SearchContact.View
    lateinit override var ptdata: ArrayList<PatternData>
    lateinit override var userdata: ArrayList<RecentData>
    override var type: Int = 0
    override var page: Int = 0
    override var searchtxt: String = ""

    override fun requestData(ctx:Context, type:Int) {
        this.type = type
        val receiver = SearchReceiver(ptdata, userdata, type, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    fun getUrl(): String {
        var ext = "/"

        when(type) {
            0 -> ext += "music/"
            1 -> ext += "gskill/"
            2 -> ext += "dskill/"
            3 -> ext += "name/"
        }

        page = 1

        return Const.MAIN + Const.ADDR_SEARCH + ext + searchtxt + "/" + page
    }
}