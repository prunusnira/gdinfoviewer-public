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
import android.widget.AdapterView

import com.roxyeris.gdinfo.data.SRData
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.adapter.SRAdapter
import com.roxyeris.gdinfo.contact.SRContact
import com.roxyeris.gdinfo.presenter.SRPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_rank.*

import java.util.ArrayList

class SRActivity : BaseNavActivity(), SRContact.View {
    lateinit override var presenter: SRContact.Presenter
    lateinit var adapter:SRAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_rank, frameMain)
        presenter = SRPresenter().apply {
            view = this@SRActivity
            gtype = intent.getStringExtra("gtype")
            page = intent.getIntExtra("page", 1)
            data = ArrayList<SRData>()
        }
        adapter = SRAdapter(applicationContext, presenter.data, presenter.page, presenter.gtype)
        initView()
    }

    fun initView() {
        lv_rank.adapter = adapter

        btn_rank_gtype.setOnClickListener {
            presenter.data.clear()
            when(presenter.gtype) {
                "gf" -> {
                    presenter.gtype = "dm"
                    presenter.page = 1
                }
                "dm" -> {
                    presenter.gtype = "gf"
                    presenter.page = 1
                }
            }

            adapter.gtype = presenter.gtype
            adapter.page = presenter.page
            presenter.requestData(this, presenter.data)
        }

        btn_rank_pagedown.setOnClickListener {
            if (presenter.page > 1) {
                presenter.data.clear()
                presenter.page--
                adapter.page = presenter.page
                presenter.requestData(this, presenter.data)
            }
        }

        btn_rank_pageup.setOnClickListener {
            presenter.data.clear()
            presenter.page++
            adapter.page = presenter.page
            presenter.requestData(this, presenter.data)
        }

        lv_rank.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.putExtra("id", presenter.data[position].id)
            startActivity(intent)
        }

        presenter.requestData(this, presenter.data)
    }

    override fun updateUI() {
        adapter.notifyDataSetChanged()

        if (presenter.gtype == "gf") {
            supportActionBar!!.subtitle = "GuitarFreaks Skill Ranking"
        } else if (presenter.gtype == "dm") {
            supportActionBar!!.subtitle = "DrumMania Skill Ranking"
        } else {
            supportActionBar!!.subtitle = "Wrong connection"
        }
    }
}
