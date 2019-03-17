/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.preference.PreferenceManager
import android.widget.RemoteViews

import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.tool.Const

class WidgetProviderType3 : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val widget = ComponentName(context, WidgetProviderType3::class.java)
        val view = RemoteViews(context.packageName, R.layout.widget_skill_type2)

        // 버튼 처리
        val intent = Intent(context, javaClass)
        intent.action = Const.WIDGET_UPDATE
        val pintent = PendingIntent.getBroadcast(context, 0, intent, 0)
        view.setOnClickPendingIntent(R.id.widget_btn_update, pintent)
        appWidgetManager.updateAppWidget(widget, view)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val action = intent.action

        if (action == Const.WIDGET_UPDATE) {
            // Thread 업데이트 및 view 갱신
            val manager = AppWidgetManager.getInstance(context)

            val view = RemoteViews(context.packageName, R.layout.widget_skill_type2)
            val widget = ComponentName(context, WidgetProviderType3::class.java)

            view.setInt(R.id.widget_ll, "setBackgroundColor", Color.parseColor("#" + pref.getString("wdgBgColor", "88000000")!!))
            view.setTextViewText(R.id.widget_username, pref.getString("name", ""))
            view.setTextViewText(R.id.widget_gskill, pref.getString("gskill", ""))
            view.setTextViewText(R.id.widget_dskill, pref.getString("dskill", ""))

            manager.updateAppWidget(widget, view)
        }
    }
}
