package com.mygdx.game.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/17/2015.
 */
public class AIOLauncherNoOptions extends AndroidApplication {
    public static String imageNameToBeSaved;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGdxGame.pictureAddress = imageNameToBeSaved;
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MyGdxGame(), config);
    }

    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem menuItem1 = menu.findItem(R.id.option1);
        menuItem1.setTitle("restart activity");
        //menuItem1.setVisible(false);
        MenuItem menuItem2 = menu.findItem(R.id.option2);
        menuItem2.setTitle("");
        menuItem2.setVisible(false);
        MenuItem menuItem3 = menu.findItem(R.id.option3);
        menuItem3.setTitle("");
        menuItem3.setVisible(false);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        invalidateOptionsMenu ();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                goToRenderingActivity();
                return true;
            case R.id.option2:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void goToRenderingActivity(){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MyGdxGame(), config);
    }
 /*   @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
       *//* if (mCamera != null) {
            mCamera.release()
            mCamera = null;
        }*//*
    }*/
}