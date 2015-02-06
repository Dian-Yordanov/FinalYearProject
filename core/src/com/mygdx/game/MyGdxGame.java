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
import com.badlogic.gdx.math.Vector3;

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

    public static Sprite sprite;

    private OrthographicCamera camera;
    int x1;
    int y1;
    Vector3 input;

    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher")) {checkIfFileExists(imageNameToBeSavedMGG2);}
        createContent();
        createCamera();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
        batch.setProjectionMatrix(camera.combined);

        x1 = Gdx.input.getX();
        y1 = Gdx.input.getY();

        final float speed=0.1f,ispeed=1.0f-speed;
        //The result is roughly: old_position*0.9 + target * 0.1
        Vector3 cameraPosition = camera.position;
        cameraPosition.scl(ispeed);
        Vector3 target = new Vector3(x1,y1,0);
        target.scl(speed);
        cameraPosition.add(target);

        float lerp = 0.1f;
        Vector3 position = camera.position;
        position.x += (x1 - position.x) * lerp;
        position.y += (y1-position.y ) * lerp;

        camera.position.set(cameraPosition);

        //camera.position.set(x1,y1, 0);
        //camera.position.set(input);
        Gdx.app.log("MyTag","" + x1 + " "+y1);
        //camera.unproject(input);
        camera.update();

        if (patternStyle.equals("SquareTillingLauncher")) {SquareRendering();}
        if (patternStyle.equals("HexagonalTillingLauncher")) {HexagonalRendering();}
        if (patternStyle.equals("TriangullarTillingLauncher")) {TriangullargleRendering();}
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
                //* height/width;
        camera.update();
    }
    public void createCamera(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        camera.update();

        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        input = new Vector3(x1, y1, 0);
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
            for (int i = 0; i < 10; i++) {
                for (int ii = 0; ii < 10; ii++) {
                    batch.draw(square1Img, (square1Img.getWidth() + 5) * i, (square1Img.getHeight() + 5) * ii);
                }
            }
            batch.end();
    }
    public void HexagonalRendering(){
        batch.begin();
        batch.enableBlending();
        for (int i = 0; i < 10; i++) {
            for (int ii = 0; ii < 10; ii++) {
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
//the + and - 3 are because of the white lines
        for (int i = 0; i < 10; i++) {
            for (int ii = 0; ii < 10; ii++) {
                if(ii%2==0) batch.draw(square1Img,
                        ((square1Img.getWidth()) * i)+3,
                        (square1Img.getHeight()) * ii);
                if(ii%2!=0) batch.draw(square1Img,
                        (((square1Img.getWidth() * i) - square1Img.getWidth()/2))+3,
                        ((square1Img.getHeight()) * ii));
                sprite.setRotation(180f);
                if(ii%2==0)sprite.setPosition(((sprite.getWidth()) * i - square1Img.getWidth()/2) -3, (sprite.getHeight()) * ii);
                if(ii%2!=0)sprite.setPosition(((sprite.getWidth()) * i - square1Img.getWidth()) -3,(sprite.getHeight()) * ii);
                sprite.draw(batch);
            }
        }
        batch.disableBlending();
        batch.end();
    }

}
