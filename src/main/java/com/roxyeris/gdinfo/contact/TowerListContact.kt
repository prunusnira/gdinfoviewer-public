/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context

interface TowerListContact {
    interface View {
        var presenter:Presenter

    }

    interface Presenter {
        var view:View

        fun langCheck(ctx: Context):String
        fun getEventTowerList()
    }
}