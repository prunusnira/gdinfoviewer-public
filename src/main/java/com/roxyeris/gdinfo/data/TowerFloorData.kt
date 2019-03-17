/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.data

class TowerFloorData(
        var floor: Int,
        var musicid: Int,
        var mname: String,
        var ptcode: Int,
        var level: Int,
        var score: Int,
        var rate: Int,
        var fc: Boolean,
        var combo: Int,
        var desc: String,
        var skill: MusicPatternData,
        var clear: Boolean
):BaseData()