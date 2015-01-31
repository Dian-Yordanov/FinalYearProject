package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;

public class MyGdxGame extends ApplicationAdapter {

	public static SpriteBatch batch;
    public static Texture square1Img;
    public static String pictureAddress;
    private static String imageNameToBeSavedMGG = "data/initialization_image.png";
    //public static String imagePathToBeDeleted ="";

	@Override
	public void create () {
        MyGdxGame.batch = new SpriteBatch();
        checkIfFileExists();
        createContent();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for(int i=0;i<100;i++) {
            for (int ii = 0; ii < 100; ii++) {
            batch.draw(square1Img,(square1Img.getWidth() + 5 )* i,  (square1Img.getHeight() +5)* ii);
            }
        }
    batch.end();
    }
    public static void createContent(){
        AssetManager manager;
        manager = new AssetManager(new ExternalFileHandleResolver());
        manager.load(pictureAddress, Texture.class);
        manager.finishLoading();
        com.mygdx.game.MyGdxGame.square1Img = manager.get(pictureAddress, Texture.class);
    }
    public void checkIfFileExists(){
            if (!Gdx.files.external(imageNameToBeSavedMGG).exists()) {
                FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG);
                from.copyTo(Gdx.files.external(imageNameToBeSavedMGG));
            }
    }
}
