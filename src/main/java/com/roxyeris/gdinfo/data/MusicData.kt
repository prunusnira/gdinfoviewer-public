/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.data

import java.util.ArrayList

class MusicData(var ptype: String?, var mname: String?,
                var composer: String?, var ver: Int, var data: ArrayList<MusicPatternData>?)
    :BaseData()
