package com.mithilakshar.mithilapanchang.Api



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BhagwatGitaRetrofitInstance {

    val api:BhagwatGitaInterface by lazy {

        Retrofit.Builder().baseUrl(baseUrl.base)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BhagwatGitaInterface::class.java)
    }
}