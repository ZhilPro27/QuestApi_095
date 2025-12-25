package com.example.questapi.apiservices

import com.example.questapi.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApiSiswa {
    @GET("bacateman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postSiswa(@Body dataSiswa: DataSiswa):retrofit2.Response<Void>

    @GET("baca1Teman.php/{id}")
    suspend fun getStatusSiswa(@Query("id") id: Int): DataSiswa

}