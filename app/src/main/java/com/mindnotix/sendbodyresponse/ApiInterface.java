package com.mindnotix.sendbodyresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {



    @POST("checkout.php")
    Call<BasicResponse> updateSkills(@Body BasicSend task);




}