package com.example.loveapp

import android.app.Application
import androidx.room.Room
import com.example.loveapp.room.AppDataBase
import dagger.hilt.android.HiltAndroidApp
import kotlin.text.Typography.dagger

@HiltAndroidApp
class App:Application() {

    companion object{
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this,AppDataBase::class.java,"love-base").allowMainThreadQueries().build()
    }
}