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
import com.roxyeris.gdinfo.data.RecentData

import java.util.ArrayList

class RecentAdapter(private val ctx: Context, private val item: ArrayList<RecentData>,
                    private val imageLoader: ImageLoader) : BaseAdapter() {
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
            val vi = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(R.layout.lv_recent_obj, null)
        }

        var name = v?.findViewById<View>(R.id.lv_rec_name) as TextView
        var title = v.findViewById<View>(R.id.lv_rec_towertitle) as ImageView
        var gf = v.findViewById<View>(R.id.lv_rec_gf) as TextView
        var dm = v.findViewById<View>(R.id.lv_rec_dm) as TextView
        var time = v.findViewById<View>(R.id.lv_rec_time) as TextView

        val current = item[position]

        if(current.towertitle != "") {
            imageLoader.displayImage(Const.ADDR_TITLE+current.towertitle+".png", title)
        }
        name.text = current.name
        gf.text = String.format("%.2f", current.gf?.toDouble())
        dm.text = String.format("%.2f", current.dm?.toDouble())
        Const.horizontalColorSetter(java.lang.Float.valueOf(current.gf), gf)
        Const.horizontalColorSetter(java.lang.Float.valueOf(current.dm), dm)
        time.text = current.date

        return v
    }
}
