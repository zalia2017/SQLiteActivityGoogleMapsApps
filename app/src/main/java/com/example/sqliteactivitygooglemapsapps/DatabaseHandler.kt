package com.example.sqliteactivitygooglemapsapps

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "MyActivityDatabase"
        private val TABLE_ACTIVITIES = "MyActivityTable"

        private val KEY_ID = "_id"
        private val ACTIVITY_NAME = "activity_name"
        private val ACTIVITY_TIME = "activity_time"
        private val ACTIVITY_ADDRESS = "activity_address"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ACTIVITIES_TABLE = ("CREATE TABLE ${TABLE_ACTIVITIES}" +
                "('${KEY_ID}' INTEGER PRIMARY KEY, '${ACTIVITY_NAME}' TEXT, '${ACTIVITY_TIME}' TEXT, " +
                "'${ACTIVITY_ADDRESS}' TEXT)")
        db?.execSQL(CREATE_ACTIVITIES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIVITIES")
        onCreate(db)
    }

    /**
     * Method to insert data
     */
    fun addActivity(myActivityModel: myActivityModel): Long {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(ACTIVITY_NAME, myActivityModel.activity)
        cv.put(ACTIVITY_TIME, myActivityModel.time)
        cv.put(ACTIVITY_ADDRESS, myActivityModel.address)

        val success = db.insert(TABLE_ACTIVITIES, null, cv)
        db.close()
        return success
    }

    /*
     * Method to read the records
     */
    fun viewActivity(): ArrayList<myActivityModel>{
        val actList: ArrayList<myActivityModel> = ArrayList<myActivityModel>()

        val selectQuery = "SELECT * FROM $TABLE_ACTIVITIES"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var time: String
        var activity: String
        var address: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                time = cursor.getString(cursor.getColumnIndex(ACTIVITY_TIME))
                activity = cursor.getString(cursor.getColumnIndex(ACTIVITY_NAME))
                address = cursor.getString(cursor.getColumnIndex(ACTIVITY_ADDRESS))

                val act = myActivityModel(id = id, activity = activity,time = time, address = address)
                actList.add(act)
            }while(cursor.moveToNext())
        }
        return actList
    }

    /**
     * Method to delete record
     */
    fun deleteActivity(act: myActivityModel): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(KEY_ID, act.id)
        val success = db.delete(TABLE_ACTIVITIES, KEY_ID + "=" + act.id, null)
        db.close()
        return success
    }

}