package ch.stair.platypus.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServiceGenerator {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static final Retrofit retrofit = builder.build();

    static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }}
