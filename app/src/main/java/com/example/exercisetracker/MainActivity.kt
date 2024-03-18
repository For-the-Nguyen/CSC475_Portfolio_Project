package com.example.exercisetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var home: TextView
    private lateinit var homeToAddWorkout: Button
    private lateinit var homeToViewWorkout: Button
    private lateinit var homeToAddGoal: Button
    private lateinit var homeToViewGoal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeToAddWorkout = findViewById(R.id.addWorkoutButton)
        homeToAddGoal = findViewById(R.id.addGoalButton)
        home = findViewById(R.id.homeTitle)

        home.text = "Exercise Tracker".trimIndent()

        homeToAddWorkout.setOnClickListener {
            val intent = Intent(this, WorkoutAdd::class.java)
            startActivity(intent)
        }

        homeToAddGoal.setOnClickListener {
            val intent = Intent(this, GoalAddCustom::class.java)
            startActivity(intent)
        }

    }
}

