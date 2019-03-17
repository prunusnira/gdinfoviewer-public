/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.PRData

interface PRContact {
    interface View {
        var presenter:Presenter

        fun updateUI(mname:String, comp:String, pattern:String, level:Int, pages:Int)
    }
    interface Presenter {
        var view:View
        var mid:Int
        var ptcode:Int
        var page:Int
        var pages:Int
        var ver:Int
        var data:ArrayList<PRData>

        fun requestData(ctx: Context)
    }
}