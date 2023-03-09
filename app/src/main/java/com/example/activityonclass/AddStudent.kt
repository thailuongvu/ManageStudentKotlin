package com.example.activityonclass

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*

class AddStudent : AppCompatActivity() {
    var tClass: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        val name: EditText =findViewById(R.id.name)
        val date: EditText =findViewById(R.id.date)
        tClass =findViewById(R.id.tclass)
        val radioGroup: RadioGroup =findViewById(R.id.radioGroup)
        var sex:String=""
        val intent = intent
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)

                sex=radio.getText().toString()



            })
        tClass!!.setInputType(InputType.TYPE_NULL)
        tClass!!.setOnClickListener {
            val intent = Intent(this, ChooseClass::class.java)

            startActivityForResult(intent, 250)
        }


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val fullName: String = name.getText().toString()
            val birthday:String=date.getText().toString()
            val classType:String=tClass!!.getText().toString()

            val replyIntent = Intent()

            val tmp=Student(fullName,classType,birthday,sex)
            replyIntent.putExtra("key3", tmp)


            setResult(700, replyIntent)
            finish()
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 250) {
            if (resultCode === 800) {

                val classTmp = data!!.getStringExtra("key3")

                tClass!!.setText(classTmp)
//                val adapter=MyListAdapter(this, studentList)
//                simpleList!!.adapter=adapter
            }
        }

    }

}