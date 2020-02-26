package ru.skillbranch.learn_rx_java.utils;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import ru.skillbranch.learn_rx_java.BuildConfig;
import ru.skillbranch.learn_rx_java.retrofit.ApiKeyInterceptor;
import ru.skillbranch.learn_rx_java.retrofit.ApiPost;

public class PostApiService {
    public static final List<Class<?>> NETWORK_EXCEPTIONS = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    private static OkHttpClient sClient;
    private static Retrofit sRetrofit;
    private static Gson sGson;
    private static ApiPost sApi;

    private static OkHttpClient getClient(){
        if (sClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ApiKeyInterceptor());// Это нужно чтобы добавить
            //какое-нибудь apiKey в header. Оставил себе на будущее для примера.
            if (!BuildConfig.BUILD_TYPE.contains("release")){
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            sClient = builder.build();
        }
        return sClient;
    }

    private static Retrofit getRetrofit(){
        if (sGson == null){sGson = new Gson();}
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create(sGson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static ApiPost getApiService(){
        if (sApi == null){
            sApi = getRetrofit().create(ApiPost.class);
        }
        return sApi;
    }
}
