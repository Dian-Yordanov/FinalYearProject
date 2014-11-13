package com.mygdx.game.android;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.MyGdxGame;

import java.io.File;
import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.option1:


                ContextWrapper c = new ContextWrapper(this);
                Log.v(c.getFilesDir().getPath(), "!" + Gdx.files.local(c.getFilesDir().getPath()).isDirectory());
                Log.v(c.getApplicationInfo().dataDir , "!" + Gdx.files.local(c.getApplicationInfo().dataDir ).list().length);
                Log.v("taggg", "!" + Gdx.files.getLocalStoragePath());
                Log.v("taggg", "!" + Gdx.files.getExternalStoragePath());

                String path = Environment.getExternalStorageDirectory().toString()+"/Pictures";
                Log.d("Files", "Path: " + path);
                File ff = new File(path);
                File file[] = ff.listFiles();
                Log.d("Files", "Size: "+ file.length);
                for (int i=0; i < file.length; i++)
                {
                    Log.d("Files", "FileName:" + file[i].getName());
                }





                return true;
            case R.id.option2:
                Toast.makeText(getApplicationContext(), "Sample Text2", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
