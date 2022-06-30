package com.sparkling.smile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.auth.FirebaseAuth


class ChooserActivity : AppCompatActivity() {

    lateinit var adRequest: AdRequest
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)

        val basicPath : LinearLayout = findViewById(R.id.basicPath)
        val beginnerPath : LinearLayout = findViewById(R.id.beginnerPath)
        val interPath : LinearLayout = findViewById(R.id.interPath)
        val expertPath : LinearLayout = findViewById(R.id.expertPath)
        val finalPath : LinearLayout = findViewById(R.id.finalPath)
        val logout : Button = findViewById(R.id.logout)
        val next: ImageButton = findViewById(R.id.threedot);

        next.setOnClickListener {
            startActivity(Intent(this,profiledetails::class.java))
        }



        MobileAds.initialize(this) {
            adRequest = AdRequest.Builder().build()
            loadads()
        }

        basicPath.setOnClickListener {
            val i = Intent(this, ModuleActivity :: class.java).also {
                it.putExtra("path", "basic")
                startActivity(it)
            }
        }
        beginnerPath.setOnClickListener {
            val i = Intent(this, ModuleActivity :: class.java).also {
                it.putExtra("path", "beginner")
                startActivity(it)
            }

        }
        interPath.setOnClickListener {
            val i = Intent(this, ModuleActivity :: class.java).also {
                it.putExtra("path", "intermediate")
                startActivity(it)
            }
        }
        expertPath.setOnClickListener {
            val i = Intent(this, ModuleActivity :: class.java).also {
                it.putExtra("path", "expert")
                startActivity(it)
            }
        }

        finalPath.setOnClickListener {
            val i = Intent(this, ModuleActivity :: class.java).also {
                it.putExtra("path", "final")
                startActivity(it)
            }
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            val i = Intent(this, WelcomeActivity :: class.java).also {
                startActivity(it)
            }
        }
    }

    private fun loadads() {
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    mInterstitialAd = interstitialAd
                    if (mInterstitialAd != null) {
                        mInterstitialAd!!.show(this@ChooserActivity)
                        mInterstitialAd!!.setFullScreenContentCallback(object :
                            FullScreenContentCallback() {
                            override fun onAdClicked() {
                                super.onAdClicked()
                            }

                            override fun onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent()

                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                super.onAdFailedToShowFullScreenContent(adError)
                            }

                            override fun onAdImpression() {
                                super.onAdImpression()
                            }

                            override fun onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent()
                            }
                        })
                    }
                }
            })
    }
}