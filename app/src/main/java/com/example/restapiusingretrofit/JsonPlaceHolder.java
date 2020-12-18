package com.example.restapiusingretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String,String> parameter);
    @GET("posts/2/comments")
    Call<List<Comment>> getComment();
    @GET("posts/{id}/comments")
    Call<List<Comment>> getParameterComment(@Path("id")int positionId);
}
