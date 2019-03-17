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
import android.widget.TextView
import com.nostra13.universalimageloader.core.ImageLoader
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.Converter
import com.roxyeris.gdinfo.data.MusicData

class MusicAdapter(ctx: Context, item:ArrayList<MusicData>, mid:Int, imageLoader:ImageLoader) : BaseAdapter() {
    val ctx = ctx
    val item = item
    val mid = mid
    val imageLoader = imageLoader
    
    override fun getItem(p0: Int): Any {
        return item[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return item.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.lv_music_item, null)

        val ivJacket = v.findViewById<View>(R.id.iv_mdetail_jacket) as ImageView
        val tvPtype = v.findViewById<View>(R.id.tv_mdetail_ptype) as TextView
        val tvName = v.findViewById<View>(R.id.tv_mdetail_name) as TextView
        val tvComp = v.findViewById<View>(R.id.tv_mdetail_comp) as TextView
        val tvInfo = v.findViewById<View>(R.id.tv_mdetail_lv) as TextView

        val tvBscLevel = v.findViewById<View>(R.id.tv_music_bsclv) as TextView
        val tvBscClear = v.findViewById<View>(R.id.tv_music_bscclear) as TextView
        val tvBscCombo = v.findViewById<View>(R.id.tv_music_bsccombo) as TextView
        val tvBscScore = v.findViewById<View>(R.id.tv_music_bscscore) as TextView
        val tvBscRank = v.findViewById<View>(R.id.tv_music_bscrank) as TextView
        val tvBscRate = v.findViewById<View>(R.id.tv_music_bscrate) as TextView
        val tvBscSkill = v.findViewById<View>(R.id.tv_music_bscskill) as TextView
        val tvBscRatetb = v.findViewById<View>(R.id.tv_music_bscratetb) as TextView
        val tvBscRatetbre = v.findViewById<View>(R.id.tv_music_bscratetbre) as TextView
        val tvBscRatemx = v.findViewById<View>(R.id.tv_music_bscratemx) as TextView

        val tvAdvLevel = v.findViewById<View>(R.id.tv_music_advlv) as TextView
        val tvAdvClear = v.findViewById<View>(R.id.tv_music_advclear) as TextView
        val tvAdvCombo = v.findViewById<View>(R.id.tv_music_advcombo) as TextView
        val tvAdvScore = v.findViewById<View>(R.id.tv_music_advscore) as TextView
        val tvAdvRank = v.findViewById<View>(R.id.tv_music_advrank) as TextView
        val tvAdvRate = v.findViewById<View>(R.id.tv_music_advrate) as TextView
        val tvAdvSkill = v.findViewById<View>(R.id.tv_music_advskill) as TextView
        val tvAdvRatetb = v.findViewById<View>(R.id.tv_music_advratetb) as TextView
        val tvAdvRatetbre = v.findViewById<View>(R.id.tv_music_advratetbre) as TextView
        val tvAdvRatemx = v.findViewById<View>(R.id.tv_music_advratemx) as TextView

        val tvExtLevel = v.findViewById<View>(R.id.tv_music_extlv) as TextView
        val tvExtClear = v.findViewById<View>(R.id.tv_music_extclear) as TextView
        val tvExtCombo = v.findViewById<View>(R.id.tv_music_extcombo) as TextView
        val tvExtScore = v.findViewById<View>(R.id.tv_music_extscore) as TextView
        val tvExtRank = v.findViewById<View>(R.id.tv_music_extrank) as TextView
        val tvExtRate = v.findViewById<View>(R.id.tv_music_extrate) as TextView
        val tvExtSkill = v.findViewById<View>(R.id.tv_music_extskill) as TextView
        val tvExtRatetb = v.findViewById<View>(R.id.tv_music_extratetb) as TextView
        val tvExtRatetbre = v.findViewById<View>(R.id.tv_music_extratetbre) as TextView
        val tvExtRatemx = v.findViewById<View>(R.id.tv_music_extratemx) as TextView

        val tvMasLevel = v.findViewById<View>(R.id.tv_music_maslv) as TextView
        val tvMasClear = v.findViewById<View>(R.id.tv_music_masclear) as TextView
        val tvMasCombo = v.findViewById<View>(R.id.tv_music_mascombo) as TextView
        val tvMasScore = v.findViewById<View>(R.id.tv_music_masscore) as TextView
        val tvMasRank = v.findViewById<View>(R.id.tv_music_masrank) as TextView
        val tvMasRate = v.findViewById<View>(R.id.tv_music_masrate) as TextView
        val tvMasSkill = v.findViewById<View>(R.id.tv_music_masskill) as TextView
        val tvMasRatetb = v.findViewById<View>(R.id.tv_music_masratetb) as TextView
        val tvMasRatetbre = v.findViewById<View>(R.id.tv_music_masratetbre) as TextView
        val tvMasRatemx = v.findViewById<View>(R.id.tv_music_masratemx) as TextView
        
        val cur = item.get(p0)
        imageLoader.displayImage(Const.ADDR_MUSIC + mid + ".jpg", ivJacket)

        tvName.text = cur.mname
        tvComp.text = cur.composer
        tvInfo.text = Converter.verConvert(cur.ver)

        when(cur.ptype) {
            "g" -> tvPtype.text = "GUITAR"
            "b" -> tvPtype.text = "BASS"
            "d" -> tvPtype.text = "DRUM"
        }

        for(i in 0..cur.data!!.size - 1) {
            val cp = cur.data!![i]
            when(cp.ptcode) {
                1, 5, 9 -> {
                    tvBscClear.text = ctx.getString(R.string.tv_music_cleartime) + "\n" +
                            cp.cleartime + " / " + cp.playtime
                    tvBscCombo.text = ctx.getString(R.string.tv_music_combo) + "\n" +
                            cp.combo
                    tvBscLevel.text = "BAS\n" + cp.level.toDouble()/100
                    tvBscRank.text = ctx.getString(R.string.tv_music_rank) + "\n" +
                            cp.rank
                    tvBscRate.text = ctx.getString(R.string.tv_music_rate) + "\n" +
                            cp.rate.toDouble()/100 + "%"
                    tvBscRatetb.text = ctx.getString(R.string.tv_music_rate) + "(TB)\n" +
                            cp.ratetb.toDouble()/100 + "%"
                    tvBscRatetbre.text = ctx.getString(R.string.tv_music_rate) + "(TBRE)\n" +
                            cp.ratetbre.toDouble()/100 + "%"
                    tvBscRatemx.text = ctx.getString(R.string.tv_music_rate) + "(MX)\n" +
                            cp.ratemx.toDouble()/100 + "%"
                    tvBscScore.text = ctx.getString(R.string.tv_music_score) + "\n" +
                            cp.score
                    tvBscSkill.text = ctx.getString(R.string.tv_music_skill) + "\n" +
                            String.format("%.2f", Math.floor(cp.skill.toDouble()/10000)/100)
                }
                2, 6, 10 -> {
                    tvAdvClear.text = ctx.getString(R.string.tv_music_cleartime) + "\n" +
                            cp.cleartime + " / " + cp.playtime
                    tvAdvCombo.text = ctx.getString(R.string.tv_music_combo) + "\n" +
                            cp.combo
                    tvAdvLevel.text = "ADV\n" + cp.level.toDouble() / 100
                    tvAdvRank.text = ctx.getString(R.string.tv_music_rank) + "\n" +
                            cp.rank
                    tvAdvRate.text = ctx.getString(R.string.tv_music_rate) + "\n" +
                            cp.rate.toDouble() / 100 + "%"
                    tvAdvRatetb.text = ctx.getString(R.string.tv_music_rate) + "(TB)\n" +
                            cp.ratetb.toDouble() / 100 + "%"
                    tvAdvRatetbre.text = ctx.getString(R.string.tv_music_rate) + "(TBRE)\n" +
                            cp.ratetbre.toDouble() / 100 + "%"
                    tvAdvRatemx.text = ctx.getString(R.string.tv_music_rate) + "(MX)\n" +
                            cp.ratemx.toDouble() / 100 + "%"
                    tvAdvScore.text = ctx.getString(R.string.tv_music_score) + "\n" +
                            cp.score
                    tvAdvSkill.text = ctx.getString(R.string.tv_music_skill) + "\n" +
                            String.format("%.2f", Math.floor(cp.skill.toDouble()/10000)/100)
                }
                3, 7, 11 -> {
                    tvExtClear.text = ctx.getString(R.string.tv_music_cleartime) + "\n" +
                            cp.cleartime + " / " + cp.playtime
                    tvExtCombo.text = ctx.getString(R.string.tv_music_combo) + "\n" +
                            cp.combo
                    tvExtLevel.text = "EXT\n" + cp.level.toDouble() / 100
                    tvExtRank.text = ctx.getString(R.string.tv_music_rank) + "\n" +
                            cp.rank
                    tvExtRate.text = ctx.getString(R.string.tv_music_rate) + "\n" +
                            cp.rate.toDouble() / 100 + "%"
                    tvExtRatetb.text = ctx.getString(R.string.tv_music_rate) + "(TB)\n" +
                            cp.ratetb.toDouble() / 100 + "%"
                    tvExtRatetbre.text = ctx.getString(R.string.tv_music_rate) + "(TBRE)\n" +
                            cp.ratetbre.toDouble() / 100 + "%"
                    tvExtRatemx.text = ctx.getString(R.string.tv_music_rate) + "(MX)\n" +
                            cp.ratemx.toDouble() / 100 + "%"
                    tvExtScore.text = ctx.getString(R.string.tv_music_score) + "\n" +
                            cp.score
                    tvExtSkill.text = ctx.getString(R.string.tv_music_skill) + "\n" +
                            String.format("%.2f", Math.floor(cp.skill.toDouble()/10000)/100)
                }
                4, 8, 12 -> {
                    tvMasClear.text = ctx.getString(R.string.tv_music_cleartime) + "\n" +
                            cp.cleartime + " / " + cp.playtime
                    tvMasCombo.text = ctx.getString(R.string.tv_music_combo) + "\n" +
                            cp.combo
                    tvMasLevel.text = "MAS\n" + cp.level.toDouble() / 100
                    tvMasRank.text = ctx.getString(R.string.tv_music_rank) + "\n" +
                            cp.rank
                    tvMasRate.text = ctx.getString(R.string.tv_music_rate) + "\n" +
                            cp.rate.toDouble() / 100 + "%"
                    tvMasRatetb.text = ctx.getString(R.string.tv_music_rate) + "(TB)\n" +
                            cp.ratetb.toDouble() / 100 + "%"
                    tvMasRatetbre.text = ctx.getString(R.string.tv_music_rate) + "(TBRE)\n" +
                            cp.ratetbre.toDouble() / 100 + "%"
                    tvMasRatemx.text = ctx.getString(R.string.tv_music_rate) + "(MX)\n" +
                            cp.ratemx.toDouble() / 100 + "%"
                    tvMasScore.text = ctx.getString(R.string.tv_music_score) + "\n" +
                            cp.score
                    tvMasSkill.text = ctx.getString(R.string.tv_music_skill) + "\n" +
                            String.format("%.2f", Math.floor(cp.skill.toDouble()/10000)/100)
                }
            }
        }

        return v
    }
}