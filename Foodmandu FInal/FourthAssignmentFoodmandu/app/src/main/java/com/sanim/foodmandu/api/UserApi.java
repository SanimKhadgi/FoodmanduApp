package com.sanim.foodmandu.api;

import com.sanim.foodmandu.model.User;
import com.sanim.foodmandu.model.username;
import com.sanim.foodmandu.serverresponse.ImageResponse;
import com.sanim.foodmandu.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserApi {
    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body User user);

//    @FormUrlEncoded
//    @POST("user/login")
//    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @POST("user/login")
    Call<SignUpResponse> checklogin(@Body username Username);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("user/me")
    Call<User> getUserDetails(@Header("Authorization") String token);

}
