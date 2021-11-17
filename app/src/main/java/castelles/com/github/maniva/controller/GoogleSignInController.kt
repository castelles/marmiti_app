package castelles.com.github.maniva.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import castelles.com.github.maniva.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleSignInController(private val resultHandler: SignInCallback) {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    fun buildSignInScope(context: Context) {
        val googleLoginOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(
            context,
            googleLoginOptions
        )
    }

    fun getLastAccount(context: Context, onRetrieveAccount: (GoogleSignInAccount) -> Unit) {
        GoogleSignIn.getLastSignedInAccount(context)?.let {
            onRetrieveAccount(it)
        }
    }

    fun executeSingInTask(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        handleSignInResult(task)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            resultHandler.onSuccess(account)
        } catch (e: ApiException) {
            resultHandler.onFailure(e)
        }
    }

    fun signIn(activity: Activity) {
        val singInIntent = mGoogleSignInClient.signInIntent
        activity.startActivityForResult(singInIntent, RC_SIGN_IN)
    }

    fun signOut(onSignOut: (Task<Void>) -> Unit) {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            onSignOut(it)
        }
    }

    interface SignInCallback {
        fun onSuccess(account: GoogleSignInAccount?)
        fun onFailure(error: ApiException)
    }

    companion object {
        const val RC_SIGN_IN = 200
    }
}