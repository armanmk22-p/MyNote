package com.example.mynoteapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication :Application() {
}

/**
 * we must add  : android:name=".MyApplication" inside <application> manifests file
 * name should be the same as class we created for dagger - hilt application
 */