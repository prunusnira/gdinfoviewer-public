/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.data

class ProfileData(
        val id:Int,
        val title:String,
        val name:String,
        val comment:String,
        val gskill:String,
        val dskill:String,
        val gskilltbre:String,
        val dskilltbre:String,
        val gskilltb:String,
        val dskilltb:String,
        val gskillall:String,
        val dskillall:String,
        val gclearlv:String,
        val dclearlv:String,
        val gclearnum:String,
        val dclearnum:String,
        val gfclv:String,
        val dfclv:String,
        val gfcnum:String,
        val dfcnum:String,
        val gexclv:String,
        val dexclv:String,
        val gexcnum:String,
        val dexcnum:String,
        val opencnt:String,
        val countgf:Int,
        val countdm:Int,
        val updatetime:String
)
    :BaseData()