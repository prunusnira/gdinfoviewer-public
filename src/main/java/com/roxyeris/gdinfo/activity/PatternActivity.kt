/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.LinearLayout
import com.roxyeris.gdinfo.adapter.PatternAdapter
import com.roxyeris.gdinfo.data.PatternData
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.PatternContact
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.presenter.PatternPresenter
import kotlinx.android.synthetic.main.filter_ptd_select.view.*
import kotlinx.android.synthetic.main.filter_skill.*
import kotlinx.android.synthetic.main.filter_ptn.view.*
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_pattern.*
import java.util.ArrayList

class PatternActivity : BaseNavActivity(), PatternContact.View {
    lateinit override var presenter: PatternContact.Presenter
    lateinit var adapter: PatternAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_pattern, frameMain)
        presenter = PatternPresenter().apply {
            view = this@PatternActivity
            ver = intent.getStringExtra("ver")
            order = intent.getStringExtra("order")
            page = intent.getIntExtra("page", 1)
            hot = intent.getStringExtra("hot")
            data = ArrayList<PatternData>()
        }
        adapter = PatternAdapter(applicationContext, presenter.data, imageLoader)
        initView()

        // basic view
        presenter.requestData(this)
    }

    fun initView() {
        lv_pattern.adapter = adapter
        supportActionBar!!.subtitle = "Pattern List"

        lv_pattern.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val dialog = AlertDialog.Builder(this@PatternActivity)
            dialog.setTitle(R.string.tv_pat_dlg_title)
            val dv = layoutInflater.inflate(R.layout.filter_ptd_select, null)

            val intent = Intent(this@PatternActivity, PRActivity::class.java)
            intent.putExtra("mid", presenter.data[position].mid)
            intent.putExtra("page", 1)
            intent.putExtra("ver", Const.CURRENTVER)

            dv.btn_ptd_gbsc.setOnClickListener {
                intent.putExtra("ptcode", 1)
                startActivity(intent)
            }
            dv.btn_ptd_gadv.setOnClickListener {
                intent.putExtra("ptcode", 2)
                startActivity(intent)
            }
            dv.btn_ptd_gext.setOnClickListener {
                intent.putExtra("ptcode", 3)
                startActivity(intent)
            }
            dv.btn_ptd_gmas.setOnClickListener {
                intent.putExtra("ptcode", 4)
                startActivity(intent)
            }
            dv.btn_ptd_bbsc.setOnClickListener {
                intent.putExtra("ptcode", 5)
                startActivity(intent)
            }
            dv.btn_ptd_badv.setOnClickListener {
                intent.putExtra("ptcode", 6)
                startActivity(intent)
            }
            dv.btn_ptd_bext.setOnClickListener {
                intent.putExtra("ptcode", 7)
                startActivity(intent)
            }
            dv.btn_ptd_bmas.setOnClickListener {
                intent.putExtra("ptcode", 8)
                startActivity(intent)
            }
            dv.btn_ptd_dbsc.setOnClickListener {
                intent.putExtra("ptcode", 9)
                startActivity(intent)
            }
            dv.btn_ptd_dadv.setOnClickListener {
                intent.putExtra("ptcode", 10)
                startActivity(intent)
            }
            dv.btn_ptd_dext.setOnClickListener {
                intent.putExtra("ptcode", 11)
                startActivity(intent)
            }
            dv.btn_ptd_dmas.setOnClickListener {
                intent.putExtra("ptcode", 12)
                startActivity(intent)
            }

            val params = LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT)
            params.gravity = Gravity.CENTER
            dv.layoutParams = params
            dialog.setView(dv)
            dialog.create().show()
        }

        btn_pattern_filter.setOnClickListener {
            val dialog = android.app.AlertDialog.Builder(this@PatternActivity)
            dialog.setTitle(getString(R.string.filter_title))
            // 새 뷰를 만들어서 내용으로 등록
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val sv = inflater.inflate(R.layout.filter_ptn, null)

            // 각 버튼 항목 설정
            val ver = arrayOfNulls<CheckBox>(26)
            for (i in 0..25) {
                ver[i] = sv.findViewById<View>(Const.getResId(this@PatternActivity,
                        "chk_filter_ver" + i.toString())) as CheckBox
            }

            dialog.setPositiveButton("APPLY") { _, _ ->
                var nver = ""
                for (i in ver.indices) {
                    if (ver[i]!!.isChecked()) {
                        val no = i + 1
                        if (no < 10) {
                            nver += "0" + no.toString()
                        } else {
                            nver += no.toString()
                        }
                    }
                }
                if (nver == "") {
                    nver = "00"
                }

                presenter.ver = nver

                if (sv.chk_filter_hot_h.isChecked) presenter.hot = "h"
                if (sv.chk_filter_hot_o.isChecked) presenter.hot = "o"

                presenter.order = when(sv.rg_filter_order.checkedRadioButtonId) {
                    R.id.radio_filter_order_titleasc -> "titleasc"
                    R.id.radio_filter_order_titledesc -> "titledesc"
                    R.id.radio_filter_order_verasc -> "verasc"
                    R.id.radio_filter_order_verdesc -> "verdesc"
                    R.id.radio_filter_order_gbscasc -> "gbscasc"
                    R.id.radio_filter_order_gbscdesc -> "gbscdesc"
                    R.id.radio_filter_order_gadvasc -> "gadvasc"
                    R.id.radio_filter_order_gadvdesc -> "gadvdesc"
                    R.id.radio_filter_order_gextasc -> "gextasc"
                    R.id.radio_filter_order_gextdesc -> "gextdesc"
                    R.id.radio_filter_order_gmasasc -> "gmasasc"
                    R.id.radio_filter_order_gmasdesc -> "gmasdesc"
                    R.id.radio_filter_order_bbscasc -> "bbscasc"
                    R.id.radio_filter_order_bbscdesc -> "bbscdesc"
                    R.id.radio_filter_order_badvasc -> "badvasc"
                    R.id.radio_filter_order_badvdesc -> "badvdesc"
                    R.id.radio_filter_order_bextasc -> "bextasc"
                    R.id.radio_filter_order_bextdesc -> "bextdesc"
                    R.id.radio_filter_order_bmasasc -> "bmasasc"
                    R.id.radio_filter_order_bmasdesc -> "bmasdesc"
                    R.id.radio_filter_order_dbscasc -> "dbscasc"
                    R.id.radio_filter_order_dbscdesc -> "dbscdesc"
                    R.id.radio_filter_order_dadvasc -> "dadvasc"
                    R.id.radio_filter_order_dadvdesc -> "dadvdesc"
                    R.id.radio_filter_order_dextasc -> "dextasc"
                    R.id.radio_filter_order_dextdesc -> "dextdesc"
                    R.id.radio_filter_order_dmasasc -> "dmasasc"
                    R.id.radio_filter_order_dmasdesc -> "dmasdesc"
                    else -> "titleasc"
                }

                presenter.data.clear()
                presenter.requestData(this)
            }

            dialog.setNegativeButton("RESET") { _, _ ->
                rg_filter_order.clearCheck()
                chk_filter_hot_h.isChecked = false
                chk_filter_hot_o.isChecked = false
                for (i in ver.indices) {
                    ver[i]!!.setChecked(false)
                }
            }

            dialog.setNeutralButton("CLOSE") { dlg, _ -> dlg.dismiss() }

            dialog.setView(sv)
            dialog.show()
        }

        btn_pattern_pagedown.setOnClickListener {
            if (presenter.page > 1) {
                presenter.data.clear()
                presenter.page--
                presenter.requestData(this)
            }
        }

        btn_pattern_pageup.setOnClickListener {
            if(presenter.page < presenter.pages) {
                presenter.data.clear()
                presenter.page++
                presenter.requestData(this)
            }
        }
    }

    override fun updateUI(pages:Int) {
        presenter.pages = pages
        adapter.notifyDataSetChanged()
    }
}
