package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;

public class MyGdxGame extends ApplicationAdapter {

    public static Sprite sprite;
    public static SpriteBatch batch;
    public static Texture square1Img;
    public static String pictureAddress;
    public static String patternStyle = "";
    public static String imageNameToBeSavedMGG = "";
    Texture foreground;
    Texture background;
    Pixmap fg;
    Pixmap mask;
    //public static String imagePathToBeDeleted ="";

    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();

        checkIfFileExists();

        createContent();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);

        if (patternStyle.equals("SquareTillingLauncher")) {SquareRendering();}
        if (patternStyle.equals("HexagonalTillingLauncher")) {HexagonalRendering();}
    }

    public void createContent() {
        AssetManager manager;
        manager = new AssetManager(new ExternalFileHandleResolver());
        if (patternStyle.equals("SquareTillingLauncher")) {manager.load(pictureAddress, Texture.class); }
        if (patternStyle.equals("HexagonalTillingLauncher")) {manager.load(pictureAddress, Pixmap.class); }
        //manager.load(pictureAddress, Pixmap.class);
        manager.finishLoading();
        if (patternStyle.equals("SquareTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher")) {


            // Initially, the mask should have an alpha of 1

            //Pixmap fg = new Pixmap(Gdx.files.internal(pictureAddress));


            mask = new Pixmap(128, 128, Pixmap.Format.Alpha);

            // Cut a rectangle of alpha value 0
            //Pixmap.setBlending(Pixmap.Blending.None);
            mask.setBlending(Pixmap.Blending.None);
            mask.setColor(new Color(0f, 0f, 0f, 0f));
            mask.fillRectangle(0, 0, 32, 32);
            //square1Img =

            fg = new Pixmap(Gdx.files.external(pictureAddress));//manager.get(pictureAddress, Pixmap.class);
            fg.drawPixmap(mask, fg.getWidth(), fg.getHeight());

            mask.setBlending(Pixmap.Blending.SourceOver);
            //Pixmap.setBlending(Pixmap.Blending.SourceOver);

            foreground = new Texture(fg);

            background = new Texture(Gdx.files.internal(pictureAddress));//new Texture(pictureAddress);

            /*

            Pixmap mask = new Pixmap(128, 128, Pixmap.Format.Alpha);
            mask.setBlending(Pixmap.Blending.None);
            mask.setColor(new Color(0f, 0f, 0f, 0f));
            mask.fillRectangle(0, 0, 32, 32);

            //Pixmap fg = new Pixmap(Gdx.files.internal(pictureAddress));
            Pixmap fg = manager.get(pictureAddress, Pixmap.class);
                    //new Pixmap(Gdx.files.external(pictureAddress));

            //Pixmap fg = manager.get(pictureAddress, Pixmap.class);
            fg.drawPixmap(mask, fg.getWidth(), fg.getHeight());
            mask.setBlending(Pixmap.Blending.SourceOver);
            foreground = new Texture(fg);
            //background = new Texture("data/ii_square_tilling.png");

            //createSpriteForTransperancyRendering();

            */
        }
    }

    public void checkIfFileExists() {
        if (!Gdx.files.external(imageNameToBeSavedMGG).exists()) {
            FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG);
            from.copyTo(Gdx.files.external(imageNameToBeSavedMGG));
        }
    }
    public void SquareRendering(){
            batch.begin();
            for (int i = 0; i < 100; i++) {
                for (int ii = 0; ii < 100; ii++) {
                    batch.draw(square1Img, (square1Img.getWidth() + 5) * i, (square1Img.getHeight() + 5) * ii);
                }
            }
            batch.end();
    }
    public void HexagonalRendering(){

        batch.begin();
        batch.enableBlending();

        Sprite sprite11 = new Sprite(foreground);
        sprite = new Sprite(background);
        sprite.setColor(1, 0, 0, 1);

        //sprite.draw(batch);
        //batch.enableBlending();

        batch.draw(foreground,0,0);
        batch.draw(sprite, 50, 50);
        batch.draw(background,100,100);
        batch.draw(sprite11,150,150);

        //batch.draw(fg,100,100);
        //batch.draw(background,0,0);
        //batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);

        /*
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                batch.draw(foreground,(foreground.getWidth() + 5) * i,(foreground.getHeight() + 5) * ii);
                // batch.draw(square1Img, (square1Img.getWidth() + 5) * i, (square1Img.getHeight() + 5) * ii);
            }
        }
        */
        batch.disableBlending();
        batch.end();


        //batch.disableBlending();
    }
    public void createSpriteForTransperancyRendering(){
        Pixmap mask = new Pixmap(128, 128, Pixmap.Format.Alpha);
        mask.setBlending(Pixmap.Blending.None);
        mask.setColor(new Color(0f, 0f, 0f, 0f));
        mask.fillRectangle(0, 0, 32, 32);

        //Pixmap fg = new Pixmap(Gdx.files.internal(pictureAddress));
        Pixmap fg = new Pixmap(Gdx.files.external(pictureAddress));

        //Pixmap fg = manager.get(pictureAddress, Pixmap.class);
        fg.drawPixmap(mask, fg.getWidth(), fg.getHeight());
        mask.setBlending(Pixmap.Blending.SourceOver);
        foreground = new Texture(fg);
        //background = new Texture("data/ii_square_tilling.png");
    }

}
