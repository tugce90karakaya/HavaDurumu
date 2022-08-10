package com.example.havadurumu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnaInterface {
    //@GET("weather?lat=39.57&lon=32.53&appid=75cf252bb4b919b27c36b8221db9c518&units=metric")
    @GET("weather?")
    Call<AnaSinif> hava(@Query("lat") double lat,
                        @Query("lon") double lon,
                        @Query("appid") String Api,
                        @Query("units") String metric);
}
