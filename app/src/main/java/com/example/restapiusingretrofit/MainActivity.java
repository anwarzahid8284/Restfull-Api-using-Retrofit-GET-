package com.example.restapiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    BaseUrl baseUrl;
    TextView textView;
    JsonPlaceHolder jsonPlaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.data_id);
        baseUrl=new BaseUrl();
        Gson gson=new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor httpLoggingIntercepton=new HttpLoggingInterceptor();
        httpLoggingIntercepton.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newBuilder().addInterceptor(httpLoggingIntercepton).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        jsonPlaceHolder=retrofit.create(JsonPlaceHolder.class);
      // jsonPlaceHolder=baseUrl.baseUrl().create(JsonPlaceHolder.class);
       // createPost();
        //postCreate();
        //getPost();
       // getComment();
       // getParamenterComments(); // get value using path
       updatePost();// update the post for specific object
       // pachUpdatePost();
        //deletePost();
    }

    private void deletePost() {
        Call<Void> postCall=jsonPlaceHolder.deletePost(2);
        postCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    textView.setText("Code:"+response.code());
                    return;
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textView.setText("Failed:"+t.getMessage());

            }
        });
    }

    private void pachUpdatePost() {
        Post post=new Post(12,null,"Update text");
        Call<Post> postCall=jsonPlaceHolder.putPost(5,post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                Post posts=response.body();
                String content = "";
                content += "Code :" +response.code() + "\n";
                content += "UserId :" +posts.getUserId() + "\n";
                content += "AutoId:" + posts.getId() + "\n";
                content += "Title :" + posts.getTitle() + "\n";
                content += "Text :" + posts.getBody() + "\n\n";
                textView.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText("Failed:"+t.getMessage());

            }
        });
    }

    private void updatePost() {
        Post post=new Post(12,null,"Update text");
        Call<Post> postCall=jsonPlaceHolder.putPost(5,post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                Post posts=response.body();
                String content = "";
                content += "Code :" +response.code() + "\n";
                content += "UserId :" +posts.getUserId() + "\n";
                content += "AutoId:" + posts.getId() + "\n";
                content += "Title :" + posts.getTitle() + "\n";
                content += "Text :" + posts.getBody() + "\n\n";
                textView.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText("Failed:"+t.getMessage());

            }
        });
    }

    private void createPost() {
        final Post post=new Post(333,"Android development", "java");
        Call<Post> postCall=jsonPlaceHolder.createPost(post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                Post posts=response.body();
                    String content = "";
                    content += "UserId :" +posts.getUserId() + "\n";
                    content += "AutoId:" + posts.getId() + "\n";
                    content += "Title :" + posts.getTitle() + "\n";
                    content += "Text :" + posts.getBody() + "\n\n";
                    textView.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText("Failed:"+t.getMessage());
            }
        });
    }
    private void postCreate() {
        Call<Post> postCall=jsonPlaceHolder.postCreate(440,"akbar zahid","android developer");
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                Post posts=response.body();
                String content = "";
                content += "UserId :" +posts.getUserId() + "\n";
                content += "AutoId:" + posts.getId() + "\n";
                content += "Title :" + posts.getTitle() + "\n";
                content += "Text :" + posts.getBody() + "\n\n";
                textView.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText("Failed:"+t.getMessage());
            }
        });
    }


    private void getParamenterComments() {
        Call<List<Comment>> commentList=jsonPlaceHolder.getParameterComment(4);
        commentList.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                List<Comment> comments=response.body();
                for(Comment comment: comments){
                    String content = "";
                    content += "ID :" + comment.getId() + "\n";
                    content += "Post ID:" + comment.getPostId() + "\n";
                    content += "Name :" + comment.getName() + "\n";
                    content += "Email :" + comment.getEmail() + "\n";
                    content += "Text :" + comment.getText() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }
    private void getComment() {

        Call<List<Comment>> commentList=jsonPlaceHolder.getComment();
        commentList.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                List<Comment> comments=response.body();
                for(Comment comment: comments){
                    String content = "";
                    content += "ID :" + comment.getId() + "\n";
                    content += "Post ID:" + comment.getPostId() + "\n";
                    content += "Name :" + comment.getName() + "\n";
                    content += "Email :" + comment.getEmail() + "\n";
                    content += "Text :" + comment.getText() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }

    private void getPost() {
        Map<String, String> parameter=new HashMap<>();
        parameter.put("userId","1");
        parameter.put("_sort","Id");
        parameter.put("_order","desc");
        Call<List<Post>> listCall=jsonPlaceHolder.getPost(parameter);
        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Failed:"+response.code());
                    return;
                }
                List<Post> posts=response.body();
                for(Post post:posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getBody() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }


}