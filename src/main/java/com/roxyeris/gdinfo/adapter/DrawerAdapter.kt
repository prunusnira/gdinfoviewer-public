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

import java.util.ArrayList

class DrawerAdapter(private val context: Context, private val item: ArrayList<IntStringStructure>) : BaseAdapter() {
    var icon: ImageView? = null
    var content: TextView? = null

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
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            // Activity를 extend해야 사용 가능한 getSystemService를 Context를 이용하여 사용 가능 하도록 설정
            v = vi.inflate(R.layout.drawer_item, null)
        }
        icon = v!!.findViewById<View>(R.id.drawer_icon_id) as ImageView
        content = v.findViewById<View>(R.id.drawer_text_id) as TextView

        val data = item[position]

        icon!!.setImageResource(data.intValue)
        content!!.text = data.strValue

        return v
    }
}
