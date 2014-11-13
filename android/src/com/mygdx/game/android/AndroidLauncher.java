package com.mygdx.game.android;

import android.os.Bundle;
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
                //.makeText(getApplicationContext(), "Sample Text1", Toast.LENGTH_LONG).show();
               // Log.v("taggg", "ghg" );
                //Gdx.app.log("AssetPath", Gdx.files.internal("Square1.png").file().getAbsolutePath());

                //FileHandle dirHandle = Gdx.files.absolute("/storage/emulated/0/");
                //for (FileHandle entry: dirHandle.list()) {
                //    Log.v("taggg", "1 " +  entry.name() + files.toString() );
                //}

                Gdx.app.log("AssetPath", Gdx.files.internal("/android/assets/Square1.png").file().getParent());

                Gdx.app.log("AssetPath", Gdx.files.internal("/android/assets/Square1.png").file().getPath());
                Gdx.app.log("taggg", "!" + Gdx.files.internal("/android/assets/").file().listFiles());
                Gdx.app.log("taggg", "!" + Gdx.files.internal("/android/assets").file().listFiles());


                Log.v("taggg", "!" + Gdx.files.internal("/").isDirectory());
                Log.v("taggg", "!" + Gdx.files.internal("/").list().length);


                Log.v("AssetPath", "!" + Gdx.files.internal("data/graphics/").file().isDirectory());
                Log.v("taggg", "!" + Gdx.files.internal("/storage/emulated/0").file().list());

                Log.v("taggg", "!" + Gdx.files.external("/Android/").list().length);
                Log.v("taggg", "!" + Gdx.files.internal("/storage/emulated/0").isDirectory());
                Log.v("taggg", "!" + Gdx.files.internal("/storage/emulated/0/Android").list().length);
                Log.v("taggg", "!" + Gdx.files.absolute("/assets").list().length);


                FileHandle[] files = Gdx.files.internal("/").list();
                for(FileHandle file: files) {
                    Log.v("taggg", "2 " +  file.name() );
                }
                FileHandle[] files1 = Gdx.files.internal("/").list();
                for(FileHandle file2: files1) {
                    if(file2.name().equals("assets")){
                        
                        Log.v("taggg", "4 " +  file2.name() );
                    }

                }

                Log.v("taggg", "3 " + files);
                return true;
            case R.id.option2:
                Toast.makeText(getApplicationContext(), "Sample Text2", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
