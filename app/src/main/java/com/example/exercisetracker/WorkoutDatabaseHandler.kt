package com.example.exercisetracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

class WorkoutDatabaseHandler (context: Context): SQLiteOpenHelper(context,
    WorkoutDatabaseHandler.DATABASE_NAME,null,
    WorkoutDatabaseHandler.DATABASE_VERSION
){
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "WorkoutGoalDatabase"
        private val TABLE_CONTACTS = "WorkoutGoalTable"
        private val KEY_ID = "id"
        private val KEY_DATE = "date"
        private val KEY_EXERCISE = "exercise"
        private val KEY_HOURS = "hours"
        private val KEY_MINS = "mins"
        private val KEY_SECS = "secs"
        private val KEY_FEELING= "feeling"
        private val KEY_NOTES = "notes"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_EXERCISE + " TEXT," + KEY_HOURS + " TEXT,"+ KEY_MINS + " TEXT,"+ KEY_SECS+ " TEXT,"
                + KEY_FEELING + " TEXT," + KEY_NOTES + " TEXT" +  ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addWorkout(wrk: WorkoutModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_DATE, wrk.exerciseDate)
        contentValues.put(KEY_EXERCISE,wrk.exercise )
        contentValues.put(KEY_HOURS,wrk.durationHrs )
        contentValues.put(KEY_MINS, wrk.durationMin)
        contentValues.put(KEY_SECS,wrk.durationSec )
        contentValues.put(KEY_FEELING,wrk.feeling )
        contentValues.put(KEY_NOTES, wrk.notes)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun viewWorkout():List<WorkoutModelClass>{
        val wrkList:ArrayList<WorkoutModelClass> = ArrayList<WorkoutModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var exerciseDate: String
        var exercise: String
        var durationHrs: Int
        var durationMin: Int
        var durationSec: Double
        var feeling: String
        var notes: String
        if (cursor.moveToFirst()) {
            do {
                exerciseDate = cursor.getString(cursor.getColumnIndex("date"))
                exercise = cursor.getString(cursor.getColumnIndex("exercise"))
                durationHrs = cursor.getInt(cursor.getColumnIndex("hours"))
                durationMin = cursor.getInt(cursor.getColumnIndex("mins"))
                durationSec = cursor.getDouble(cursor.getColumnIndex("secs"))
                feeling = cursor.getString(cursor.getColumnIndex("feeling"))
                notes = cursor.getString(cursor.getColumnIndex("notes"))
                val wrk= WorkoutModelClass(exerciseDate = exerciseDate, exercise = exercise, durationHrs = durationHrs,
                    durationMin= durationMin, durationSec = durationSec, feeling = feeling, notes = notes)
                wrkList.add(wrk)
            } while (cursor.moveToNext())
        }
        return wrkList
    }

}