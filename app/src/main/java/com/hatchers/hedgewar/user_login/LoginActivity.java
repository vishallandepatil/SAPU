package com.hatchers.hedgewar.user_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hatchers.hedgewar.Pref_Manager.PrefManager;
import com.hatchers.hedgewar.R;
import com.hatchers.hedgewar.activity.MenuActivity;
import com.hatchers.hedgewar.user_login.apihelper.Login_ApiHelper;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText edtName,edtPassword;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
        clickListners();
    }

    private void initialization()
    {
        prefManager = new PrefManager(this);
        edtName = (EditText)findViewById(R.id.username);
        edtPassword = (EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.btn_login);
    }

    private void clickListners()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInfo();
               // Login_ApiHelper.userLoginApi(LoginActivity.this);
                Intent intent=new Intent(LoginActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUserInfo()
    {
        prefManager.setUserName(edtName.getText().toString());
        prefManager.setPassword(edtPassword.getText().toString());

    }


}
