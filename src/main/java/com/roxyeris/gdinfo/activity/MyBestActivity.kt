/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.roxyeris.gdinfo.data.MyBestData
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.MyBestContact
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.presenter.MyBestPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_mybest.*
import org.json.JSONException
import java.util.ArrayList

class MyBestActivity : BaseNavActivity(), MyBestContact.View {
    lateinit override var presenter: MyBestContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_mybest, frameMain)
        presenter = MyBestPresenter().apply {
            view = this@MyBestActivity
            userid = intent.getIntExtra("id", 0)
        }
        initView()

        // basic view
        presenter.requestData(this, 0)
    }

    fun initView() {
        btn_best_m.setOnClickListener { presenter.requestData(this, 0) }
        btn_best_p.setOnClickListener { presenter.requestData(this, 1) }
        btn_best_g.setOnClickListener { presenter.requestData(this, 2) }
        btn_best_d.setOnClickListener { presenter.requestData(this, 3) }
    }

    override fun updateUI(username:String, bestdata:ArrayList<MyBestData>, type:Int) {
        supportActionBar!!.setSubtitle(username + getString(R.string.sub_best))
        flex_mybest.removeAllViews()

        val size = bestdata.size
        for (i in 0 until size) {
            try {
                val v = layoutInflater.inflate(R.layout.obj_mybest, null)
                val c = bestdata[i]

                imageLoader.displayImage(Const.ADDR_MUSIC + c.mid.toString() + ".jpg", v.findViewById<ImageView>(R.id.iv_best_jacket))
                v.findViewById<TextView>(R.id.tv_best_num).text = "Rank " + (i + 1).toString()
                if (type == 0) { // mybestm
                    v.findViewById<TextView>(R.id.tv_best_name).text = c.mname
                } else { // 그 외
                    v.findViewById<TextView>(R.id.tv_best_name).text = c.mname
                    v.findViewById<TextView>(R.id.tv_best_pat).text = c.diff
                }
                v.findViewById<TextView>(R.id.tv_best_cnt).text = c.cnt.toString() + getString(R.string.tv_best_cnt)

                flex_mybest.addView(v)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}
