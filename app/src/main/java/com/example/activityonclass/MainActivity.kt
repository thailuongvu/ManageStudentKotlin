package com.example.activityonclass

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    var simpleList:ListView?=null
    val name= arrayOf("Hieu","Thai","Minh")
    val tClass= arrayOf("19CLC","20CLC","21CLC")
    val date= arrayOf("19/12/2001","19/12/2002","19/12/2003")
    val sex= arrayOf("Male","Female","Male")

    private lateinit var studentList:ArrayList<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btnAdd:ImageButton = findViewById(R.id.addBtn)
        simpleList=findViewById(R.id.simpleListView)

        studentList=ArrayList()
    for(i in name.indices){
        val s=Student(name[i], tClass[i],date[i],sex[i])
        studentList.add(s)
    }
        val adapter=MyListAdapter(this, studentList)
        simpleList!!.adapter=adapter

        simpleList!!.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, SecondActivity::class.java)
            //intent.putExtra("key", "This is value")
            intent.putExtra("key",studentList[i])
            intent.putExtra("keyIndex",i)
            startActivityForResult(intent, 200)
        }


        btnAdd.setOnClickListener {
            val intent = Intent(this, AddStudent::class.java)
            //intent.putExtra("key", "This is value")
            startActivityForResult(intent, 300)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 300) {
            if (resultCode === 700) {

                val people = data!!.getSerializableExtra("key3") as Student

                studentList.add(people)
                val adapter=MyListAdapter(this, studentList)
                simpleList!!.adapter=adapter
            }
        }
        if(requestCode===200){
            if (resultCode === Activity.RESULT_OK) {
                val index=data!!.getIntExtra("indexB",0)

                val people = data!!.getSerializableExtra("key2") as Student

                studentList[index]=people

                val adapter=MyListAdapter(this, studentList)
                simpleList!!.adapter=adapter
            }else{
                val indexDel=data!!.getIntExtra("indexD",0)
                studentList.removeAt(indexDel)
                val adapter=MyListAdapter(this, studentList)
                simpleList!!.adapter=adapter
            }

        }
    }

}
