package com.example.serverdatabase.repository

import com.example.serverdatabase.ServiceAPI.MahasiswaService
import com.example.serverdatabase.model.Mahasiswa
import okio.IOException

interface mahasiswaRepository {
    suspend fun getmahasiswa(): List<Mahasiswa>
    suspend fun insetmahasiswa(mahasiswa: Mahasiswa)
    suspend fun updatemahasiswa(nim: String, mahasiswa: Mahasiswa)
    suspend fun deletemahasiswa(nim: String)
    suspend fun getmahasiswaById(nim: String): Mahasiswa
}

class NetworkmahasiswaRepository(
    private val mahasiswaService: MahasiswaService
) : mahasiswaRepository {
    override suspend fun insetmahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaService.insertmahasiswa(mahasiswa)
    }

    override suspend fun updatemahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaService.updatemahasiswa(nim,mahasiswa)
    }

    override suspend fun deletemahasiswa(nim: String) {
        try {
            val response = mahasiswaService.deletemahasiswa(nim)
            if (!response.isSuccessful){
                throw IOException("Failed to delete mahasiswa. HTTP Status Code: " +
                "${response.code()}")
            } else {
               response.message()
               println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getmahasiswa(): List<Mahasiswa> =mahasiswaService.getmahasiswa()

    override suspend fun getmahasiswaById(nim: String): Mahasiswa {
        return mahasiswaService.getmahasiswaById(nim)
    }
}

