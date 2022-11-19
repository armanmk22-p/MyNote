package com.example.mynoteapplication.repository

import androidx.room.*
import com.example.mynoteapplication.db.NotDao
import com.example.mynoteapplication.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val dao: NotDao) {


    suspend fun insertNote(note: Note) = dao.insertNote(note)

    suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    suspend fun updateNote(note: Note) = dao.updateNote(note)

    fun getAllNote() : Flow<List<Note>> = dao.getAllNote()
}