package com.example.serverdatabase.dependeciesinjection

import com.example.serverdatabase.ServiceAPI.MahasiswaService
import com.example.serverdatabase.repository.NetworkmahasiswaRepository
import com.example.serverdatabase.repository.mahasiswaRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val mahasiswaRepository: mahasiswaRepository
}

class MahasiswaContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:8080/umyTI/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()


    private val mahasiswaService: MahasiswaService by lazy { retrofit.create(MahasiswaService::class.java) }


    override val mahasiswaRepository: mahasiswaRepository by lazy {
        NetworkmahasiswaRepository(mahasiswaService)
    } }
