package com.example.mynoteapplication.di

import android.content.Context
import androidx.room.Room
import com.example.mynoteapplication.db.NoteDatabase
import com.example.mynoteapplication.utils.Constants
import com.example.mynoteapplication.utils.Constants.NOTE_DATABASE
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NoteDatabase::class.java, NOTE_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(db : NoteDatabase)= db.noteDao()
}


/**
 * You also cannot constructor-inject a type that you do not own, such as a class from an external library.
 * In these cases, you can provide Hilt with binding information by using Hilt modules.
 *  Like a Dagger module,it informs Hilt how to provide instances of certain types. Unlike Dagger modules,
 * you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module
 * will be used (in which level : in whole application ,activity , fragment or service)
 * or installed in.
 */

/**
 * Interfaces are not the only case where you cannot constructor-inject a type.
 * Constructor injection is also not possible if you don't own the class because
 * it comes from an external library (classes like Retrofit, OkHttpClient, or Room databases),
 * or if instances must be created with the builder pattern.
 * Consider the previous example. If you don't directly own the AnalyticsService class,
 * you can tell Hilt how to provide instances of this type by creating a function inside a Hilt module
 * and annotating that function with @Provides.
 */

/**
 * SingletonComponent ---> Application
 * ViewModelComponent ---> ViewModel
 * ActivityComponent ---> Activity
 * FragmentComponent ---> Fragment
 * ServiceComponent ---> Service
 */