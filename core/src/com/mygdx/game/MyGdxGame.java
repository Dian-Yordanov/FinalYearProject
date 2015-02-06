package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.File;

public class MyGdxGame extends ApplicationAdapter {

    public static SpriteBatch batch;
    public static String patternStyle = "";

    public static Texture square1Img;
    public static Texture square2Img;

    public static String pictureAddress;
    public static String pictureAddress2;

    public static String imageNameToBeSavedMGG = "";
    public static String imageNameToBeSavedMGG2 = "";

    Sprite sprite;
    Pixmap fg;
    Pixmap mask;

    private OrthographicCamera camera;

    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher")) {checkIfFileExists(imageNameToBeSavedMGG2);}
        createContent();

        camera = new OrthographicCamera(0, 0);
        camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        camera.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (patternStyle.equals("SquareTillingLauncher")) {SquareRendering();}
        if (patternStyle.equals("HexagonalTillingLauncher")) {HexagonalRendering();}
        if (patternStyle.equals("TriangullarTillingLauncher")) {TriangullargleRendering();}
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight() * height/width;
        camera.update();
    }

    public void createContent() {
        AssetManager manager;
        manager = new AssetManager(new ExternalFileHandleResolver());
        if (patternStyle.equals("SquareTillingLauncher")) {manager.load(pictureAddress, Texture.class); }
        if (patternStyle.equals("HexagonalTillingLauncher")) {manager.load(pictureAddress, Texture.class); }
        if (patternStyle.equals("TriangullarTillingLauncher")) {manager.load(pictureAddress, Texture.class);
                                                                manager.load(pictureAddress2, Texture.class);}
        manager.finishLoading();
        if (patternStyle.equals("SquareTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangullarTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
            square2Img = manager.get(pictureAddress2, Texture.class);

            sprite = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());

        }
    }

    public void checkIfFileExists(String imageNameToBeSavedMGG1) {
        if (!Gdx.files.external(imageNameToBeSavedMGG1).exists()) {
            FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG1);
            from.copyTo(Gdx.files.external(imageNameToBeSavedMGG1));
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
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                if(i%2==0){ batch.draw(square1Img,
                        (((square1Img.getWidth()+5)*3/4)*i)-((square1Img.getWidth()+5)*3/4)/2
                        ,(square1Img.getHeight()+5)*ii);}
                if(i%2!=0){ batch.draw(square1Img,
                        (((square1Img.getWidth()+5)*3/4)*i)-((square1Img.getWidth()+5)*3/4)/2
                        ,(((square1Img.getHeight()+5)*ii)-square1Img.getHeight()/2)-2);}
            }
        }
        batch.disableBlending();
        batch.end();
    }
    public void TriangullargleRendering(){
        batch.begin();
        batch.enableBlending();

        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                if(ii%2==0) batch.draw(square1Img,
                        (square1Img.getWidth()) * i,
                        (square1Img.getHeight()) * ii);
                if(ii%2!=0) batch.draw(square1Img,
                        ((square1Img.getWidth() * i) - square1Img.getWidth()/2),
                        ((square1Img.getHeight()) * ii));
                sprite.setRotation(180f);
                if(ii%2==0)sprite.setPosition((sprite.getWidth()) * i - square1Img.getWidth()/2, (sprite.getHeight()) * ii);
                if(ii%2!=0)sprite.setPosition((sprite.getWidth()) * i - square1Img.getWidth(),(sprite.getHeight()) * ii);
                sprite.draw(batch);
            }
        }


        batch.disableBlending();
        batch.end();
    }
}
