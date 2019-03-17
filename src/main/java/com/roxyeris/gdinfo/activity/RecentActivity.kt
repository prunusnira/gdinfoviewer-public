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
import android.view.View
import android.widget.AdapterView
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.adapter.RecentAdapter
import com.roxyeris.gdinfo.contact.RecentContact
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.presenter.RecentPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_main.*

class RecentActivity: BaseNavActivity(), RecentContact.View {
    lateinit var adapter: RecentAdapter
    lateinit override var presenter:RecentContact.Presenter
    val recent = ArrayList<RecentData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_main, frameMain)
        presenter = RecentPresenter().apply {
            view = this@RecentActivity
            data = recent
        }
        adapter = RecentAdapter(this@RecentActivity, presenter.data, imageLoader)
        initView()
        presenter.requestData(this@RecentActivity, adapter)

        // 로그인 상태 확인 후 내 정보 업데이트
        // 이 메소드는 앱 메인화면을 봤을때 실행되어 앱에 등록된 내 정보를 갱신하는 역할을 수행
        updateSelf()
    }

    fun initView() {
        supportActionBar!!.subtitle = getString(R.string.tv_recent)

        // Adapter update 및 연결만 수행, 데이터 업데이트는 Receiver의 handler가 담당
        adapter = RecentAdapter(this, recent, imageLoader)

        refresh_recent.setOnRefreshListener {
            presenter.data.clear()
            presenter.requestData(this@RecentActivity, adapter)
            refresh_recent.isRefreshing = false
        }
        lv_recent.adapter = adapter
        lv_recent.setOnItemClickListener(object:AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val intent = Intent(this@RecentActivity, ProfileActivity::class.java)
                intent.putExtra("id", (adapter.getItem(p2) as RecentData).id)
                startActivity(intent)
            }
        })
    }

    fun updateSelf() {
        if(pref != null) {
            if (pref!!.getInt("numid", -1) > 0) {
                presenter.requestSelf(this, pref!!)
            }
        }
    }
}