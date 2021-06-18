package org.tensorflow.lite.examples.detection;


import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {

    // /distance
    @GET("distance")
    Call<org.tensorflow.lite.examples.detection.Data> getAllData();

    @GET("location")
    Call<LocationModel>getLocation();
}
