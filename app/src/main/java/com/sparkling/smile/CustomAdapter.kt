package com.sparkling.smile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class CustomAdapter(val context: Context, private val mList: List<ItemsViewModel>, val module: String) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var database : DatabaseReference
    private var value : String? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_list_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.countView.text = ItemsViewModel.count
        Glide.with(context).load(ItemsViewModel.image).into(holder.imageView);
        holder.textView.text = ItemsViewModel.text

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = Firebase.auth.currentUser
        val userkey = user?.uid.toString()

        database.child(userkey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                value = snapshot.child(module).value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        holder.rootView.setOnClickListener {
            if (value == "full"){
                val i = Intent(context, VideoActivity::class.java)
                context.startActivity(i)
            }else if(value == "half" || module == "bm1"){
                if (position>=4){
                    Toast.makeText(context,"Please buy the full pack to unlock the content", Toast.LENGTH_SHORT).show()
                }else{
                    val i = Intent(context, VideoActivity::class.java)
                    context.startActivity(i)
                }
            }else{
                Toast.makeText(context,"Please purchase the pack to unlock the content", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val rootView: LinearLayout = itemView.findViewById(R.id.rootView)
        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)
        val textView: TextView = itemView.findViewById(R.id.title)
        val countView : TextView = itemView.findViewById(R.id.counter)


    }
}
