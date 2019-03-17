/**********************************************
 * GITADORA Info Viewer
 * Developed by Tae Jun Kang a.k.a Prunus Nira
 * (c) Nira 2017
 *
 * This project is under GNU GPL v3.0
 **********************************************/
package com.roxyeris.gdinfo.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.roxyeris.gdinfo.R
import com.roxyeris.gdinfo.contact.LoginContact
import com.roxyeris.gdinfo.tool.Const
import com.roxyeris.gdinfo.presenter.LoginPresenter
import kotlinx.android.synthetic.main.lo_baseact.*
import kotlinx.android.synthetic.main.lo_login.*
import org.json.JSONObject
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import java.security.MessageDigest


class LoginActivity: BaseNavActivity(), LoginContact.View {
    lateinit override var presenter:LoginContact.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.lo_login, frameMain)
        presenter = LoginPresenter().apply {
            view = this@LoginActivity
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("REQUEST_TOKEN_HERE")
                    .requestEmail().build()
            client = GoogleApiClient.Builder(this@LoginActivity)
                    .enableAutoManage(this@LoginActivity, object:GoogleApiClient.OnConnectionFailedListener {
                        override fun onConnectionFailed(p0: ConnectionResult) {
                            Log.e("GITADORA Info", "Login failed")
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()
            //client2 = GoogleSignIn.getClient(this@LoginActivity, gso)
        }
        //presenter.initSignIn(this)
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Const.RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
            /*val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)*/
        }
        else {
            loginFailureDialog(-1)
        }
    }

    fun initView() {
        sign_in_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                presenter.signIn(this@LoginActivity, bpresenter.mAuth)
            }
        })
    }

    fun handleSignInResult(result:GoogleSignInResult) {
        if(result.isSuccess) {
            val account = result.signInAccount
            val editor = pref!!.edit()
            val userid = account?.email!!.split("@")[0]
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(userid.toString().toByteArray())
            val token = digest.fold("",  { str, it -> str + "%02x".format(it)})
            // SHA256 code from https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25

            editor.putString("googleid", userid)
            editor.putString("token", token)
            editor.commit()

            // 토큰 아이디 받아오기
            presenter.getToken(this, token)
            firebaseAuthWithGoogle(account)

            finish()
        }
        else {
            loginFailureDialog(result.status.statusCode)
        }
    }

    /*fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            val currentUser = bpresenter.mAuth.currentUser
            firebaseAuthWithGoogle(account)
        } catch(e:ApiException) {
            //loginFailureDialog(task.exception.message)
            e.printStackTrace()
        }
    }*/

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        bpresenter.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this@LoginActivity, object:OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if(task.isSuccessful) {
                            val user = bpresenter.mAuth.currentUser

                            val editor = pref!!.edit()
                            val userid = user?.email!!.split("@")[0]
                            val md = MessageDigest.getInstance("SHA-256")
                            val digest = md.digest(userid.toByteArray())
                            val token = digest.fold("",  { str, it -> str + "%02x".format(it)})
                            // SHA256 code from https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25

                            editor.putString("googleid", userid)
                            editor.putString("token", token)
                            editor.commit()

                            // 토큰 아이디 받아오기
                            presenter.getToken(this@LoginActivity, token)
                        }
                        else {
                            loginFailureDialog(-1)
                        }
                    }
                })
    }

    override fun updateLoginValue(data:JSONObject) {
        val editor = pref!!.edit()
        editor.putInt("numid", data.getInt("id"))
        editor.putString("title", data.getString("title"))
        editor.putString("name", data.getString("name"))
        editor.putString("gskill", data.getString("gskill"))
        editor.putString("dskill", data.getString("dskill"))
        editor.commit()
    }

    fun loginFailureDialog(code:Int) {
        // 로그인 실패 UI
        val dlg = AlertDialog.Builder(this)
        dlg.setTitle(R.string.tv_login_fail_title)
        if(code == -1) {
            dlg.setMessage(R.string.tv_login_fail_cont_unknown)
        }
        else {
            dlg.setMessage(getString(R.string.tv_login_fail_cont) + "\n\nERROR CODE: " + code.toString())
        }
        dlg.setNeutralButton(R.string.ok) {
            dialogInterface, _ -> dialogInterface.dismiss()
            finish()
        }
        dlg.create().show()
    }
}