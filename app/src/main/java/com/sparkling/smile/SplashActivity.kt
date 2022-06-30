package com.sparkling.smile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val i = Intent(this, ChooserActivity :: class.java)
                startActivity(i)
                finish()
            } else {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }, 3000)
    }
}