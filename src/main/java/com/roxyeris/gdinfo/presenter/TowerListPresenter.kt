/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.Context
import android.os.Build
import com.roxyeris.gdinfo.contact.TowerListContact

class TowerListPresenter: TowerListContact.Presenter {
    override lateinit var view: TowerListContact.View

    override fun langCheck(ctx: Context):String {
        // 안드로이드 시스템 언어를 체크하여 버튼 이미지 변경
        var locale:String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = ctx.resources.configuration.locales[0].language
        } else {
            locale = ctx.resources.configuration.locale.language
        }
        return locale
    }
    override fun getEventTowerList() {
        // DB에서 타워 이름 가져오기... 이거 나중에 서버쪽도 해야할 거 같은데
    }
}