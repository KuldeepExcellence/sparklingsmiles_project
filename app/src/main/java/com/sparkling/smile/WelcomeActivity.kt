package com.sparkling.smile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*


class WelcomeActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val share :Button = findViewById(R.id.sahre_apk)

        //    Share Apk
        share.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val abc = "Download this app"
            val sub = "https://play.google.com"
            intent.putExtra(Intent.EXTRA_TEXT, abc)
            intent.putExtra(Intent.EXTRA_TEXT, sub)
            startActivity(Intent.createChooser(intent, "Share using"))
        }

        auth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val button : Button = findViewById(R.id.google_sign_in_button_top)
        val guest : Button = findViewById(R.id.guest_button)

        button.setOnClickListener {
            signIn()
        }

        guest.setOnClickListener {
            val i = Intent(this, ChooserActivity :: class.java)
            startActivity(i)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val user = auth.currentUser
                    val userkey = user?.uid.toString()

                    database.child(userkey).addValueEventListener(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot.exists()) {
                                Log.d("userCheck", "onDataChange: User exists")
                            } else {
                                database.child(userkey).child("bm1").setValue("")
                                database.child(userkey).child("bm2").setValue("")
                                database.child(userkey).child("bm3").setValue("")
                                database.child(userkey).child("bm4").setValue("")
                                database.child(userkey).child("sbm1").setValue("")
                                database.child(userkey).child("sbm2").setValue("")
                                database.child(userkey).child("sbm3").setValue("")
                                database.child(userkey).child("sbm4").setValue("")
                                database.child(userkey).child("sim1").setValue("")
                                database.child(userkey).child("sim2").setValue("")
                                database.child(userkey).child("sim3").setValue("")
                                database.child(userkey).child("sim4").setValue("")
                                database.child(userkey).child("sem1").setValue("")
                                database.child(userkey).child("sem2").setValue("")
                                database.child(userkey).child("sem3").setValue("")
                                database.child(userkey).child("sem4").setValue("")
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }

                    })

                    val i = Intent(this, ChooserActivity :: class.java)
                    startActivity(i)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                }
            }
    }


}