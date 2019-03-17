/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.PatternData

interface PatternContact {
    interface View {
        var presenter:Presenter

        fun updateUI(pages: Int)
    }
    interface Presenter {
        var view:View
        var ver:String
        var order:String
        var page:Int
        var pages:Int
        var hot:String
        var data:ArrayList<PatternData>

        fun requestData(ctx: Context)
    }
}