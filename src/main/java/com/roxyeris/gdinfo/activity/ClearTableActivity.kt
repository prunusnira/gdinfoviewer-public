/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.os.Bundle
import android.widget.TextView

import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.ClearTableContract
import com.roxyeris.gdinfo.presenter.ClearTablePresenter
import com.roxyeris.gdinfo.tool.Const
import kotlinx.android.synthetic.main.lo_baseact.*
import org.json.JSONException

class ClearTableActivity : BaseNavActivity(), ClearTableContract.View {
    lateinit override var presenter: ClearTableContract.Presenter

    val garray = arrayOfNulls<Array<TextView?>>(9)
    val darray = arrayOfNulls<Array<TextView?>>(9)

    // 데이터
    var userid:Int = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_ctable, frameMain)
        presenter = ClearTablePresenter().apply {
            view = this@ClearTableActivity
            gfData = Array<Array<Int>>(9, {_ -> Array<Int>(19, {_ -> 0})})
            dmData = Array<Array<Int>>(9, {_ -> Array<Int>(19, {_ -> 0})})
        }
        initView()
        presenter.requestData(this, userid)
    }

    fun initView() {
        supportActionBar!!.subtitle = getString(R.string.btn_profile_cleartable)
        userid = intent.getIntExtra("id", 0)

        // textview array init
        for(i in 0..8) {
            garray[i] = arrayOfNulls<TextView>(19)
            darray[i] = arrayOfNulls<TextView>(19)
        }

        for(i in 0..8) {
            for (j in 0..18) {
                garray[i]!![j] = findViewById(Const.getResId(this, "ct_g_" + i.toString() + "_" + j.toString()))
                darray[i]!![j] = findViewById(Const.getResId(this, "ct_d_" + i.toString() + "_" + j.toString()))
            }
        }
    }

    override fun updateUI() {
        try {
            for(i in 0..8) {
                for (j in 0..18) {
                    garray[i]!![j]!!.text = presenter.gfData[i][j].toString()
                    darray[i]!![j]!!.text = presenter.dmData[i][j].toString()
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
