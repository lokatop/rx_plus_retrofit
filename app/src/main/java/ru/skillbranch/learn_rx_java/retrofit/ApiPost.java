package ru.skillbranch.learn_rx_java.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.skillbranch.learn_rx_java.model.Post;

public interface ApiPost {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
