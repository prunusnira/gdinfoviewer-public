/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.tool

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.roxyeris.gdinfo.receiver.BaseRecvHandler
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

class ThreadRunner(url:String, handler: BaseRecvHandler, ctx:Context) : Runnable {
    var url = ""
    var handler : Handler? = null
    var ctx: Context? = null

    init {
        this.url = url
        this.handler = handler
        this.ctx = ctx
    }
    override fun run() {
        // Spring의 RestTemplate을 사용하여 값을 가져옴
        // 사실상 REST API화...
        val restTemplate = RestTemplate()

        // String 메시지를 message converter에 추가
        restTemplate.messageConverters.add(StringHttpMessageConverter(Charset.forName("UTF-8")))

        // HTTP GET request를 받아옴
        val result = restTemplate.getForObject(url, String::class.java)

        val bundle = Bundle()
        bundle.putString("data", result)
        val msg = Message()
        msg.data = bundle

        (ctx as Activity).runOnUiThread(object:Runnable {
            override fun run() {
                if(handler != null) // handler가 nullable이므로 체크
                    handler?.handleMessage(msg)
            }
        })
    }
}