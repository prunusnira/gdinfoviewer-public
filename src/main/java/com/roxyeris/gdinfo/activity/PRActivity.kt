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

import com.roxyeris.gdinfo.adapter.PRAdapter
import com.roxyeris.gdinfo.data.PRData
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.PRContact
import com.roxyeris.gdinfo.presenter.PRPresenter
import com.roxyeris.gdinfo.tool.Const
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_patterndetail.*

import java.util.ArrayList

class PRActivity : BaseNavActivity(), PRContact.View {
    lateinit override var presenter: PRContact.Presenter
    lateinit var adapter: PRAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_patterndetail, frameMain)
        presenter = PRPresenter().apply {
            view = this@PRActivity
            mid = intent.getIntExtra("mid", 0)
            ptcode = intent.getIntExtra("ptcode", 0)
            page = intent.getIntExtra("page", 0)
            ver = intent.getIntExtra("ver", Const.CURRENTVER)
            data = ArrayList<PRData>()
        }
        adapter = PRAdapter(applicationContext, presenter.data, presenter.page)
        initView()
        presenter.requestData(this@PRActivity)
    }

    fun initView() {
        lv_ptd_rank.adapter = adapter
        lv_ptd_rank.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@PRActivity, MusicActivity::class.java)
            intent.putExtra("mid", presenter.mid)
            intent.putExtra("uid", presenter.data[position].userid)
            startActivity(intent)
        }

        btn_ptrank_pagedown.setOnClickListener {
            if(presenter.page > 1) {
                presenter.data.clear()
                presenter.page--
                adapter.updatePage(presenter.page)
                presenter.requestData(this@PRActivity)
            }
        }

        btn_ptrank_pageup.setOnClickListener {
            if(presenter.page < presenter.pages) {
                presenter.data.clear()
                presenter.page++
                adapter.updatePage(presenter.page)
                presenter.requestData(this@PRActivity)
            }
        }
    }

    override fun updateUI(mname:String, comp:String, pattern:String, level:Int, pages:Int) {
        imageLoader.displayImage(Const.ADDR_MUSIC + presenter.mid + ".jpg", iv_ptd_jacket)
        presenter.pages = pages
        tv_ptd_name.text = mname
        tv_ptd_comp.text = comp
        tv_ptd_pattern.text = pattern + "/" + (level.toDouble() / 100).toString()
        adapter.notifyDataSetChanged()
    }
}
