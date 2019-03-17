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
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.TowerListContact
import com.roxyeris.gdinfo.presenter.TowerListPresenter
import com.roxyeris.gdinfo.tool.Const
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_towerlist.*

class TowerListActivity: BaseNavActivity(), TowerListContact.View {
    override lateinit var presenter: TowerListContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_towerlist, frameMain)
        presenter = TowerListPresenter().apply() {
            view = this@TowerListActivity
        }

        var lang = presenter.langCheck(this@TowerListActivity)
        updateBtnLang(lang)
        initialize()
    }

    fun initialize() {
        var dIntent = Intent(this, TowerDetailActivity::class.java)
        btn_tw_dmdkdk.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.DKDK.value)
                startActivity(dIntent)
            }
        }
        btn_tw_dmfc.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.DMFC.value)
                startActivity(dIntent)
            }
        }
        btn_tw_dmleft.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.LEFT.value)
                startActivity(dIntent)
            }
        }
        btn_tw_dmnote.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.NOTE.value)
                startActivity(dIntent)
            }
        }
        btn_tw_gfalter.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.ALTER.value)
                startActivity(dIntent)
            }
        }
        btn_tw_gfchord.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.CHORD.value)
                startActivity(dIntent)
            }
        }
        btn_tw_gffc.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.GFFC.value)
                startActivity(dIntent)
            }
        }
        btn_tw_gfmixed.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined();
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.MIXED.value)
                startActivity(dIntent)
            }
        }
        btn_tw_spmodeldd.setOnClickListener {
            if(bpresenter.mAuth.currentUser == null) {
                alertNotLogined()
            }
            else {
                dIntent.putExtra("type", Const.TOWER_TYPE.SPMODELDD.value)
                startActivity(dIntent)
            }
        }
    }

    fun alertNotLogined() {
        AlertDialog.Builder(this)
                .setTitle(R.string.tl_notlogin_title)
                .setMessage(R.string.tl_notlogin_cont)
                .setNeutralButton(R.string.ok, object:DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0?.dismiss()
                    }
                })
                .create().show();
    }

    fun updateBtnLang(lang:String) {
        if(lang == "jp") {
            btn_tw_dmdkdk.setImageResource(R.drawable.towerdmdkdk_jp)
            btn_tw_dmfc.setImageResource(R.drawable.towerdmfc_jp)
            btn_tw_dmleft.setImageResource(R.drawable.towerdmleftpedal_jp)
            btn_tw_dmnote.setImageResource(R.drawable.towerdmnote_jp)
            btn_tw_gfalter.setImageResource(R.drawable.towergfalter_jp)
            btn_tw_gfchord.setImageResource(R.drawable.towergfchord_jp)
            btn_tw_gffc.setImageResource(R.drawable.towergffc_jp)
            btn_tw_gfmixed.setImageResource(R.drawable.towergfmixed_jp)
        }
    }
}