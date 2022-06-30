package com.sparkling.smile

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    private var plan = 0
    var mode: String = ""
    var module: String = ""

    val TAG:String = PaymentActivity::class.toString()
    private lateinit var database : DatabaseReference

    private lateinit var auth: FirebaseAuth
    var value : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        Checkout.preload(applicationContext)

        auth = FirebaseAuth.getInstance()
        module = intent.getStringExtra("module").toString()
        value =  intent.getStringExtra("value").toString()

        Log.d("PayMod", "onCreate: " + module)


        val nextBtn : Button = findViewById(R.id.btnNext)
        val firstPlan : MaterialCardView = findViewById(R.id.firstPlan)
        val secondPlan : MaterialCardView = findViewById(R.id.secondPlan)
        val thirdPlan : MaterialCardView = findViewById(R.id.thirdPlan)

        val firstPlanTitle : TextView = findViewById(R.id.firstPlanTitle)
        val firstPlanSign : TextView = findViewById(R.id.firstPlanSign)
        val firstPlanPrice : TextView = findViewById(R.id.firstPlanPrice)

        val secondPlanTitle : TextView = findViewById(R.id.secondPlanTitle)
        val secondPlanSign : TextView = findViewById(R.id.secondPlanSign)
        val secondPlanPrice : TextView = findViewById(R.id.secondPlanPrice)

        val thirdPlanTitle : TextView = findViewById(R.id.thirdPlanTitle)
        val thirdPlanSign : TextView = findViewById(R.id.thirdPlanSign)
        val thirdPlanPrice : TextView = findViewById(R.id.thirdPlanPrice)

        Log.d("value", "onCreate: " + value)
        if(value.equals("half")){
            firstPlan.visibility = View.GONE
            thirdPlan.visibility = View.GONE
            secondPlan.setOnClickListener {
                secondPlan.setCardBackgroundColor(Color.parseColor("#6D4A73"))
                secondPlanTitle.setTextColor(Color.parseColor("#FFFFFF"))
                secondPlanSign.setTextColor(Color.parseColor("#FFFFFF"))
                secondPlanPrice.setTextColor(Color.parseColor("#FFFFFF"))
                firstPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                firstPlanTitle.setTextColor(Color.parseColor("#000000"))
                firstPlanSign.setTextColor(Color.parseColor("#000000"))
                firstPlanPrice.setTextColor(Color.parseColor("#000000"))
                thirdPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                thirdPlanTitle.setTextColor(Color.parseColor("#000000"))
                thirdPlanSign.setTextColor(Color.parseColor("#000000"))
                thirdPlanPrice.setTextColor(Color.parseColor("#000000"))
                plan = 2
                mode = "full"
            }
        }else{
            firstPlan.setOnClickListener {
                firstPlan.setCardBackgroundColor(Color.parseColor("#6D4A73"))
                firstPlanTitle.setTextColor(Color.parseColor("#FFFFFF"))
                firstPlanSign.setTextColor(Color.parseColor("#FFFFFF"))
                firstPlanPrice.setTextColor(Color.parseColor("#FFFFFF"))
                secondPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                secondPlanTitle.setTextColor(Color.parseColor("#000000"))
                secondPlanSign.setTextColor(Color.parseColor("#000000"))
                secondPlanPrice.setTextColor(Color.parseColor("#000000"))
                thirdPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                thirdPlanTitle.setTextColor(Color.parseColor("#000000"))
                thirdPlanSign.setTextColor(Color.parseColor("#000000"))
                thirdPlanPrice.setTextColor(Color.parseColor("#000000"))
                plan = 1
                mode="half"
            }

            secondPlan.visibility = View.GONE

            thirdPlan.setOnClickListener {
                thirdPlan.setCardBackgroundColor(Color.parseColor("#6D4A73"))
                thirdPlanTitle.setTextColor(Color.parseColor("#FFFFFF"))
                thirdPlanSign.setTextColor(Color.parseColor("#FFFFFF"))
                thirdPlanPrice.setTextColor(Color.parseColor("#FFFFFF"))
                firstPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                firstPlanTitle.setTextColor(Color.parseColor("#000000"))
                firstPlanSign.setTextColor(Color.parseColor("#000000"))
                firstPlanPrice.setTextColor(Color.parseColor("#000000"))
                secondPlan.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                secondPlanTitle.setTextColor(Color.parseColor("#000000"))
                secondPlanSign.setTextColor(Color.parseColor("#000000"))
                secondPlanPrice.setTextColor(Color.parseColor("#000000"))
                plan = 3
                mode = "full"
            }

        }

        nextBtn.setOnClickListener {
            when (plan){
                1 -> startPayment("First four video of module", "19900","gaurav@example.com")
                2 -> startPayment("Second four video of module", "29900","gaurav@example.com")
                3 -> startPayment("All videos of module", "49900","gaurav@example.com")
                else -> {
                    Toast.makeText(this,"Please choose a plan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startPayment(desc: String, amount: String, email: String) {

        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","eotM")
            options.put("description",desc)
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#E3F2FD");
            options.put("currency","INR");
            options.put("amount",amount)//pass amount in currency subunits

            val prefill = JSONObject()
            prefill.put("email",email)
            prefill.put("contact","9814941771")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = auth.currentUser
        val userkey = user?.uid.toString()

        database.child(userkey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    database.child(userkey).child(module).setValue(mode)
                }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onPaymentError(p0: Int, p1: String?) {
    }
}