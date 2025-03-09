package com.example.ccp_movil.data

import com.example.ccp_movil.data.model.Producto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("Productos/Listar?key=13719760")
    suspend fun listCatalogoProductos(
    ): List<Producto>

    /*@GET("collectors")
    suspend fun listCollectors(
    ): List<Collector>

    @GET("albums/{id}")
    suspend fun getAlbumDetails(@Path("id") id: String): AlbumesItem

    @GET("bands/{id}")
    suspend fun getBandDetails(@Path("id") id: String): Band*/
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://my.api.mockaroo.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}