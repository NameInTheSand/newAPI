package com.example.newsapi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface NewsDAO {
    @Query("SELECT * from news")
    List<News> getallNews();
    @Query("SELECT * from news WHERE title LIKE :seacrh ")
    List<News> getSearch(String seacrh);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(News... news);
}
