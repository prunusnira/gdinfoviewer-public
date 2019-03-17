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

import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.data.PRData

import java.util.ArrayList

class PRAdapter(val ctx: Context, val item: ArrayList<PRData>, val p: Int) : BaseAdapter() {

    private var color: LinearLayout? = null
    private var num: TextView? = null
    private var name: TextView? = null
    private var stat: TextView? = null
    private var ivRank: ImageView? = null
    private var ivFC: ImageView? = null
    private var page:Int = p

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
            v = inflater.inflate(R.layout.lv_ptdrank_obj, null)
        }

        color = v!!.findViewById<View>(R.id.lo_prank_color) as LinearLayout
        num = v.findViewById<View>(R.id.tv_ptrank_num) as TextView
        name = v.findViewById<View>(R.id.tv_ptrank_name) as TextView
        stat = v.findViewById<View>(R.id.tv_ptrank_stat) as TextView
        ivRank = v.findViewById<View>(R.id.iv_ptrank_rank) as ImageView
        ivFC = v.findViewById<View>(R.id.iv_ptrank_fc) as ImageView

        val cur = item[position]

        Const.verticalColorSetter(cur.skill, color!!)
        num!!.text = (30 * (page - 1) + position + 1).toString()
        name!!.text = cur.name
        stat!!.text = ((cur.rate.toDouble() / 100).toString() + "% / "
                + String.format("%.2f", cur.skill.toDouble() / 100))

        val rank = cur.rank
        when (rank) {
            "EXC", "SS" -> ivRank!!.setImageResource(R.drawable.rank_ss)
            "S" -> ivRank!!.setImageResource(R.drawable.rank_s)
            "A" -> ivRank!!.setImageResource(R.drawable.rank_a)
            "B" -> ivRank!!.setImageResource(R.drawable.rank_b)
            "C" -> ivRank!!.setImageResource(R.drawable.rank_c)
            "D" -> ivRank!!.setImageResource(R.drawable.rank_d)
            "E" -> ivRank!!.setImageResource(R.drawable.rank_e)
        }

        if (cur.checkfc == "N") {
            ivFC!!.visibility = View.GONE
        } else if (cur.rank == "EXC") {
            ivFC!!.visibility = View.VISIBLE
            ivFC!!.setImageResource(R.drawable.exc)
        } else {
            ivFC!!.visibility = View.VISIBLE
        }

        return v
    }

    fun updatePage(p:Int) {
        page = p
    }
}
