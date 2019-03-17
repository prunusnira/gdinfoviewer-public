/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.nostra13.universalimageloader.core.ImageLoader
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.data.SkillData
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.Converter

class SkillAdapter(ctx: Context, item:ArrayList<SkillData>, page:Int, imageLoader: ImageLoader): BaseAdapter() {
    val ctx = ctx
    val item = item
    var page = page
    val imageLoader = imageLoader

    override fun getItem(p0: Int): Any {
        return item.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return item.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var v = p1
        if(v == null) {
            val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.lv_skill_obj, null)
        }

        val loColor = v!!.findViewById(R.id.iv_skill_color) as LinearLayout
        val tvNumber = v.findViewById<View>(R.id.tv_skill_number) as TextView
        val ivJacket = v.findViewById<View>(R.id.iv_skill_jacket) as ImageView
        val tvName = v.findViewById<View>(R.id.tv_skill_title) as TextView
        val tvInfo = v.findViewById<View>(R.id.tv_skill_pattern) as TextView
        val ivRank = v.findViewById<View>(R.id.iv_skill_rank) as ImageView
        val ivFC = v.findViewById<View>(R.id.iv_skill_fc) as ImageView
        val tvRate = v.findViewById<View>(R.id.tv_skill_rate) as TextView

        val data = item.get(p0)
        tvNumber.text = (30 * (page - 1) + p0 + 1).toString()
        tvName.text = data.mname

        val ptcode = data.ptcode
        val level = data.level
        val ver = data.ver

        tvInfo.text = Converter.ptcodeConvert(ptcode) + " / " + (level.toFloat()/100) + " / " + Converter.verConvert(ver)
        imageLoader.displayImage(Const.ADDR_MUSIC + data.musicid + ".jpg", ivJacket)
        val rank = data.rank
        when(rank) {
            "EXC", "SS" -> ivRank.setImageResource(R.drawable.rank_ss)
            "S" -> ivRank.setImageResource(R.drawable.rank_s)
            "A" -> ivRank.setImageResource(R.drawable.rank_a)
            "B" -> ivRank.setImageResource(R.drawable.rank_b)
            "C" -> ivRank.setImageResource(R.drawable.rank_c)
            "D" -> ivRank.setImageResource(R.drawable.rank_d)
            "E" -> ivRank.setImageResource(R.drawable.rank_e)
        }

        if(data.checkfc == "N") ivFC.visibility = View.GONE
        else if(data.rank == "EXC") {
            ivFC.visibility = View.VISIBLE
            ivFC.setImageResource(R.drawable.exc)
        }
        else {
            ivFC.visibility = View.VISIBLE
        }
        tvRate.text = (data.rate.toDouble()/100).toString() + "% / " + String.format("%.2f", (data.skill/10000).toDouble()/100)

        Const.verticalColorSetter(data.skill/10000, loColor)

        return v
    }

    fun updatePage(page:Int) {
        this.page = page
    }
}