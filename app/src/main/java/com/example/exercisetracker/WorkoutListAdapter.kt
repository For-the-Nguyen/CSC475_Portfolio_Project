package com.example.exercisetracker

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class WorkoutListAdapter(private val context: Activity, private val date: Array<String>, private val exercise: Array<String>, private val hours: Array<String>,
                         private val minutes: Array<String>, private val seconds: Array<String>, private val feeling: Array<String>,
                         private val notes: Array<String>)
    : ArrayAdapter<String>(context, R.layout.workout_list, exercise) {//exercise might have an issue here

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.workout_list, null, true)

        val dateText = rowView.findViewById(R.id.textViewDate) as TextView
        val exerciseText = rowView.findViewById(R.id.textViewExercise) as TextView
        val durationText = rowView.findViewById(R.id.textViewDuration) as TextView
        val feelingText = rowView.findViewById(R.id.textViewFeeling) as TextView
        val notesText = rowView.findViewById(R.id.textViewNotes) as TextView

        dateText.text = "Date: ${date[position]}"
        exerciseText.text = "Exercise: ${exercise[position]}"
        durationText.text = "Duration: ${hours[position]}"+":${minutes[position]}"+":${seconds[position]}"
        feelingText.text = "Feeling: ${feeling[position]}"
        notesText.text = "Notes: ${notes[position]}"
        return rowView
    }
}