package com.example.exercisetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.exercisetracker.databinding.ActivityWorkoutAddBinding

class WorkoutAdd : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    fun saveWorkout(view: View) {
        val date = binding.date.text.toString()
        val exercise = binding.exercise.text.toString()
        val hours = binding.hours.text.toString()
        val minutes = binding.minutes.text.toString()
        val seconds = binding.seconds.text.toString()
        val feeling = binding.feeling.text.toString()
        val notes = binding.notes.text.toString()
        val itIsA: TimeCheck= TimeCheck()

        val databaseHandler: WorkoutDatabaseHandler = WorkoutDatabaseHandler(this)
        if (date.trim() != "" && exercise.trim() != "" && hours.trim() != "" && minutes.trim() != ""
            && seconds.trim() != "" && feeling.trim() != "" && notes.trim() != ""
            && itIsA.validHours(hours) && itIsA.validMinutes(minutes) && itIsA.validSeconds(seconds)) {
            val status = databaseHandler.addWorkout(WorkoutModelClass(date,exercise,Integer.parseInt(hours),
                Integer.parseInt(minutes), seconds.toDouble(),feeling,notes))
            if (status > -1) {
                Toast.makeText(applicationContext, "workout save", Toast.LENGTH_LONG).show()
                binding.date.text.clear()
                binding.exercise.text.clear()
                binding.hours.text.clear()
                binding.minutes.text.clear()
                binding.seconds.text.clear()
                binding.feeling.text.clear()
                binding.notes.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "error",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun viewRecord(view: View) {
        val databaseHandler: WorkoutDatabaseHandler = WorkoutDatabaseHandler(this)
        val wrk: List<WorkoutModelClass> = databaseHandler.viewWorkout()
        val wrkArrayDate = Array<String>(wrk.size) { "null" }
        val wrkArrayExercise = Array<String>(wrk.size) { "null" }
        val wrkArrayHours = Array<String>(wrk.size) { "0" }
        val wrkArrayMins = Array<String>(wrk.size) { "0" }
        val wrkArraySecs = Array<String>(wrk.size) { "0.0" }
        val wrkArrayFeeling = Array<String>(wrk.size) { "null" }
        val wrkArrayNotes = Array<String>(wrk.size) { "null" }
        var index = 0
        for (e in wrk) {
            wrkArrayDate[index] = e.exerciseDate
            wrkArrayExercise[index] = e.exercise
            wrkArrayHours[index] = e.durationHrs.toString()
            wrkArrayMins[index] = e.durationMin.toString()
            wrkArraySecs[index] = e.durationSec.toString()
            wrkArrayFeeling[index] = e.feeling
            wrkArrayNotes[index] = e.notes
            index++
        }
        val myListAdapter = WorkoutListAdapter(this, wrkArrayDate, wrkArrayExercise, wrkArrayHours,
                                            wrkArrayMins, wrkArraySecs, wrkArrayFeeling, wrkArrayNotes)
        binding.listView.adapter = myListAdapter
    }

    fun goHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}