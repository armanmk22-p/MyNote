package com.example.mynoteapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mynoteapplication.model.Note
import com.example.mynoteapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: Repository) :ViewModel() {


    fun insertNote (note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote (note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun updateNote (note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    val getAllNotes = repository.getAllNote().asLiveData()
}

