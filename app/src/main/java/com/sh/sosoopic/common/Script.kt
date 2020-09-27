package com.sh.sosoopic.common

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "script_table")
data class Script(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @NonNull
    @ColumnInfo(name = "subject")
    var subject: String,

    @NonNull
    @ColumnInfo(name = "type")
    var type: String,

    @NonNull
    @ColumnInfo(name = "question")
    var question: String,

    @NonNull
    @ColumnInfo(name = "answer")
    var answer: String
)