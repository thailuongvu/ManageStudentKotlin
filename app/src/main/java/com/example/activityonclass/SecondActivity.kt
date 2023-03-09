package com.example.activityonclass

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    var tClass:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name:EditText=findViewById(R.id.name)
        val date:EditText=findViewById(R.id.date)
        tClass=findViewById(R.id.tclass)
        val radioGroup:RadioGroup=findViewById(R.id.radioGroup)
        var sex:String=""
        val intent = intent
        val index=intent.getIntExtra("keyIndex",0)



        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)

                sex=radio.getText().toString()



            })

        val people = intent!!.getSerializableExtra("key") as Student
        name.setText(people.name)
        date.setText(people.date)
        tClass!!.setText(people.tClass)
        tClass!!.setInputType(InputType.TYPE_NULL)
        val gender:String=people.sex
        if(gender=="Male")
        {
           findViewById<RadioButton>(R.id.choice1).isChecked = true

        }else if(gender=="Female"){
            val radio:RadioButton=findViewById(R.id.choice2)
            radio.isChecked=true
        }else{
            val radio:RadioButton=findViewById(R.id.choice3)
            radio.isChecked=true
        }
        tClass!!.setOnClickListener {
            val intent = Intent(this, ChooseClass::class.java)

            startActivityForResult(intent, 350)
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val fullName: String = name.getText().toString()
            val birthday:String=date.getText().toString()
            val classType:String=tClass!!.getText().toString()

            val replyIntent = Intent()

            val tmp=Student(fullName,classType,birthday,sex)
            replyIntent.putExtra("key2", tmp)
            replyIntent.putExtra("indexB",index)

            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val replyIntent = Intent()

            replyIntent.putExtra("indexD",index)

            setResult(500, replyIntent)
            finish()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 350) {
            if (resultCode === 800) {

                val classTmp = data!!.getStringExtra("key3")

                tClass!!.setText(classTmp)
//                val adapter=MyListAdapter(this, studentList)
//                simpleList!!.adapter=adapter
            }
        }

    }
}