package ru.skillbranch.learn_rx_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import ru.skillbranch.learn_rx_java.Adapter.PostAdapter;
import ru.skillbranch.learn_rx_java.model.Post;
import ru.skillbranch.learn_rx_java.retrofit.ApiPost;
import ru.skillbranch.learn_rx_java.retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    ApiPost apiPost;
    RecyclerView recycler_posts;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Hi");
        //init API
        Retrofit retrofit = RetrofitClient.getInstance();
        apiPost = retrofit.create(ApiPost.class);

        //View
        recycler_posts = (RecyclerView)findViewById(R.id.recycler_posts);
        recycler_posts.setHasFixedSize(true);
        recycler_posts.setLayoutManager( new LinearLayoutManager(this));

        fetchData();

    }

    private void fetchData() {
        compositeDisposable.add(apiPost.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                displayData(posts);
            }
        }));
    }

    private void displayData(List<Post> posts){
        PostAdapter adapter = new PostAdapter(this, posts);
        recycler_posts.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
