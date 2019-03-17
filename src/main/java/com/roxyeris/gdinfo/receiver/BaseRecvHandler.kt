/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Handler
import android.os.Message

abstract class BaseRecvHandler() : Handler() {
    override fun handleMessage(msg: Message) {
        TODO("구현하는 곳에서 내부 내용 처리")
    }
}
