package com.example.exercisetracker

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GoalListAdapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val email: Array<String>)
    : ArrayAdapter<String>(context, R.layout.goal_custom_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.goal_custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val emailText = rowView.findViewById(R.id.textViewEmail) as TextView

        idText.text = "Goal Name: ${id[position]}"
        nameText.text = "Goal: ${name[position]}"
        emailText.text = "Target Date: ${email[position]}"
        return rowView
    }
}