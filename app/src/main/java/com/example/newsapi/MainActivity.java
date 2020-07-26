package com.example.newsapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //constants
    final static String token = "0b0071f5adaf460ea6fa0c5e2bdb28a8";
    final static String type = "bitcoin";
    final static String sort = "publishedAt";
    final static String date = "2020-06-26";
    //Ui elemets
    EditText title;
    Button find;
    RecyclerView news;
    RecyclerAdapter recyclerAdapter;
    RecyclerAdapter recyclerAdapter2;
    List<News> newsList;
    List<News> searchList;
    AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title= findViewById(R.id.et_find_by_title);
        find=findViewById(R.id.BtnGet);
        if (savedInstanceState != null) {
            title.setText(savedInstanceState.getString("ID"));
            initViews();
        }
        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,
                "News")
                .allowMainThreadQueries()
                .build();
        initViews();
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    searchList = database.newsDAO().getSearch('%'+title.getText().toString()+'%');
                recyclerAdapter2 = new RecyclerAdapter(searchList.size(), searchList);
                    news.setAdapter(recyclerAdapter2);
            }
        });
    }
    private void initViews() {
        news = findViewById(R.id.rv_news);
        news.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        news.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON() {
        NetworkAPI.getInstance()
                .getJSONApi()
                .getPostofUser(type,sort,date,token)
                .enqueue(new Callback<PlaceHolderAPI.Post>() {
                    @Override
                    public void onResponse(@NonNull Call<PlaceHolderAPI.Post> call,
                                           @NonNull Response<PlaceHolderAPI.Post> response) {
                        PlaceHolderAPI.Post post = response.body();
                        try {

                            if (post != null) {

                                for (int i = 0; i < 20; i++) {
                                        database.newsDAO().insertAll(new News(post.sources.get(i).title,
                                                post.sources.get(i).description,
                                                post.sources.get(i).imgUrl));
                                }
                                newsList = database.newsDAO().getallNews();
                                recyclerAdapter = new RecyclerAdapter(20, newsList);
                                news.setAdapter(recyclerAdapter);

                            }
                        }
                        catch (Exception e)
                        {
                            Log.d("Error",e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceHolderAPI.Post> call, Throwable t) {
                        Log.d("Fail", t.getMessage());
                    }
                });
    }
}