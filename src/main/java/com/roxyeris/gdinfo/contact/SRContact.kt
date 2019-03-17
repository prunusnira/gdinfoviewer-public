/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.SRData

interface SRContact {
    interface View {
        var presenter :Presenter

        fun updateUI()
    }
    interface Presenter {
        var view:View

        var gtype:String
        var page:Int
        var data:ArrayList<SRData>

        fun requestData(ctx: Context, data:ArrayList<SRData>)
    }
}