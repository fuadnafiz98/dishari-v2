package org.tensorflow.lite.examples.detection;


import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {

    // /distance
    @GET("distance")
    Call<Model> getAllData();

    @GET("location")
    Call<LocationModel>getLocation();
}
