package com.sh.sosoopic.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Script::class], version = 1)
abstract class ScriptDB : RoomDatabase() {
    abstract fun getScriptDao(): ScriptDao

    companion object {
        private var instance: ScriptDB? = null

        @Synchronized
        fun getInstance(context: Context): ScriptDB? {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScriptDB::class.java,
                        "script.db"
                    ).build()
                }
                return instance
            }
        }

        fun destroyInstance() {
            instance = null
        }
    }
}