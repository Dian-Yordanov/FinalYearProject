package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.File;

public class MyGdxGame extends ApplicationAdapter {
	public static SpriteBatch batch;
	//Texture img;
    public static Texture square1Img;
	
	@Override
	public void create () {
        com.mygdx.game.MyGdxGame.batch = new SpriteBatch();

        AssetManager manager = new AssetManager();
        //Texture tex = manager.get("data/mytexture.png", Texture.class);
       // BitmapFont font = manager.get("data/myfont.fnt", BitmapFont.class);

        String path = Gdx.files.getExternalStoragePath()+"assets";
        File ff = new File(path);
        File file[] = ff.listFiles();

       // Gdx.app.log("AssetPath", "!"+file[0].exists());
        Gdx.app.log("AssetPath", "!"+file[0].getName());


        assert file != null;
      //  manager.load(path+"/"+Gdx.files.internal(file[0].getName()), Texture.class);
        manager.load("data/Square1.png",Texture.class);
        manager.finishLoading();
        com.mygdx.game.MyGdxGame.square1Img = manager.get("data/Square1.png",Texture.class);
                //new Texture("data/Square1.png");
                //manager.get("data/Square1.png",Texture.class);
                //manager.get(Gdx.files.internal("data/Square1.png").path(), Texture.class);
                //new Texture(path+"/"+Gdx.files.internal(file[0].getName()));
        //path+"/"+Gdx.files.internal(file[0].getName()).path()

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        com.mygdx.game.MyGdxGame.batch.begin();
        for(int i=0;i<100;i++) {
            for (int ii = 0; ii < 100; ii++) {
                com.mygdx.game.MyGdxGame.batch.draw(com.mygdx.game.MyGdxGame.square1Img,
                        (com.mygdx.game.MyGdxGame.square1Img.getWidth() + 5 )* i,  (com.mygdx.game.MyGdxGame.square1Img.getHeight() +5)* ii);
            }

        }
        com.mygdx.game.MyGdxGame.batch.end();

    }
}
