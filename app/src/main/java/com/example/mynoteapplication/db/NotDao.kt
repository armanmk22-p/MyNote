package com.example.mynoteapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mynoteapplication.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Dao = Data access object. A mapping of SQL queries to functions.
 * By default, all queries must be executed on a separate thread.
 * This allows your queries to be annotated with the suspend modifier and
 * then called from a coroutine or from another suspension function.
 *
 * The selected onConflict strategy ignores
 * a new word if it's exactly the same as one already in the list
 *
 *
 * A Flow is an async sequence of values
 * Flow produces values one at a time (instead of all at once) that can generate values from
 * async operations like network requests, database calls, or other async code.
 */

@Dao
interface NotDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY Title ASC")
    fun getAllNote():Flow<List<Note>>
}