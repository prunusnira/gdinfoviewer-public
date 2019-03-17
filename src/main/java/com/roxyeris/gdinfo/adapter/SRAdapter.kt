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
import android.widget.TextView
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.data.SRData
import java.util.ArrayList

class SRAdapter(val ctx: Context, val item: ArrayList<SRData>, var page: Int?, var gtype: String?) : BaseAdapter() {

    fun changeGtype(gtype: String) {
        this.gtype = gtype
    }

    override fun getCount(): Int {
        return item.size
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        if (v == null) {
            val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.lv_skillrank_obj, null)
        }
        val tvRank = v!!.findViewById<View>(R.id.tv_rank_num) as TextView
        val tvName = v.findViewById<View>(R.id.tv_rank_name) as TextView
        val tvSkill = v.findViewById<View>(R.id.tv_rank_skill) as TextView

        tvRank.text = (30 * (page!! - 1) + position + 1).toString()
        tvName.text = item[position].name
        if (gtype == "gf") {
            tvSkill.text = item[position].gskill.toString()
            Const.horizontalColorSetter(item[position].gskill, tvSkill)
        } else if (gtype == "dm") {
            tvSkill.text = item[position].dskill.toString()
            Const.horizontalColorSetter(item[position].dskill, tvSkill)
        }

        return v
    }
}