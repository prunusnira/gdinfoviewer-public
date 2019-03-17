/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import android.content.SharedPreferences
import com.roxyeris.gdinfo.adapter.RecentAdapter
import com.roxyeris.gdinfo.data.RecentData

interface RecentContact {
    interface View {
        var presenter: Presenter
    }

    interface Presenter {
        var view:View
        var data:ArrayList<RecentData>

        fun requestData(ctx:Context, adapter: RecentAdapter)
        fun requestSelf(ctx:Context, pref: SharedPreferences)
    }
}