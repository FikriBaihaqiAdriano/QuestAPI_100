package com.example.serverdatabase

import android.app.Application
import com.example.serverdatabase.dependeciesinjection.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var MahasiswaContainer: MahasiswaContainer

    override fun onCreate() {
    super.onCreate()
    MahasiswaContainer= MahasiswaContainer()
    }
}
