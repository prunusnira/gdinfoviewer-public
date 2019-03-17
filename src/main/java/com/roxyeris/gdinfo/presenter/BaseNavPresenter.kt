/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.roxyeris.gdinfo.contact.BaseNavContact

class BaseNavPresenter : BaseNavContact.Presenter {
    lateinit override var bview: BaseNavContact.View
    lateinit override var mAuth: FirebaseAuth

    override fun checkLoginStatus(pref:SharedPreferences) {
        var user = mAuth.currentUser

        if (user != null) {
            val userid = user.email!!.split("@")[0]
            if(pref.getString("googleid", "") != "") {
                bview.updateMenuLogined()
            }
            else {
                bview.updateMenuLogout()
            }
        } else {
            bview.updateMenuLogout()
        }
    }
}