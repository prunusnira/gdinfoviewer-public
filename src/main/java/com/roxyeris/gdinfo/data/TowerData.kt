/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.data

class TowerData(
        var name: String,
        var floor: Int,
        var skill: Int
): BaseData() {
    var list = HashMap<Int, ArrayList<TowerFloorData>>()
}