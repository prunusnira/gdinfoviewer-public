/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.SkillData

interface SkillContact {
    interface View {
        var presenter:Presenter

        fun updateUI(pages:Int)
    }
    interface Presenter {
        var view:View
        // 기본 URL 요소
        var ptype:Int
        var userid:Int
        var gtype:String
        var page:Int
        var order:String
        // extend
        var level:Int
        var rank:Int
        var ver:String
        var hot:String

        // etc
        var pages:Int
        var target:String
        var data:ArrayList<SkillData>

        fun requestData(ctx: Context)
        fun resetExtendUrl()
    }
}