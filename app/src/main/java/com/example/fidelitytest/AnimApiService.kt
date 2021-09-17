package com.example.fidelitytest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://api.jikan.moe"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface AnimApiService {
    /**
     * Returns a Retrofit callback that delivers a Anim and list
     */
    @Headers("Content-Type: application/json")
    @GET("$BASE_URL/v3/search/anime")
    fun getAnim(@Query("q") animName: String):
            Deferred<Anim>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object AnimApi {
    val retrofitService: AnimApiService by lazy { retrofit.create(AnimApiService::class.java) }
}