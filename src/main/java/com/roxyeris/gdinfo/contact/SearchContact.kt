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
import com.roxyeris.gdinfo.data.RecentData

interface SearchContact {
    interface View {
        var presenter:Presenter

        fun updateUI()
    }
    interface Presenter {
        var view:View
        var ptdata:ArrayList<PatternData>
        var userdata:ArrayList<RecentData>
        var type:Int
        var page:Int
        var searchtxt:String

        fun requestData(ctx:Context, type:Int)
    }
}