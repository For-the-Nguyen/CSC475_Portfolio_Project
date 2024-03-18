package com.example.exercisetracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

class GoalDatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "CustomGoalDatabase"
        private val TABLE_CONTACTS = "CustomGoalTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
        private val KEY_GOAL = "goal"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_GOAL + " TEXT" +  ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addCustomGoal(emp: CustomGoalModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.goalName)
        contentValues.put(KEY_EMAIL,emp.dateMade )
        contentValues.put(KEY_GOAL,emp.goalText )
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun viewCustomGoal():List<CustomGoalModelClass>{
        val empList:ArrayList<CustomGoalModelClass> = ArrayList<CustomGoalModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var dateMade: String
        var userName: String
        var userEmail: String
        if (cursor.moveToFirst()) {
            do {
                dateMade = cursor.getString(cursor.getColumnIndex("goal"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                val emp= CustomGoalModelClass(dateMade = userEmail, goalName = userName, goalText = dateMade)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun deleteCustomGoal(emp: CustomGoalModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.dateMade)
        val success = db.delete(TABLE_CONTACTS,"id="+emp.dateMade,null)
        db.close()
        return success
    }

}