package com.sanim.foodmandu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sanim.foodmandu.MainActivity;
import com.sanim.foodmandu.R;
import com.sanim.foodmandu.api.UserApi;
import com.sanim.foodmandu.bll.LoginBll;
import com.sanim.foodmandu.model.username;
import com.sanim.foodmandu.serverresponse.SignUpResponse;
import com.sanim.foodmandu.StrictModeClass;
import com.sanim.foodmandu.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
TextView txtView;
EditText etUsername,etPassword;
Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnlogin=findViewById(R.id.btnlogin);
        txtView=findViewById(R.id.txtView);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login(){
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();

        LoginBll loginBll=new LoginBll();

        com.sanim.foodmandu.model.username Username=new username(username,password);

        StrictModeClass.StrictMode();

        UserApi userapi= Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> userCall=userapi.checklogin(Username);
        userCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Url.token += response.body().getToken();
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("Image","ninjeng.jpg");
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "error is = " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
