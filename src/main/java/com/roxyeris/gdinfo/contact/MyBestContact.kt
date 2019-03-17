/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.MyBestData

interface MyBestContact {
    interface View {
        var presenter:Presenter

        fun updateUI(username:String, bestdata:ArrayList<MyBestData>, type:Int)
    }
    interface Presenter {
        var view:View
        var userid:Int

        fun requestData(ctx: Context, type:Int)
    }
}