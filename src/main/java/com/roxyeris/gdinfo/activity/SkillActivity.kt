/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.data.SkillData
import com.roxyeris.gdinfo.adapter.SkillAdapter
import com.roxyeris.gdinfo.contact.SkillContact
import com.roxyeris.gdinfo.presenter.SkillPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_skill.*
import kotlinx.android.synthetic.main.obj_numpick.view.*
import kotlinx.android.synthetic.main.filter_target.view.*

class SkillActivity : BaseNavActivity(), SkillContact.View {
    lateinit override var presenter: SkillContact.Presenter
    lateinit var adapter:SkillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_skill, frameMain)
        presenter = SkillPresenter().apply {
            view = this@SkillActivity

            ptype = intent.extras.getInt("ptype")
            userid = intent.extras.getInt("id")
            gtype = intent.extras.getString("gtype")
            page = intent.extras.getInt("page")
            order = intent.extras.getString("order")

            hot = intent.extras.getString("hot", "h")

            target = intent.extras.getString("target", "y")
            data = ArrayList<SkillData>()
        }
        adapter = SkillAdapter(this, presenter.data, presenter.page, imageLoader)
        initView()
        presenter.requestData(this@SkillActivity)
    }

    override fun updateUI(pages:Int) {
        presenter.pages = pages
        adapter.notifyDataSetChanged()
    }

    fun initView() {
        // UI initView
        lv_skill.emptyView = tv_skill_empty

        btn_skill_category.setOnClickListener(object:View.OnClickListener {
            override fun onClick(p0: View?) {
                val dlg = AlertDialog.Builder(this@SkillActivity)
                dlg.setTitle(R.string.btn_skill_category)
                dlg.setNeutralButton(R.string.close) {
                    dialogInterface: DialogInterface?, _: Int -> dialogInterface!!.dismiss()
                }

                val view = LayoutInflater.from(this@SkillActivity).inflate(R.layout.filter_target, null)
                // view에 대해서 버튼 동작 정의 - presenter에서 변경 수행

                view.btn_skill_gfall.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "gf"
                        presenter.ptype = 0
                        presenter.hot = ""
                        presenter.order = "skilldesc"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_skill_dmall.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "dm"
                        presenter.ptype = 0
                        presenter.hot = ""
                        presenter.order = "skilldesc"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_gfhot.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "gf"
                        presenter.ptype = 1
                        presenter.hot = "h"
                        presenter.order = "y"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_gfhot_c.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "gf"
                        presenter.ptype = 1
                        presenter.hot = "h"
                        presenter.order = "n"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_dmhot.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "dm"
                        presenter.ptype = 1
                        presenter.hot = "h"
                        presenter.order = "y"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_dmhot_c.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "dm"
                        presenter.ptype = 1
                        presenter.hot = "h"
                        presenter.order = "n"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_gfother.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "gf"
                        presenter.ptype = 1
                        presenter.hot = "o"
                        presenter.order = "y"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_gfother_c.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "gf"
                        presenter.ptype = 1
                        presenter.hot = "o"
                        presenter.order = "n"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_dmother.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "dm"
                        presenter.ptype = 1
                        presenter.hot = "o"
                        presenter.order = "y"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_dmother_c.setOnClickListener(object:View.OnClickListener {
                    override fun onClick(p0: View?) {
                        presenter.data.clear()
                        presenter.gtype = "dm"
                        presenter.ptype = 1
                        presenter.hot = "o"
                        presenter.order = "n"
                        presenter.page = 1
                        presenter.requestData(this@SkillActivity)
                    }
                })

                view.btn_target_tb_gfhot.isEnabled = false;
                view.btn_target_tb_gfother.isEnabled = false;
                view.btn_target_tb_dmhot.isEnabled = false;
                view.btn_target_tb_dmother.isEnabled = false;
                view.btn_target_tbre_gfhot.isEnabled = false;
                view.btn_target_tbre_gfother.isEnabled = false;
                view.btn_target_tbre_dmhot.isEnabled = false;
                view.btn_target_tbre_dmother.isEnabled = false;
                view.btn_target_mx_gfhot.isEnabled = false;
                view.btn_target_mx_gfother.isEnabled = false;
                view.btn_target_mx_dmhot.isEnabled = false;
                view.btn_target_mx_dmother.isEnabled = false;

                dlg.setView(view)
                dlg.create().show()
            }
        })

        btn_skill_pagechange.setOnClickListener(object:View.OnClickListener {
            override fun onClick(p0: View?) {
                val dlg = AlertDialog.Builder(this@SkillActivity)
                dlg.setTitle(R.string.btn_skill_pagechange)
                val inflater = LayoutInflater.from(applicationContext)
                val view = inflater.inflate(R.layout.obj_numpick, null)
                view.numpick.minValue = 1
                view.numpick.maxValue = presenter.pages
                view.numpick.value = presenter.page
                dlg.setView(view)

                dlg.setNegativeButton(R.string.close) {
                    dialogInterface: DialogInterface?, _: Int -> dialogInterface!!.dismiss()
                }

                dlg.setPositiveButton(R.string.ok) {
                    dialogInterface, _ ->
                        presenter.data.clear()
                        presenter.page = view.numpick.value
                        adapter.updatePage(presenter.page)
                        presenter.requestData(this@SkillActivity)
                        dialogInterface.dismiss()
                }
                dlg.create().show()
            }
        })

        lv_skill.adapter = adapter
        lv_skill.setOnItemClickListener(object:AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val intent = Intent(this@SkillActivity, MusicActivity::class.java)
                intent.putExtra("mid", presenter.data[p2].musicid)
                intent.putExtra("uid", presenter.userid)
                startActivity(intent)
            }
        })
    }
}