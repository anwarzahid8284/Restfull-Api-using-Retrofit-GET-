package com.example.restapiusingretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String,String> parameter);
    @GET("posts/2/comments")
    Call<List<Comment>> getComment();
    @GET("posts/{id}/comments")
    Call<List<Comment>> getParameterComment(@Path("id")int positionId);
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> postCreate(@Field("userId") int userId,@Field("title")String title,
                          @Field("text") String text);
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post );
    @PATCH("posts/{id}")
    Call<Post>patchPost(@Path("id")int id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
