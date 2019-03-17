/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.app.ActionBar
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.adapter.PatternAdapter
import com.roxyeris.gdinfo.data.PatternData
import com.roxyeris.gdinfo.adapter.RecentAdapter
import com.roxyeris.gdinfo.contact.SearchContact
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.presenter.SearchPresenter
import kotlinx.android.synthetic.main.filter_ptd_select.view.*
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_search.*

class SearchActivity: BaseNavActivity(), SearchContact.View {
    lateinit override var presenter: SearchContact.Presenter
    lateinit var ptadapter:PatternAdapter
    lateinit var useradapter:RecentAdapter
    var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_search, frameMain)
        presenter = SearchPresenter().apply {
            view = this@SearchActivity
            ptdata = ArrayList<PatternData>()
            userdata = ArrayList<RecentData>()
        }
        ptadapter = PatternAdapter(this, presenter.ptdata, imageLoader)
        useradapter = RecentAdapter(this, presenter.userdata, imageLoader)
        initView()
    }

    override fun updateUI() {
        when(presenter.type) {
            0 ->  {
                lv_search_result.adapter = ptadapter
                ptadapter.notifyDataSetChanged()
            }
            else -> {
                lv_search_result.adapter = useradapter
                useradapter.notifyDataSetChanged()
            }
        }
    }

    fun initView() {
        btn_search_submit.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                presenter.searchtxt = edt_search_cnt.text.toString()

                when(rg_search_option.checkedRadioButtonId) {
                    R.id.radio_search_music -> presenter.requestData(this@SearchActivity, 0)
                    R.id.radio_search_gskill -> {
                        if(checkTextNumber(presenter.searchtxt))
                            presenter.requestData(this@SearchActivity, 1)
                        else {
                            Toast.makeText(this@SearchActivity, getString(R.string.tst_search_notnum), Toast.LENGTH_SHORT).show()
                        }
                    }
                    R.id.radio_search_dskill -> {
                        if(checkTextNumber(presenter.searchtxt))
                            presenter.requestData(this@SearchActivity, 2)
                        else {

                        }
                    }
                    R.id.radio_search_player -> presenter.requestData(this@SearchActivity, 3)
                }
            }
        })

        lv_search_result.onItemClickListener = object:AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(type) {
                    0 -> {
                        val dlg = AlertDialog.Builder(this@SearchActivity)
                        dlg.setTitle(R.string.dlg_search_music_sel_title)
                        dlg.setPositiveButton(R.string.dlg_search_music_sel_prank, object:DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                // 패턴 선택을 위한 Alert Dialog를 띄움
                                val patdlg = AlertDialog.Builder(this@SearchActivity)
                                patdlg.setTitle(R.string.tv_pat_dlg_title)
                                val v = layoutInflater.inflate(R.layout.filter_ptd_select, null)

                                val intent = Intent(this@SearchActivity, PRActivity::class.java)
                                intent.putExtra("mid", presenter.ptdata[p2].mid)
                                intent.putExtra("page", 1)

                                v.btn_ptd_gbsc.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 1)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_gadv.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 2)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_gext.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 3)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_gmas.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 4)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_bbsc.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 5)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_badv.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 6)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_bext.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 7)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_bmas.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 8)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_dbsc.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 9)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_dadv.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 10)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_dext.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 11)
                                        startActivity(intent)
                                    }
                                })
                                v.btn_ptd_dmas.setOnClickListener(object:View.OnClickListener {
                                    override fun onClick(p0: View?) {
                                        intent.putExtra("ptcode", 12)
                                        startActivity(intent)
                                    }
                                })

                                val llparams = LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                                        ActionBar.LayoutParams.MATCH_PARENT)
                                llparams.gravity = Gravity.CENTER
                                v.layoutParams = llparams
                                patdlg.setView(v)
                                patdlg.create().show()
                            }
                        })
                        dlg.setNegativeButton(R.string.dlg_search_music_sel_myrecord, object:DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                if(pref!!.getInt("numid", 0) != 0) {
                                    val intent = Intent(this@SearchActivity, MusicActivity::class.java)
                                    intent.putExtra("mid", presenter.ptdata[p2].mid)
                                    intent.putExtra("uid", pref!!.getInt("numid", 0))
                                    startActivity(intent)
                                }
                                else {
                                    // 로그인 상태 아님, 메시지 띄움
                                    val d = AlertDialog.Builder(this@SearchActivity)
                                    d.setTitle(R.string.dlg_search_notlogin)
                                    d.setMessage(R.string.dlg_search_notlogin_cont)
                                    d.setNeutralButton("Cancel", object:DialogInterface.OnClickListener {
                                        override fun onClick(p0: DialogInterface?, p1: Int) {
                                            p0!!.dismiss()
                                        }
                                    })
                                    d.create().show()
                                }
                            }
                        })
                        dlg.setNeutralButton("Cancel", object:DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                p0!!.dismiss()
                            }
                        })
                        dlg.create().show()
                    }
                    else -> {
                        val intent = Intent(applicationContext, ProfileActivity::class.java)
                        intent.putExtra("id", presenter.userdata[p2].id)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    fun checkTextNumber(str:String):Boolean {
        var chk:Boolean = true
        for(i in 0..str.length) {
            if(str.toDoubleOrNull() == null) chk = false
        }
        return chk
    }
}