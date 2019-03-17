/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.MusicData
import com.roxyeris.gdinfo.data.RecentData

interface MusicContact {
    interface View {
        var presenter:Presenter

        fun updateUI(jacketUrl:String, mname:String, comp:String)
        fun updateData()
        fun updateUserData()
    }
    interface Presenter {
        var view:View
        var data:ArrayList<MusicData>
        var userdata:RecentData
        var mid:Int
        var uid:Int

        fun requestUserData(ctx: Context)
        fun requestData(ctx: Context)
    }
}