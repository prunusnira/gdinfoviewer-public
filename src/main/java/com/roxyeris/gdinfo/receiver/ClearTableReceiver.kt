/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.receiver

import android.os.Message
import com.roxyeris.gdinfo.contact.ClearTableContract
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ClearTableReceiver(val garr:Array<Array<Int>>,
                         val darr:Array<Array<Int>>,
                         val view:ClearTableContract.View)
    : BaseRecvHandler() {
    override fun handleMessage(msg: Message) = try {
        val json = JSONObject(msg.data.getString("data"))
        val ctable = json.getJSONObject("ctable")
        val totalgf = ctable.getJSONArray("totalPatternCountGF")
        val totaldm = ctable.getJSONArray("totalPatternCountDM")
        val countgf = ctable.getJSONObject("patternCountGF")
        val countdm = ctable.getJSONObject("patternCountDM")

        val gf = arrayOfNulls<JSONArray>(7)
        val dm = arrayOfNulls<JSONArray>(7)

        // Array 생성과 동시에 초기화
        val gfcnt = IntArray(7, {_ -> 0})
        val dmcnt = IntArray(7, {_ -> 0})

        gf[0] = countgf.getJSONArray("EXC")
        gf[1] = countgf.getJSONArray("SS")
        gf[2] = countgf.getJSONArray("S")
        gf[3] = countgf.getJSONArray("A")
        gf[4] = countgf.getJSONArray("B")
        gf[5] = countgf.getJSONArray("C")
        gf[6] = countgf.getJSONArray("F")
        dm[0] = countdm.getJSONArray("EXC")
        dm[1] = countdm.getJSONArray("SS")
        dm[2] = countdm.getJSONArray("S")
        dm[3] = countdm.getJSONArray("A")
        dm[4] = countdm.getJSONArray("B")
        dm[5] = countdm.getJSONArray("C")
        dm[6] = countdm.getJSONArray("F")

        for(i in 0..6) {
            for (j in 0..17) {
                garr[i][j] = gf[i]!!.getInt(j)
                darr[i][j] = dm[i]!!.getInt(j)
            }
        }

        for(i in 0..17) {
            garr[7][i] = totalgf.getInt(i)
            garr[8][i] = totalgf.getInt(i) - gf[0]!!.getInt(i) - gf[1]!!.getInt(i)
                    - gf[2]!!.getInt(i) - gf[3]!!.getInt(i) - gf[4]!!.getInt(i)
                    - gf[5]!!.getInt(i)

            darr[7][i] = totaldm.getInt(i)
            darr[8][i] = totaldm.getInt(i) - dm[0]!!.getInt(i) - dm[1]!!.getInt(i)
                    - dm[2]!!.getInt(i) - dm[3]!!.getInt(i) - dm[4]!!.getInt(i)
                    - dm[5]!!.getInt(i)
        }

        for(i in 0..6) {
            for (j in 0..17) {
                gfcnt[i] += gf[i]!!.getInt(j)
                dmcnt[i] += dm[i]!!.getInt(j)
            }
            garr[i][18] = gfcnt[i]
            darr[i][18] = dmcnt[i]
        }

        view.updateUI()
    } catch (e: JSONException) {
        e.printStackTrace()
    }
}