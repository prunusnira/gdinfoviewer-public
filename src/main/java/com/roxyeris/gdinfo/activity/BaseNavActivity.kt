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
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.BaseNavContact
import com.roxyeris.gdinfo.presenter.BaseNavPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.app_bar_base_nav.*
import kotlinx.android.synthetic.main.nav_header_base_nav.view.*

open class BaseNavActivity : BaseNavContact.View, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    // SharedPreferences
    protected var pref: SharedPreferences? = null

    lateinit override var bpresenter: BaseNavContact.Presenter

    // imageloader (Base에서 불러옴으로서 모든 앱에서 바로 사용 가능)
    // Java로 작성된 API 이므로 Java식 싱글턴 로드 사용
    protected val imageLoader = ImageLoader.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lo_baseact)
        setSupportActionBar(toolbar)

        pref = PreferenceManager.getDefaultSharedPreferences(this)
        bpresenter = BaseNavPresenter().apply() {
            bview = this@BaseNavActivity
            mAuth = FirebaseAuth.getInstance()
        }

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null // 네비바 아이콘 흑백을 색상 살려줌

        val config = ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()).build()
        imageLoader.init(config)
    }

    override fun onResume() {
        super.onResume()
        // 현재 로그인 상태를 확인하고 메뉴 업데이트를 수행
        bpresenter.checkLoginStatus(PreferenceManager.getDefaultSharedPreferences(this))
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.base_nav, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.menu_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                val id = pref!!.getInt("numid", 0)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            R.id.menu_home -> {
                startActivity(Intent(this, RecentActivity::class.java))
            }
            R.id.menu_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.menu_ranking -> {
                val intent = Intent(this, SRActivity::class.java)
                intent.putExtra("gtype", "gf")
                intent.putExtra("page", 1)
                startActivity(intent)
            }
            R.id.menu_pattern -> {
                val intent = Intent(this, PatternActivity::class.java)
                intent.putExtra("ver", "00")
                intent.putExtra("page", 1)
                intent.putExtra("order", "titleasc")
                intent.putExtra("hot", "h")
                startActivity(intent)
            }
            R.id.menu_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
            R.id.menu_about -> {
                val dlg = AlertDialog.Builder(this, R.style.DarkDialog)
                dlg.setTitle("GITADORA Info Viewer")
                dlg.setMessage(
                        "This app is viewer of GITADORA Info\n\n"+
                                "Dev: Prunus Nira (@prunusNira)\n"+
                                "Ver: 0.1.4.190314b\n"+
                                "Twitter: @gitadorainfo"
                )
                dlg.setNeutralButton(R.string.ok) {
                    dialogInterface: DialogInterface?, _: Int -> dialogInterface!!.dismiss()
                }
                dlg.create().show()
            }
            R.id.menu_tower -> {
                startActivity(Intent(this, TowerListActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun updateMenuLogined() {
        nav_view.menu.findItem(R.id.menu_login).setVisible(false)
        nav_view.menu.findItem(R.id.menu_profile).setVisible(true)

        var view = nav_view.getHeaderView(0)
        view.headerTopText.text = pref!!.getString("name", "")
        view.headerBottomText.text = "GF "+pref!!.getString("gskill", "0.00")+" DM "+pref!!.getString("dskill", "0.00")
    }

    override fun updateMenuLogout() {
        nav_view.menu.findItem(R.id.menu_login).setVisible(true)
        nav_view.menu.findItem(R.id.menu_profile).setVisible(false)

        var view = nav_view.getHeaderView(0)
        view.headerTopText.text = getString(R.string.nav_header_title)
        view.headerBottomText.text = getString(R.string.nav_header_subtitle)
    }
}
