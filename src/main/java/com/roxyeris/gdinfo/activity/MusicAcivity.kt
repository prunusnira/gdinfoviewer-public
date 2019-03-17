/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.os.Bundle
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.adapter.MusicAdapter
import com.roxyeris.gdinfo.contact.MusicContact
import com.roxyeris.gdinfo.data.MusicData
import com.roxyeris.gdinfo.data.RecentData
import com.roxyeris.gdinfo.presenter.MusicPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_music.*
import java.util.ArrayList

class MusicActivity : BaseNavActivity(), MusicContact.View {
    lateinit override var presenter: MusicContact.Presenter
    lateinit var adapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_music, frameMain)
        presenter = MusicPresenter().apply {
            view = this@MusicActivity
            data = ArrayList<MusicData>()
            userdata = RecentData()
            mid = intent.getIntExtra("mid", 0)
            uid = intent.getIntExtra("uid", 0)
        }
        initView()
    }

    fun initView() {
        adapter = MusicAdapter(this, presenter.data, presenter.mid, imageLoader)
        lv_music.adapter = adapter
        presenter.requestUserData(this)
        presenter.requestData(this)
    }

    override fun updateUI(jacketUrl:String, mname:String, comp:String) {
        imageLoader.displayImage(jacketUrl, iv_music_jacket)
        tv_music_name.text = mname
        tv_music_comp.text = comp
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun updateUserData() {
        tv_music_player.text = "Player: " + presenter.userdata.name
    }
}