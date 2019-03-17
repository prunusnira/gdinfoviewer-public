/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.SkillContact
import com.roxyeris.gdinfo.data.SkillData
import com.roxyeris.gdinfo.receiver.SkillReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class SkillPresenter():SkillContact.Presenter {
    lateinit override var view: SkillContact.View
    // 기본 URL 요소
    override var ptype:Int = 0
    override var userid:Int = 0
    override var gtype:String = ""
    override var page:Int = 0
    override var order:String = ""
    // extend
    override var level:Int = 0
    override var rank:Int = 0
    override var ver:String = ""
    override var hot:String = ""

    // etc
    override var pages:Int = 0
    override var target:String = ""
    lateinit override var data:ArrayList<SkillData>

    override fun requestData(ctx:Context) {
        val receiver = SkillReceiver(ctx, gtype, ptype, data, view)
        Thread(ThreadRunner(getUrl(), receiver, ctx)).start()
    }

    override fun resetExtendUrl() {
        level = 0
        rank = 0
        ver = ""
        hot = ""
    }

    fun getUrl(): String {
        // Set url
        var addr = Const.MAIN + Const.ADDR_SKILL +
                "/" + ptype + "/" + userid + "/" + gtype + "/" + page + "/" + order

        var extend = ""

        if(level > 0) {
            if(extend == "") extend += "?"
            else extend += "&"
            extend += "lv=" + level.toString()
        }

        if(rank > 0) {
            if(extend == "") extend += "?"
            else extend += "&"
            extend += "rank=" + rank.toString()
        }

        if(ver != "") {
            if(extend == "") extend += "?"
            else extend += "&"
            extend += "ver=" + ver
        }

        if(hot != "") {
            if(extend == "") extend += "?"
            else extend += "&"
            extend += "hot=" + hot
        }

        addr += extend

        return addr
    }
}