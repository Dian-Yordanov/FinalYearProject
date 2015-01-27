package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxNativesLoader;

import java.io.File;


public class MyGdxGame extends ApplicationAdapter {
	public static SpriteBatch batch;
	Texture img;
    public static Texture square1Img;
    public static AssetManager manager;
    Sprite sprite;
	
	@Override
	public void create () {
        com.mygdx.game.MyGdxGame.batch = new SpriteBatch();
        manager = new AssetManager();
        GdxNativesLoader.load();

       // Gdx.files.external(file[i]).moveTo(Gdx.files.local("/data/"));


        String pictureAddress = "data/" + "Square4.png";
        manager.load(pictureAddress, Texture.class);
        manager.finishLoading();
        com.mygdx.game.MyGdxGame.square1Img = manager.get(pictureAddress, Texture.class);


       // com.mygdx.game.MyGdxGame.square1Img = new Texture("data/" + file[1].getName());



	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        com.mygdx.game.MyGdxGame.batch.begin();


        // sprite.draw(batch);


        for(int i=0;i<100;i++) {

//
            for (int ii = 0; ii < 100; ii++) {
                com.mygdx.game.MyGdxGame.batch.draw(com.mygdx.game.MyGdxGame.square1Img,
                        (com.mygdx.game.MyGdxGame.square1Img.getWidth() + 5 )* i,  (com.mygdx.game.MyGdxGame.square1Img.getHeight() +5)* ii);
            }

        }
        com.mygdx.game.MyGdxGame.batch.end();

    }
}
