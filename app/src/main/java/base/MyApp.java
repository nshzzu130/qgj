package base;

import android.app.Application;

import jpush.JpushService;

/**
 * author： hudong
 * time： 2017/12/25.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //极光推送
        JpushService.getInstance().setDebugMode(true);
        JpushService.getInstance().init(this);
    }
}
