/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.tool

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.LinearLayout
import android.widget.TextView

import com.roxyeris.gdinfo.R

object Const {
    val MAIN_TEST = "TEST_IP"
    val MAIN_LOCAL = "LOCAL_IP"
    val MAIN_RELEASE = "GDINFO_URL"
    val STATE = 2
    var MAIN = ""
    val CURRENTVER = 27

    val ADDR_BOARD = "GDINFO_URL"
    val ADDR_MUSIC = "GDINFO_URL"
    val ADDR_TITLE = "GDINFO_URL"
    val ADDR_INDEX = "recent"
    val ADDR_SKILL = "skill"
    val ADDR_RANK = "rank"
    val ADDR_PATTERN = "ptrank"
    val ADDR_PATTERNDETAIL = "ptdetail"
    val ADDR_MUSICPAGE = "music"
    val ADDR_SELFTOKEN = "getuser"
    val ADDR_USER_WITH_ID = "getuserid"
    val ADDR_CTABLE = "cleartable"
    val ADDR_MYBEST = "mybest"
    val ADDR_SEARCH = "search"
    val ADDR_TOWER = "towerdata"

    val WIDGET_UPDATE = "com.roxyeris.gdinfo.widget.WidgetUpdate"
    val RC_SIGN_IN = 0x1001;

    enum class TOWER_TYPE(val value:Int) {
        DKDK(0),
        LEFT(1),
        NOTE(2),
        DMFC(3),
        CHORD(4),
        ALTER(5),
        MIXED(6),
        GFFC(7),
        SPMODELDD(8)
    }

    init {
        if (STATE == 0) {
            MAIN = MAIN_TEST
        }
        if (STATE == 2) {
            MAIN = MAIN_RELEASE
        }
        if (STATE == 1) {
            MAIN = MAIN_LOCAL
        }
    }

    fun getResId(ctx: Context, resName: String): Int {
        val res = ctx.resources
        return res.getIdentifier(resName, "id", ctx.packageName)
    }

    fun verticalColorSetter(skill: Int, lo: LinearLayout) {
        if (skill < 2000)
            lo.setBackgroundResource(R.drawable.gra_10v)
        else if (skill < 3000)
            lo.setBackgroundResource(R.drawable.gra_20v)
        else if (skill < 4000)
            lo.setBackgroundResource(R.drawable.gra_30v)
        else if (skill < 5000)
            lo.setBackgroundResource(R.drawable.gra_40v)
        else if (skill < 6000)
            lo.setBackgroundResource(R.drawable.gra_50v)
        else if (skill < 7000)
            lo.setBackgroundResource(R.drawable.gra_60v)
        else if (skill < 8000)
            lo.setBackgroundResource(R.drawable.gra_70v)
        else if (skill < 9000)
            lo.setBackgroundResource(R.drawable.gra_80v)
        else if (skill < 10000)
            lo.setBackgroundResource(R.drawable.gra_90v)
        else if (skill < 11000)
            lo.setBackgroundResource(R.drawable.gra_100v)
        else if (skill < 12000)
            lo.setBackgroundResource(R.drawable.gra_110v)
        else if (skill < 13000)
            lo.setBackgroundResource(R.drawable.gra_120v)
        else if (skill < 14000)
            lo.setBackgroundResource(R.drawable.gra_130v)
        else if (skill < 15000)
            lo.setBackgroundResource(R.drawable.gra_140v)
        else if (skill < 16000)
            lo.setBackgroundResource(R.drawable.gra_150v)
        else if (skill < 17000)
            lo.setBackgroundResource(R.drawable.gra_160v)
        else if (skill < 18000)
            lo.setBackgroundResource(R.drawable.gra_170v)
        else if (skill < 19000)
            lo.setBackgroundResource(R.drawable.gra_180v)
        else
            lo.setBackgroundResource(R.drawable.gra_190v)
    }

    fun horizontalColorSetter(skill: Float, tv: TextView) {
        if (skill < 1000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(Color.WHITE, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 1500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x533d2, -0x533d2), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 2000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x533d2, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 2500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xa4, -0xa4), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 3000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xa4, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 3500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xcc0100, -0xcc0100), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 4000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xcc0100, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 4500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x8a6701, -0x8a6701), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 5000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x8a6701, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 5500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xff01, -0xff01), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 6000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0xff01, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 6500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(Color.RED, Color.RED), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 7000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(Color.RED, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 7500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x2277bc, Color.WHITE), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 8000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x777778, -0x222223), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 8500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x3c3d00, -0x49, -0x49), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 9000f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x10000, -0x100, -0x7f0100), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.REPEAT)
        else if (skill < 9500f)
            tv.paint.shader = LinearGradient(0f, 0f, tv.textSize, tv.lineHeight.toFloat(),
                    intArrayOf(-0x7f0100, -0xa75306, -0xfefe21), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.REPEAT)
        else
            tv.paint.shader = LinearGradient(0f, 0f, tv.width.toFloat(), tv.lineHeight.toFloat(),
                    intArrayOf(-0xa7a706, -0xffc0), floatArrayOf(0f, 1f), Shader.TileMode.REPEAT)
        //tv.setBackgroundResource(R.drawable.gra_190v);
    }
}
