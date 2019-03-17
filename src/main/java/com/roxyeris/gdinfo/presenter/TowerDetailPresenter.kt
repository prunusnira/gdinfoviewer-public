/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import com.roxyeris.gdinfo.contact.TowerDetailContact
import com.roxyeris.gdinfo.receiver.TowerDetailReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class TowerDetailPresenter: TowerDetailContact.Presenter {
    lateinit override var view: TowerDetailContact.View
    override var tower: Int = -1

    override fun requestData(ctx: Context, numid: Int) {
        val receiver = TowerDetailReceiver(view)
        Thread(ThreadRunner(getUrl(numid), receiver, ctx)).start()
    }

    fun getUrl(numid: Int):String {
        var type:String = "null"
        when(tower) {
            Const.TOWER_TYPE.ALTER.value -> type = "towerGfAlter"
            Const.TOWER_TYPE.CHORD.value -> type = "towerGfChord"
            Const.TOWER_TYPE.MIXED.value -> type = "towerGfMixed"
            Const.TOWER_TYPE.GFFC.value -> type = "towerGfFc"
            Const.TOWER_TYPE.DKDK.value -> type = "towerDmDKDK"
            Const.TOWER_TYPE.LEFT.value -> type = "towerDmLeftPedal"
            Const.TOWER_TYPE.NOTE.value -> type = "towerDmNote"
            Const.TOWER_TYPE.DMFC.value -> type = "towerDmFc"
            Const.TOWER_TYPE.SPMODELDD.value -> type = "towerSpModelDD"
        }

        return Const.MAIN + Const.ADDR_TOWER + "/" + type + "/" + numid
    }
}