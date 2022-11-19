package com.example.mynoteapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * a database table
 */

@Entity(tableName = "note_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id :Int,
    @ColumnInfo
    val Title :String
)
