package com.mygdx.game.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.PenroseRenderingActivity;

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

        //MyGdxGame.pictureAddress = imageNameToBeSaved;
        //MyGdxGame.pictureAddress2 = imageNameToBeSaved2;
        goToRenderingActivity();
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
        initialize(new PenroseRenderingActivity(), config);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.findItem(R.id.option1);
        menuItem1.setTitle("");
        menuItem1.setVisible(false);
        MenuItem menuItem2 = menu.findItem(R.id.option2);
        menuItem2.setTitle("");
        menuItem2.setVisible(false);
        MenuItem menuItem3 = menu.findItem(R.id.option3);
        menuItem3.setTitle("");
        menuItem3.setVisible(false);
        return true;
    }
}
