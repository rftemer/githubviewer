package br.com.rftemer.githubviewer.base;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseNetwork {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.github.com/users/");

    public static <S> S createService(Class<S> serviceClass) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        final Retrofit retrofit = builder
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()))
                .client(okHttpClient)
                .build();
        return retrofit.create(serviceClass);
    }

}
