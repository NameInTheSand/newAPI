package com.example.newsapi;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = News.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDAO newsDAO ();
}
