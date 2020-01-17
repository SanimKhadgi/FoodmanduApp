package com.sanim.foodmandu.bll;

import com.sanim.foodmandu.api.UserApi;
import com.sanim.foodmandu.model.username;
import com.sanim.foodmandu.serverresponse.SignUpResponse;
import com.sanim.foodmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

       com.sanim.foodmandu.model.username Username= new username(username,password);
       UserApi userapi= Url.getInstance().create(UserApi.class);
       Call<SignUpResponse> usersCall = userapi.checklogin(Username);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
