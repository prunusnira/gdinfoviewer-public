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
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.tool.IntStringStructure

class SettingAdapter(ctx: Context, item: ArrayList<IntStringStructure>): BaseAdapter() {
    private var item = ArrayList<IntStringStructure>()
    private var ctx:Context = ctx

    init {
        this.item.addAll(item);
        this.ctx = ctx;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val li = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = li.inflate(R.layout.drawer_item, parent, false)

        val icon = v.findViewById<ImageView>(R.id.drawer_icon_id) as ImageView
        val text = v.findViewById<TextView>(R.id.drawer_text_id) as TextView

        icon.setImageResource(item[position].intValue)
        text.setText(item[position].strValue)

        return v
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return item.size
    }

}