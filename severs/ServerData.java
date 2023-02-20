package com.example.tiki.app_canhbao.severs;

import com.example.tiki.app_canhbao.entity.NoticationClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerData {
    //https://9b02-14-169-67-74.ngrok.io
//    static String BASE_URL="https://appcanhbao.herokuapp.com/";
    static String BASE_URL="https://appcanhbao.herokuapp.com/";
    @POST("send")
    Call<NoticationClass> PostCategory(
            @Query("token") String token,
            @Query("nameClass") String nameClass,
            @Query("noidung") String noidung
        );

//    @GET("hub/category_id/{id}?")
//    Call<Response> getProduct(
//            @Path("id") int id,
//            @Query("cursor") int cursor,
//            @Query("limit") int limit
//    );
}
