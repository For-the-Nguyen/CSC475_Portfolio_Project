package com.example.exercisetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.exercisetracker.databinding.ActivityGoalAddCustomBinding


class GoalAddCustom : AppCompatActivity() {
    private lateinit var binding: ActivityGoalAddCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalAddCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun saveGoal(view: View) {
        val id = binding.id.text.toString()
        val name = binding.task.text.toString()
        val complete = binding.complete.text.toString()
        val databaseHandler: GoalDatabaseHandler = GoalDatabaseHandler(this)
        if (id.trim() != "" && name.trim() != "" && complete.trim() != "") {
            val status = databaseHandler.addCustomGoal(CustomGoalModelClass(id, name, complete))
            if (status > -1) {
                Toast.makeText(applicationContext, "task save", Toast.LENGTH_LONG).show()
                binding.id.text.clear()
                binding.task.text.clear()
                binding.complete.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "id or name or email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun viewRecord(view: View) {
        val databaseHandler: GoalDatabaseHandler = GoalDatabaseHandler(this)
        val emp: List<CustomGoalModelClass> = databaseHandler.viewCustomGoal()
        val empArrayId = Array<String>(emp.size) { "0" }
        val empArrayName = Array<String>(emp.size) { "null" }
        val empArrayEmail = Array<String>(emp.size) { "null" }
        var index = 0
        for (e in emp) {
            empArrayId[index] = e.dateMade
            empArrayName[index] = e.goalName
            empArrayEmail[index] = e.goalText
            index++
        }
        val myListAdapter = GoalListAdapter(this, empArrayId, empArrayName, empArrayEmail)
        binding.listView.adapter = myListAdapter
    }

    fun goHome(view:View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
