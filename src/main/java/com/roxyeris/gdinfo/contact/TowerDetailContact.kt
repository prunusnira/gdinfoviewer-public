/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.Context
import com.roxyeris.gdinfo.data.TowerData

interface TowerDetailContact {
    interface View {
        var presenter: Presenter

        fun updateData(tower:TowerData)
    }

    interface Presenter {
        var view: View
        var tower: Int

        fun requestData(ctx: Context, numid: Int)
    }
}