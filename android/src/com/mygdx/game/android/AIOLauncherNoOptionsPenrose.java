package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 3/16/2015.
 */
public class AIOLauncherNoOptionsPenrose extends AIOLauncherNoOptions {
    public static String imageNameToBeSaved;
    public static String imageNameToBeSaved2;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGdxGame.pictureAddress = imageNameToBeSaved;
        MyGdxGame.pictureAddress2 = imageNameToBeSaved2;
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MyGdxGame(), config);
    }
}
