package com.sparkling.smile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class CourseActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    var value : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beginner_course)

        val module: String = intent.getStringExtra("module").toString()
        val buyBtn = findViewById<Button>(R.id.buyBtn)
        val assignment = findViewById<Button>(R.id.assignment)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = Firebase.auth.currentUser
        val userkey = user?.uid.toString()

        assignment.setOnClickListener {
            val uri: Uri = Uri.parse("https://sparklingsmiles.in/worksheets-for-eotm-app-1") // missing 'https://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        database.child(userkey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                value = snapshot.child(module).value.toString()

                recyclerview.layoutManager = LinearLayoutManager(this@CourseActivity)

                val data = ArrayList<ItemsViewModel>()

                if (module == "bm1"){
                    data.add(ItemsViewModel( "1", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V1%20ABCD", "M1V1 - ABCD","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V1%20ABCD.mp4"))
                    data.add(ItemsViewModel( "2", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V2%20EFGH", "M1V2 - EFGH", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V2%20EFGH.mp4"))
                    data.add(ItemsViewModel( "3", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V3%20ACTIVITIES", "M1V3 - Activities","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V3%20Act.mp4"))
                    data.add(ItemsViewModel( "4", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V4%20IJKL", "M1V4 - IJKL","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V4%20IJKL%20main.mp4"))
         //           data.add(ItemsViewModel( "5", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V4%20IJKL", "IJKL", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V4%20IJKL.mp4"))
                    data.add(ItemsViewModel( "5", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V5%20MNOP", "M1V5 - MNOP","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V5%20MNOP.mp4"))
                    data.add(ItemsViewModel( "6", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V6%20QRSTU", "M1V6 - QRSTU","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V6%20QRSTU.mp4"))
                    data.add(ItemsViewModel( "7", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V7%20VWXYZ", "M1V7 - VWXYZ","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V7%20VWXYZ.mp4?a=view"))
                    data.add(ItemsViewModel( "8", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M1V8%20ACTIVITIES%20", "M1V8 - Activities","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%201/M1V8%20activities.mp4"))
                }else if(module == "bm2"){
                    data.add(ItemsViewModel( "1", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2%20LISTENING", "Listening Activity","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2%20Listening%20Activity.mp4"))
                    data.add(ItemsViewModel( "2", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V1%20VOWELS", "M2V1 - Vowels", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V1%20vowels.mp4"))
                    data.add(ItemsViewModel("3", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V2%202L%20WORDS", "M2V2 - 2ls", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2v2%202ls%20comp.mp4"))
                    data.add(ItemsViewModel( "4", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V3%20CVC'A1'", "M2V3 - CVC a1","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V3%20cvc%20a1.mp4"))
                    data.add(ItemsViewModel( "5", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V4%20CVC'A2'", "M2V4 - CVC a2","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V4%20cvc%20a2.mp4"))
                    data.add(ItemsViewModel( "6", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V5%20CVC'E'", "M2V5 - CVC e", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V5%20cvc%20e.mp4"))
                    data.add(ItemsViewModel( "7", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V6%20CVC%20'I'", "M2V6 - CVC i","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V6%20cvc%20i.mp4"))
                    data.add(ItemsViewModel( "8", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V7%20CVC'O'", "M2V7 - CVC o","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V7%20cvc%20o.mp4"))
                    data.add(ItemsViewModel( "9", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M2V8%20CVC%20'U'", "M2V8 - CVC u","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%202/M2V8%20cvc%20u.mp4"))
                }else if(module == "bm3"){
                    data.add(ItemsViewModel( "1", "", "O 2","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M2V7%20cvc%20o_2.mp4"))
                    data.add(ItemsViewModel( "2", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V1%20MAGICAL%20'E'", "M3V1 - Magical E", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V1%20magical%20e%20.mp4"))
                    data.add(ItemsViewModel( "3", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V2%20COLORS", "M3V2 - Colors","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V2%20colors.mp4"))
                    data.add(ItemsViewModel( "4", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V3%20BLENDS%201", "M3V3 - Blends 1","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V3%20blends1.mp4"))
                    data.add(ItemsViewModel( "5", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V4%20PARTS%20OF%20BODY", "M3V4 - Parts of body", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V4%20parts%20of%20body.mp4"))
                    data.add(ItemsViewModel( "6", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V5%20BLENDS%202", "M3V5 - Blends B","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V5%20blends%20b.mp4"))
                    data.add(ItemsViewModel( "7", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V6%20LISTENING%20ACTIVITY", "M3V6 - Listening","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V6%20listening.mp4"))
                    data.add(ItemsViewModel( "8", "https://drive.smitanarangenglishforall.workers.dev/0:/basic/Thumbnails%20/M3V7%20FRUITS%20AND%20VEGETABLES?a=view", "M3V7 - Fruits and vegs","https://drive.smitanarangenglishforall.workers.dev/0:/basic/Module%203/M3V7%20fruits%20and%20vegs.mp4"))
                }


                val adapter = CustomAdapter(this@CourseActivity, data, module)
                recyclerview.adapter = adapter
                progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        if (value.equals("full")){
            buyBtn.visibility = View.GONE
        }

        buyBtn.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                getPremium(module)
            } else {
                login()
            }
        }

    }

    private fun getPremium(module : String){
        val i = Intent(this@CourseActivity, PaymentActivity::class.java)
        Log.d("course", "onCreate: $module")
        i.putExtra("module", module)
        i.putExtra("value", value)
        startActivity(i)
    }

    private fun login(){
        val dialog = Dialog(this@CourseActivity)
        dialog.setContentView(R.layout.login_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val buttonUnlock = dialog.findViewById<Button>(R.id.next)
        buttonUnlock.setOnClickListener {
            val i = Intent(this@CourseActivity, WelcomeActivity::class.java)
            startActivity(i)
            dialog.dismiss()
        }
        dialog.show()
    }

}


