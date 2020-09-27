package com.sh.sosoopic.network


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {
    @FormUrlEncoded
    @POST("/mphoto/app/getAPIPosterList")
    fun posterList(
        @Field("movieCode") movieCode: String,
        @Field("pageIdx") pageIdx: String,
        @Field("pageSize") pageSize: String
    ): Call<RetrofitResponse>
}