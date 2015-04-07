package com.mygdx.game.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by zyan on 07/04/15.
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}