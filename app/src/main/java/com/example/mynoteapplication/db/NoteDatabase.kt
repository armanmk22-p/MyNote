package com.example.mynoteapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynoteapplication.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao() : NotDao
}