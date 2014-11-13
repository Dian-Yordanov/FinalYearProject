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
                Log.v("taggg", "ghg" );
                FileHandle[] files = Gdx.files.internal("data/graphics/").list();
                for(FileHandle file: files) {
                    Toast.makeText(getApplicationContext(), file.name() + files.toString(), Toast.LENGTH_LONG).show();
                    Log.v("taggg", file.name() + files.toString() );
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
