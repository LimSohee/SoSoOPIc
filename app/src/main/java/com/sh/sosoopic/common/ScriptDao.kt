package com.sh.sosoopic.common

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ScriptDao {
    @Query("SELECT * FROM script_table")
    fun getAllScript(): LiveData<List<Script>>

    @Query("DELETE from script_table")
    fun deleteAllScript()

    @Insert(onConflict = REPLACE)
    fun insertScript(script: Script)

    @Update(onConflict = REPLACE)
    fun updateScript(script: Script)

    @Delete
    fun deleteScript(script: Script)
}