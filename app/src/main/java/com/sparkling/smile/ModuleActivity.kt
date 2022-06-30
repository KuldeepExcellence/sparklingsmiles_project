package com.sparkling.smile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ModuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        val moduleOne : LinearLayout = findViewById(R.id.moduleOne)
        val moduleTwo : LinearLayout = findViewById(R.id.moduleTwo)
        val moduleThree : LinearLayout = findViewById(R.id.moduleThree)
      //  val moduleFour : LinearLayout = findViewById(R.id.moduleFour)
        val spoken : TextView = findViewById(R.id.spoken)

        val path: String? = intent.getStringExtra("path")

        if (path.equals("basic")){
            spoken.text = "My First Step"
        }else if (path.equals("beginner")){
            spoken.text = "I've Come So Far"
        }else if (path.equals("intermediate")){
            spoken.text = "About To Reach"
        } else if (path.equals("expert")){
            spoken.text = "Almost Done"
        } else if (path.equals("final")){
            spoken.text = "I Did It"
        }

        moduleOne.setOnClickListener {
            if (path.equals("basic")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "bm1")
                startActivity(intent)
            }else if (path.equals("beginner")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sbm1")
                startActivity(intent)
            }else if (path.equals("intermediate")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sim1")
                startActivity(intent)
            } else if (path.equals("expert")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sem1")
                startActivity(intent)
            } else if (path.equals("final")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sfm1")
                startActivity(intent)
            }
        }

        moduleTwo.setOnClickListener {
            if (path.equals("basic")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "bm2")
                startActivity(intent)
            }else if (path.equals("beginner")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sbm2")
                startActivity(intent)
            }else if (path.equals("intermediate")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sim2")
                startActivity(intent)
            } else if (path.equals("expert")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sem2")
                startActivity(intent)
            }else if (path.equals("final")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sfm2")
                startActivity(intent)
            }
        }
        moduleThree.setOnClickListener {
            if (path.equals("basic")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "bm3")
                startActivity(intent)
            }else if (path.equals("beginner")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sbm3")
                startActivity(intent)
            }else if (path.equals("intermediate")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sim3")
                startActivity(intent)
            } else if (path.equals("expert")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sem3")
                startActivity(intent)
            } else if (path.equals("final")){
                val intent = Intent(this, CourseActivity :: class.java)
                intent.putExtra("module", "sfm3")
                startActivity(intent)
            }
        }

//        moduleFour.setOnClickListener {
//            if (path.equals("basic")){
//                val intent = Intent(this, CourseActivity :: class.java)
//                intent.putExtra("module", "bm4")
//                startActivity(intent)
//            }else if (path.equals("beginner")){
//                val intent = Intent(this, CourseActivity :: class.java)
//                intent.putExtra("module", "sbm4")
//                startActivity(intent)
//            }else if (path.equals("intermediate")){
//                val intent = Intent(this, CourseActivity :: class.java)
//                intent.putExtra("module", "sim4")
//                startActivity(intent)
//            } else if (path.equals("expert")){
//                val intent = Intent(this, CourseActivity :: class.java)
//                intent.putExtra("module", "sem4")
//                startActivity(intent)
//            } else if (path.equals("final")){
//                val intent = Intent(this, CourseActivity :: class.java)
//                intent.putExtra("module", "sfm4")
//                startActivity(intent)
//            }
//        }

    }
}

