package com.example.newsapi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
interface NewsDAO {
    @Query("SELECT * from news")
    List<News> getallNews();

    @Insert
    void insertAll(News... news);
}
