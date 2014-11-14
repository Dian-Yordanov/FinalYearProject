package com.mygdx.game.android;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.io.File;
import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        setContentView(R.layout.activity_main);

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
                Log.v(c.getFilesDir().getPath(), "!" + Gdx.files.internal("/data/").isDirectory());
                Log.v(c.getApplicationInfo().dataDir , "!" + Gdx.files.internal("/data/").list().length);
                Log.v("taggg", "!" + Gdx.files.getLocalStoragePath());
                Log.v("taggg", "!" + Gdx.files.getExternalStoragePath());

                //String path = Environment.getExternalStorageDirectory().toString()+"/Pictures";
                String path = "data/";
                Log.d("Files", "Path: " + path);
                File ff = new File(path);
                File file[] = ff.listFiles();
                Log.d("Files", "Size: "+ file.length);
                for (int i=0; i < file.length; i++)
                {
                    Log.d("Files", "FileName:" + file[i].getName());
                }

                Gdx.app.log("AssetPath", Gdx.files.internal(path + file[0].getName()).file().getAbsolutePath());

                com.mygdx.game.MyGdxGame.square1Img = new Texture(Gdx.files.internal(path + file[0].getName()).file().getAbsolutePath());
                //Gdx.files.internal(path +"/"+ file[0].getName()));
                //square1Img = new Texture("Square1.png");


                com.mygdx.game.MyGdxGame.batch.begin();
                for(int i=0;i<100;i++) {
                    for (int ii = 0; ii < 100; ii++) {
                        com.mygdx.game.MyGdxGame.batch.draw(com.mygdx.game.MyGdxGame.square1Img,
                                (com.mygdx.game.MyGdxGame.square1Img.getWidth() + 5 )* i,  (com.mygdx.game.MyGdxGame.square1Img.getHeight() +5)* ii);
                    }

                }
                com.mygdx.game.MyGdxGame.batch.end();
                return true;
            case R.id.option2:
                Toast.makeText(getApplicationContext(), "Sample Text2", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static void haha(){}
}
