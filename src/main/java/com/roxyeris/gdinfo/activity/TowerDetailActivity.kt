/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.TowerDetailContact
import com.roxyeris.gdinfo.data.TowerData
import com.roxyeris.gdinfo.presenter.TowerDetailPresenter
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.Converter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_towerdetail.*
import kotlinx.android.synthetic.main.obj_towerfloor.view.*

class TowerDetailActivity: BaseNavActivity(), TowerDetailContact.View {
    lateinit override var presenter: TowerDetailContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_towerdetail, frameMain)
        presenter = TowerDetailPresenter().apply {
            view = this@TowerDetailActivity
            tower = intent.extras.getInt("type")
        }

        presenter.requestData(this@TowerDetailActivity, pref!!.getInt("numid", 0))
    }

    override fun updateData(tower: TowerData) {
        // Receiver에서 받은 타워 데이터를 이용해 UI 구성
        when(tower.name) {
            "towerDmDKDK" -> td_tname.text = getString(R.string.towerDmDKDK)
            "towerDmLeftPedal" -> td_tname.text = getString(R.string.towerDmLeftPedal)
            "towerDmNote" -> td_tname.text = getString(R.string.towerDmNote)
            "towerDmFc" -> td_tname.text = getString(R.string.towerDmFC)
            "towerGfChord" -> td_tname.text = getString(R.string.towerGfChord)
            "towerGfAlter" -> td_tname.text = getString(R.string.towerGfAlter)
            "towerGfMixed" -> td_tname.text = getString(R.string.towerGfMixed)
            "towerGfFc" -> td_tname.text = getString(R.string.towerGfFc)
            "towerSpModelDD" -> td_tname.text = getString(R.string.towerSpModelDD)
        }

        td_floors.text = tower.floor.toString() + " Floors"
        td_startskill.text = ""

        // 역으로 내려가면서 타워 리스트를 동적으로 추가
        for(i in tower.list.size - 1 downTo 0) {
            val outer = LinearLayout(this@TowerDetailActivity)
            outer.orientation = LinearLayout.VERTICAL

            val now = tower.list[i]

            val button = Button(this@TowerDetailActivity)
            val floorStatus = TextView(this@TowerDetailActivity)
            val clearCondition = TextView(this@TowerDetailActivity)
            val clearInst = TextView(this@TowerDetailActivity)
            val list = LinearLayout(this@TowerDetailActivity)
            val listmust = LinearLayout(this@TowerDetailActivity)

            var isFloorClear = true
            val numOfClear = Math.ceil(now!!.size*0.75).toInt()

            list.orientation = LinearLayout.VERTICAL
            listmust.orientation = LinearLayout.VERTICAL

            val paddingDp = 10
            val density = resources.displayMetrics.density
            val paddingPixel = (paddingDp * density).toInt()
            list.setPadding(0, paddingPixel, 0, paddingPixel)
            listmust.setPadding(0, paddingPixel, 0, paddingPixel)

            button.text = "▼Floor "+(i + 1)

            clearCondition.text = "Clear "+numOfClear+" among "+now.size+" patterns"
            clearInst.text = getString(R.string.td_titlechange)

            floorStatus.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            clearInst.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            clearCondition.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

            var numOfClearDone = 0
            if(now.size > 0) {
                for (j in 0..now.size - 1) {
                    val floor = now[j]
                    val view = layoutInflater.inflate(R.layout.obj_towerfloor, null)

                    var isClear = true

                    // 자켓 이미지
                    imageLoader.displayImage(Const.ADDR_MUSIC+floor.musicid+".jpg", view.iv_jacket)

                    // 음악 이름
                    view.tv_mtitle.text = floor.mname

                    // 난이도, 레벨
                    view.tv_diff_lv.text = Converter.ptcodeConvert(floor.ptcode) + "/" + floor.level

                    // 스코어
                    if(floor.score > 0) {
                        view.tv_scorecond.text = "Score "+floor.skill.score.toString() + "/" + floor.score
                        if(floor.skill.score < floor.score) isClear = false
                    }
                    else {
                        view.tv_scorecond.visibility = View.GONE
                    }

                    // 달성률
                    if(floor.rate > 0) {
                        view.tv_ratecond.text = "Rate "+"%.2f".format(floor.skill.rate.toDouble()/100) + "%/" + "%.2f".format(floor.rate.toDouble()/100) + "%"
                        if(floor.skill.rate < floor.rate) isClear = false
                    }
                    else {
                        view.tv_ratecond.visibility = View.GONE
                    }

                    // 콤보
                    if(floor.combo > 0) {
                        view.tv_combocond.text = "Combo "+floor.skill.combo.toString() + "/" + floor.combo
                        if(floor.skill.combo < floor.combo) isClear = false
                    }
                    else {
                        view.tv_combocond.visibility = View.GONE
                    }

                    if(!isClear) view.iv_pass.setImageResource(R.drawable.running)
                    else numOfClearDone++;
                    list.addView(view)
                }
            }

            if(numOfClearDone < numOfClear.toInt()) {
                isFloorClear = false
            }

            if(isFloorClear)
                floorStatus.text = "Floor "+(i+1)+" CLEARED"
            else
                floorStatus.text = "Floor "+(i+1)+" RUNNING"

            outer.addView(floorStatus)
            outer.addView(listmust)
            outer.addView(clearInst)
            outer.addView(clearCondition)
            outer.addView(list)
            outer.visibility = View.GONE

            button.setOnClickListener(View.OnClickListener {
                if(outer.visibility == View.VISIBLE) {
                    outer.visibility = View.GONE
                    button.text = "▼Floor "+(i + 1)
                }
                else if(outer.visibility == View.GONE) {
                    outer.visibility = View.VISIBLE
                    button.text = "▲Floor "+(i + 1)
                }
            })

            td_lo_base.addView(button)
            td_lo_base.addView(outer)
        }
    }
}