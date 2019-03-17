/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.data

import org.json.JSONObject

class MusicPatternData(var level: Int, var ptcode: Int, var playtime: Int, var cleartime: Int,
                       var rank: String?, var rate: Int, var ratetb: Int, var ratetbre: Int, var ratemx: Int,
                       var score: Int, var combo: Int, var skill: Int,
                       var fc: String?, var meter: String?)
    :BaseData() {
    companion object {
        fun getPattern(ds: JSONObject, level: Int): MusicPatternData {
            val ptcode = ds.getInt("patterncode")
            val playtime = ds.getInt("playtime")
            val cleartime = ds.getInt("cleartime")
            val rank = ds.getString("rank")
            val rate = ds.getInt("rate")
            val ratetb = ds.getInt("ratetb")
            val ratetbre = ds.getInt("ratetbre")
            val ratemx = ds.getInt("ratemx")
            val score = ds.getInt("score")
            val combo = ds.getInt("combo")
            val skill = ds.getInt("skill")
            val fc = ds.getString("checkfc")
            val meter = ds.getString("meter")

            return MusicPatternData(level, ptcode, playtime, cleartime, rank,
                    rate, ratetb, ratetbre, ratemx,
                    score, combo, skill, fc, meter)
        }

        fun getEmpty(level: Int, ptcode: Int): MusicPatternData {
            return MusicPatternData(level, ptcode, 0, 0, "none",
                    0, 0, 0, 0,
                    0, 0, 0, "N", "")
        }
    }
}
