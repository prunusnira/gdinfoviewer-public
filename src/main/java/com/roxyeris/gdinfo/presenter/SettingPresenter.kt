/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.SharedPreferences
import com.roxyeris.gdinfo.contact.SettingContact
import com.roxyeris.gdinfo.tool.IntStringStructure

class SettingPresenter:SettingContact.Presenter {
    lateinit override var view: SettingContact.View
    lateinit override var item: ArrayList<IntStringStructure>

    override fun logout(pref:SharedPreferences) {
        val edit = pref.edit();
        edit.remove("numid")
        edit.remove("title")
        edit.remove("name")
        edit.remove("gskill")
        edit.remove("dskill")
        edit.remove("googleid")
        edit.commit()
    }

    override fun isLogin(pref:SharedPreferences): Boolean {
        return pref.getString("googleid", "").equals("")
    }

    override fun getBGColor(pref:SharedPreferences):String {
        return pref.getString("wdgBgColor", "00000000")
    }

    override fun setBGColor(pref: SharedPreferences, colorStr:String) {
        val edit = pref.edit()
        edit.putString("wdgBgColor", colorStr)
        edit.commit()
    }

    override fun getFontColor(pref: SharedPreferences): String {
        return pref.getString("wdgFontColor", "FFFFFF")
    }

    override fun setFontColor(pref: SharedPreferences, colorStr: String) {
        val edit = pref.edit()
        edit.putString("wdgFontColor", colorStr)
        edit.commit()
    }

    override fun getFontSize(pref: SharedPreferences): Int {
        return pref.getInt("wdgFontSize", 20)
    }

    override fun setFontSize(pref: SharedPreferences, size: Int) {
        val edit = pref.edit()
        edit.putInt("wdgFontSize", size)
        edit.commit()
    }

    override fun getWdgType(pref: SharedPreferences): Int {
        return pref.getInt("wdgType", 0)
    }

    override fun setWdgType(pref: SharedPreferences, type: Int) {
        val edit = pref.edit()
        edit.putInt("wdgType", type)
        edit.commit()
    }
}