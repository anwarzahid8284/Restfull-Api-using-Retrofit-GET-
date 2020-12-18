package com.example.restapiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
       jsonPlaceHolder=baseUrl.baseUrl().create(JsonPlaceHolder.class);

        getPost();
       // getComment();
       // getParamenterComments(); // get value using path
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