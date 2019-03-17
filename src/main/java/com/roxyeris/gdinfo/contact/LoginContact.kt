/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.contact

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONObject

interface LoginContact {
    interface View {
        var presenter:Presenter

        fun updateLoginValue(data: JSONObject)
    }
    interface Presenter {
        var view:View
        var client: GoogleApiClient
        //var client2: GoogleSignInClient
        var gso: GoogleSignInOptions

        fun signIn(ctx: Activity, mAuth: FirebaseAuth)
        //fun initSignIn(ctx:Context)
        fun getToken(ctx: Context, token:String)
    }
}