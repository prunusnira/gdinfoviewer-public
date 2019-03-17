/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth

interface BaseNavContact {
    interface View {
        var bpresenter: Presenter

        fun updateMenuLogined()
        fun updateMenuLogout()
    }

    interface Presenter {
        var bview: View
        var mAuth: FirebaseAuth

        fun checkLoginStatus(pref:SharedPreferences)
    }
}