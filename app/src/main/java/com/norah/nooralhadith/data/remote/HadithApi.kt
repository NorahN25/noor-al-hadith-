package com.norah.nooralhadith.data.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface HadithApi {
    @GET
    suspend fun getEditionJson(@Url url: String): Map<String, Any>
}