/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.presenter

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.roxyeris.gdinfo.contact.LoginContact
import com.roxyeris.gdinfo.receiver.LoginReceiver
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.tool.ThreadRunner

class LoginPresenter: LoginContact.Presenter {
    lateinit override var view: LoginContact.View
    lateinit override var client: GoogleApiClient
    //lateinit override var client2: GoogleSignInClient
    lateinit override var gso: GoogleSignInOptions


    /*override fun initSignIn(ctx:Context) {


    }*/

    override fun signIn(ctx: Activity, mAuth: FirebaseAuth) {
        ctx.startActivityForResult(
                Auth.GoogleSignInApi.getSignInIntent(client),
                Const.RC_SIGN_IN
        )

        /*ctx.startActivityForResult(
                client2.signInIntent,
                Const.RC_SIGN_IN
        )*/
    }

    override fun getToken(ctx: Context, token:String) {
        val receiver = LoginReceiver(ctx, view)
        Thread(ThreadRunner(getUrl(token), receiver, ctx)).start()
    }

    fun getUrl(token:String):String {
        return Const.MAIN + Const.ADDR_SELFTOKEN + "/" + token
    }
}