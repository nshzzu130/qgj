package com.app.gaolonglong.fragmenttabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jpush.JpushService;

public class Dingdan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        JpushService.getInstance().setAlias("test");



    }
}
