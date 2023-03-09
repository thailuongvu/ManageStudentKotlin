package com.example.activityonclass

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MyListAdapter(private val context: Activity, private val maintitles: ArrayList<Student>,
                    ):
    ArrayAdapter<Student>(context, R.layout.list_view, maintitles){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.layoutInflater
        val rowView:View =inflater.inflate(R.layout.list_view,null,true)
        val imageView:ImageView= rowView.findViewById(R.id.imageView)
        val name:TextView=rowView.findViewById(R.id.textView2)
        val tClass:TextView=rowView.findViewById(R.id.textView3)
        val date:TextView=rowView.findViewById(R.id.textView4)
        val sex:TextView=rowView.findViewById(R.id.textView5)
        name.text=maintitles[position].name
        tClass.text=maintitles[position].tClass
        date.text=maintitles[position].date
        sex.text=maintitles[position].sex

        return rowView
    }
    }

