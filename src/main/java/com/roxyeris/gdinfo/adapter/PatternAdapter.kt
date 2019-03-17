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
import com.roxyeris.gdinfo.data.PatternData

import java.util.ArrayList

class PatternAdapter(val ctx: Context, val item: ArrayList<PatternData>, val imageLoader: ImageLoader) : BaseAdapter() {

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
        var v: View? = convertView
        if (v == null) {
            val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.lv_pattern_obj, null)
        }

        val ivJacket = v!!.findViewById<View>(R.id.iv_pat_jacket) as ImageView
        val tvName = v.findViewById<View>(R.id.tv_pat_name) as TextView
        val tvGbsc = v.findViewById<View>(R.id.tv_pat_gbsc) as TextView
        val tvGadv = v.findViewById<View>(R.id.tv_pat_gadv) as TextView
        val tvGext = v.findViewById<View>(R.id.tv_pat_gext) as TextView
        val tvGmas = v.findViewById<View>(R.id.tv_pat_gmas) as TextView
        val tvBbsc = v.findViewById<View>(R.id.tv_pat_bbsc) as TextView
        val tvBadv = v.findViewById<View>(R.id.tv_pat_badv) as TextView
        val tvBext = v.findViewById<View>(R.id.tv_pat_bext) as TextView
        val tvBmas = v.findViewById<View>(R.id.tv_pat_bmas) as TextView
        val tvDbsc = v.findViewById<View>(R.id.tv_pat_dbsc) as TextView
        val tvDadv = v.findViewById<View>(R.id.tv_pat_dadv) as TextView
        val tvDext = v.findViewById<View>(R.id.tv_pat_dext) as TextView
        val tvDmas = v.findViewById<View>(R.id.tv_pat_dmas) as TextView

        val data = item[position]

        imageLoader.displayImage(Const.ADDR_MUSIC + data.mid + ".jpg", ivJacket)
        tvName.text = data.name
        tvGbsc.text = String.format("%.2f", data.gbsc.toDouble() / 100)
        tvGadv.text = String.format("%.2f", data.gadv.toDouble() / 100)
        tvGext.text = String.format("%.2f", data.gext.toDouble() / 100)
        tvGmas.text = String.format("%.2f", data.gmas.toDouble() / 100)
        tvBbsc.text = String.format("%.2f", data.bbsc.toDouble() / 100)
        tvBadv.text = String.format("%.2f", data.badv.toDouble() / 100)
        tvBext.text = String.format("%.2f", data.bext.toDouble() / 100)
        tvBmas.text = String.format("%.2f", data.bmas.toDouble() / 100)
        tvDbsc.text = String.format("%.2f", data.dbsc.toDouble() / 100)
        tvDadv.text = String.format("%.2f", data.dadv.toDouble() / 100)
        tvDext.text = String.format("%.2f", data.dext.toDouble() / 100)
        tvDmas.text = String.format("%.2f", data.dmas.toDouble() / 100)

        return v
    }
}
