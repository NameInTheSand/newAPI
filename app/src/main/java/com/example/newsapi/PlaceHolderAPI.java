package com.example.newsapi;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
interface PlaceHolderAPI {
    @GET("/v2/everything")
    public Call<Post> getPostofUser( @Query("q") String q,
                                     @Query("sortBy") String sort,
                                     @Query("from") String from,
                                     @Query("apiKey") String key);

    public class Post { //POJO
        @SerializedName("articles")
        public ArrayList<Source> sources = new ArrayList<Source>();
        @SerializedName("totalResults")
        public int resultsCount;
    }
    public class Source
    {
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public  String description;
        @SerializedName("urlToImage")
        public String imgUrl;
    }
}
