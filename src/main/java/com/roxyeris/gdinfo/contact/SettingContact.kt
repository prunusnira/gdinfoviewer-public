/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.SharedPreferences
import com.roxyeris.gdinfo.tool.IntStringStructure

interface SettingContact {
    interface View {
        var presenter:Presenter
    }
    interface Presenter {
        var view:View
        var item:ArrayList<IntStringStructure>

        fun logout(pref: SharedPreferences)
        fun isLogin(pref:SharedPreferences):Boolean
        fun getBGColor(pref:SharedPreferences):String
        fun setBGColor(pref:SharedPreferences, colorStr:String)
        fun getFontColor(pref:SharedPreferences):String
        fun setFontColor(pref:SharedPreferences, colorStr:String)
        fun getFontSize(pref:SharedPreferences):Int
        fun setFontSize(pref:SharedPreferences, size:Int)
        fun getWdgType(pref: SharedPreferences):Int
        fun setWdgType(pref:SharedPreferences, type:Int)
    }
}