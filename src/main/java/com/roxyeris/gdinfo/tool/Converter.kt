/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.tool

object Converter {
    fun ptcodeConvert(ptcode: Int): String {
        var rtn = ""
        when (ptcode) {
            1 -> rtn = "BAS-G"
            2 -> rtn = "ADV-G"
            3 -> rtn = "EXT-G"
            4 -> rtn = "MAS-G"
            5 -> rtn = "BAS-B"
            6 -> rtn = "ADV-B"
            7 -> rtn = "EXT-B"
            8 -> rtn = "MAS-B"
            9 -> rtn = "BAS-D"
            10 -> rtn = "ADV-D"
            11 -> rtn = "EXT-D"
            12 -> rtn = "MAS-D"
        }

        return rtn
    }

    fun verConvert(ver: Int): String {
        var rtn = "GFDM"
        when (ver) {
            1 -> rtn = "GF1"
            2 -> rtn = "GF2DM1"
            3 -> rtn = "GF3DM2"
            4 -> rtn = "GF4DM3"
            5 -> rtn = "GF5DM4"
            6 -> rtn = "GF6DM5"
            7 -> rtn = "GF7DM6"
            8 -> rtn = "GF8DM7"
            9 -> rtn = "GF9DM8"
            10 -> rtn = "GF10DM9"
            11 -> rtn = "GF11DM10"
            12 -> rtn = "eeMall"
            13 -> rtn = "V"
            14 -> rtn = "V2"
            15 -> rtn = "V3"
            16 -> rtn = "V4"
            17 -> rtn = "V5"
            18 -> rtn = "V6"
            19 -> rtn = "XG"
            20 -> rtn = "XG2"
            21 -> rtn = "XG3"
            22 -> rtn = "GD"
            23 -> rtn = "GD OD"
            24 -> rtn = "GD TB"
            25 -> rtn = "GD TBRE"
            26 -> rtn = "GD MX"
            27 -> rtn = "GD EXC"
        }

        return rtn
    }
}
