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
import android.widget.Button
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.ProfileContact
import com.roxyeris.gdinfo.data.ProfileData
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.presenter.ProfilePresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_profile.*
import org.json.JSONException

class ProfileActivity: BaseNavActivity(), ProfileContact.View {
    lateinit override var presenter: ProfileContact.Presenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_profile, frameMain)
        presenter = ProfilePresenter().apply { 
            view = this@ProfileActivity
            userid = intent.getIntExtra("id", 0)
        }
        presenter.requestData(this)
    }

    override fun updateUI(data: ProfileData) {
        tv_profile_title.text = "(" + data.title + ")"
        tv_profile_name.text = data.name
        tv_profile_comment.text = data.comment
        tv_profile_gfskill.text = data.gskill
        tv_profile_dmskill.text = data.dskill
        imageLoader.displayImage(Const.ADDR_BOARD + data.id + ".png", iv_profile_board)

        // 프로필 표 설정
        tv_profile_table_gskill.text = data.gskill + "\n(" + data.gskillall + ")"
        tv_profile_table_dskill.text = data.dskill + "\n(" + data.dskillall + ")"
        tv_profile_table_gclear.text = data.gclearlv + "\n(" + data.gclearnum + ")"
        tv_profile_table_dclear.text = data.dclearlv + "\n(" + data.dclearnum + ")"
        tv_profile_table_gfc.text = data.gfclv + "\n(" + data.gfcnum + ")"
        tv_profile_table_dfc.text = data.dfclv + "\n(" + data.dfcnum + ")"
        tv_profile_table_gexc.text = data.gexclv + "\n(" + data.gexcnum + ")"
        tv_profile_table_dexc.text = data.dexclv + "\n(" + data.dexcnum + ")"
        tv_profile_table_gcnt.text = data.countgf.toString()
        tv_profile_table_dcnt.text = data.countdm.toString()

        Const.horizontalColorSetter(data.gskill.toFloat(), tv_profile_gfskill)
        Const.horizontalColorSetter(data.dskill.toFloat(), tv_profile_dmskill)

        supportActionBar!!.subtitle = data.name + getString(R.string.profile_title)

        // 페이지 내 버튼 상태 변경 (로그인 유무)
        if(pref!!.getInt("id", 0) == data.id) {
            val btnCnt = Button(this)
            val btnCmt = Button(this)

            btnCnt.text = getString(R.string.btn_profile_cnt)
            btnCmt.text = getString(R.string.btn_profile_cmt)

            btnCnt.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    // 카운트 공개 다이얼로그
                }
            })

            btnCmt.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    // 코멘트 변경 다이얼로그
                }
            })

            middlebutton.addView(btnCnt)
            middlebutton.addView(btnCmt)
        }

        btn_profile_gfskill_target.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, SkillActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                    intent.putExtra("gtype", "gf")
                    intent.putExtra("page", 1)
                    intent.putExtra("order", "y")
                    intent.putExtra("ptype", 1)
                    intent.putExtra("hot", "h")
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })

        btn_profile_dmskill_target.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, SkillActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                    intent.putExtra("gtype", "dm")
                    intent.putExtra("page", 1)
                    intent.putExtra("order", "y")
                    intent.putExtra("ptype", 1)
                    intent.putExtra("hot", "h")
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })

        btn_profile_gfskill_all.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, SkillActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                    intent.putExtra("gtype", "gf")
                    intent.putExtra("page", 1)
                    intent.putExtra("order", "skilldesc")
                    intent.putExtra("ptype", 0)
                    intent.putExtra("hot", "")
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })

        btn_profile_dmskill_all.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, SkillActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                    intent.putExtra("gtype", "dm")
                    intent.putExtra("page", 1)
                    intent.putExtra("order", "skilldesc")
                    intent.putExtra("ptype", 0)
                    intent.putExtra("hot", "")
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })

        btn_profile_cleartable.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, ClearTableActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                }
                catch (e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })

        btn_profile_mybest.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@ProfileActivity, MyBestActivity::class.java)
                try {
                    intent.putExtra("id", data.id)
                }
                catch(e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
        })
    }
}