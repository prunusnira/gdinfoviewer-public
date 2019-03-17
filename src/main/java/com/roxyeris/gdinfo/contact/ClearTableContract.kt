/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context

interface ClearTableContract {
    interface View {
        var presenter:Presenter

        fun updateUI()
    }

    interface Presenter {
        var view:View
        var gfData:Array<Array<Int>>
        var dmData:Array<Array<Int>>

        fun requestData(ctx: Context, userid:Int)
    }
}