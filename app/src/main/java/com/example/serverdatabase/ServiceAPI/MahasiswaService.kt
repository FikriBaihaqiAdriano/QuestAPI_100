package com.example.serverdatabase.ServiceAPI

import com.example.serverdatabase.model.Mahasiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacamahasiswa.php")
    suspend fun getmahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getmahasiswaById(@Query("nim") nim:String):Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun insertmahasiswa(@Body mahasiswa: Mahasiswa)

    @PUT("editmahasiswa.php/{nim}")
    suspend fun updatemahasiswa (@Query("nim") nim:String,@Body mahasiswa : Mahasiswa)

    @DELETE("deletemahasiswa.php/{nim}")
    suspend fun deletemahasiswa(@Query("nim") nim:String): Response<Void>
}