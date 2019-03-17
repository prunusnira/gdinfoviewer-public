/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.LinearLayout

import android.content.Context
import android.view.LayoutInflater
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.adapter.SettingAdapter
import com.roxyeris.gdinfo.contact.SettingContact
import com.roxyeris.gdinfo.presenter.SettingPresenter
import com.roxyeris.gdinfo.tool.IntStringStructure
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_wdg_setting_font.*
import kotlinx.android.synthetic.main.lo_widgetconfig.*
import yuku.ambilwarna.AmbilWarnaDialog

class SettingActivity : BaseNavActivity(), SettingContact.View {
    lateinit override var presenter:SettingContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_widgetconfig, frameMain)
        presenter = SettingPresenter().apply {
            view = this@SettingActivity
            item = ArrayList<IntStringStructure>()

            item.add(IntStringStructure(R.drawable.logo, getString(R.string.set_logout_t)))
            item.add(IntStringStructure(R.drawable.logo, getString(R.string.set_wgtbg_t)))
            item.add(IntStringStructure(R.drawable.logo, getString(R.string.set_wgtfontc_t)))
            item.add(IntStringStructure(R.drawable.logo, getString(R.string.set_wgtfonts_t)))
            item.add(IntStringStructure(R.drawable.logo, getString(R.string.set_wgtuitype_t)))
        }
        initView()
    }

    fun initView() {
        lv_setting.setOnItemClickListener { _, _, position, _ ->
            when(position) {
                0 -> {
                    // 로그아웃
                    logout()
                }
                1 -> {
                    // 배경투명도
                    setBGColor()
                }
                2 -> {
                    // 글자색
                    setFontColor()
                }
                3 -> {
                    // 글자크기
                    setFontSize()
                }
                4 -> {
                    // UI종류
                    setUIType()
                }
            }
        }
        lv_setting.adapter = SettingAdapter(this, presenter.item)
    }

    fun generateDialog(titleid: Int, view: View,
                       poList: DialogInterface.OnClickListener) {
        val dlg = AlertDialog.Builder(this)
        dlg.setTitle(getString(titleid))
        dlg.setView(view)
        dlg.setPositiveButton(R.string.ok, poList)
        dlg.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener
        { dialog, _ ->  dialog.dismiss() }
        )
        dlg.create().show()
    }

    fun generateDialog(titleid: Int, msg: String,
                       poList: DialogInterface.OnClickListener) {
        val dlg = AlertDialog.Builder(this)
        dlg.setTitle(getString(titleid))
        dlg.setMessage(msg)
        dlg.setPositiveButton(R.string.ok, poList)
        dlg.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener
        { dialog, _ ->  dialog.dismiss() }
        )
        dlg.create().show()
    }

    fun logout() {
        val list = DialogInterface.OnClickListener(
                { _, _ ->
                    run {
                        presenter.logout(pref!!)
                        bpresenter.mAuth.signOut()
                        finish()
                    }
                }
        )
        if(!presenter.isLogin(pref!!)) {
            generateDialog(R.string.set_logout_t, getString(R.string.set_logout_s), list)
        }
        else {
            val dlg = AlertDialog.Builder(this)
            dlg.setMessage(getString(R.string.set_logout_state_notlogin))
            dlg.setNeutralButton(R.string.ok, DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
            dlg.create().show();
        }
    }

    fun setBGColor() {
        val color = presenter.getBGColor(pref!!)

        val colorDlg = AmbilWarnaDialog(this,
                Color.parseColor("#"+color), true,
                object:AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog) {
                        dialog.dialog.dismiss()
                    }

                    override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                        presenter.setBGColor(pref!!, String.format("%08X", 0xFFFFFFFF.toInt() and color))
                    }
                })
        colorDlg.dialog.setTitle(R.string.set_wgtbg_t)
        colorDlg.show()
    }

    fun setFontColor() {
        val color = presenter.getFontColor(pref!!)

        val colorDlg = AmbilWarnaDialog(this,
                Color.parseColor("#"+color), false,
                object:AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog) {
                        dialog.dialog.dismiss()
                    }

                    override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                        presenter.setFontColor(pref!!, String.format("%08X", 0xFFFFFF.toInt() and color))
                    }
                })
        colorDlg.dialog.setTitle(R.string.set_wgtfontc_t)
        colorDlg.show()
    }

    fun setFontSize() {
        val size = presenter.getFontSize(pref!!)
        val tid = R.string.set_wgtfonts_t
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.lo_wdg_setting_font, null)
        edt_wdg_fonts.setText(size.toString())
        val list = DialogInterface.OnClickListener {
            _, _ ->
            // 숫자 받아와서 preference 저장
            presenter.setFontSize(pref!!, edt_wdg_fonts.text.toString().toInt())
        }
        generateDialog(tid, view, list)
    }

    fun setUIType() {
        //val type = presenter.getWdgType(pref!!)
        val tid = R.string.set_wgtbg_t
        val view = LinearLayout(this)
        val list = DialogInterface.OnClickListener {
            _, _ ->
            //presenter.setWdgType(pref!!, ) // 나중에 다이얼로그를 추가로 만들어서 생성함
        }
        generateDialog(tid, view, list)
    }
}
