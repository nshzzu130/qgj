package com.app.gaolonglong.fragmenttabhost;

import android.content.Intent;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText account;
    EditText pwd;
    CheckBox cb;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account=(EditText) findViewById(R.id.account);
        pwd=(EditText) findViewById(R.id.account);
        cb=(CheckBox)findViewById(R.id.ischef);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()){
                    startActivity(new Intent(LoginActivity.this,ShangjiaMainActivity.class));
                }else{
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        });
    }
}
