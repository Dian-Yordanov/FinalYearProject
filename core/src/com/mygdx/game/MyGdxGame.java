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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

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
    public static Sprite spriteForDynamicDrawing;
    public static Sprite spriteForDynamicDrawing2;

    private OrthographicCamera camera;
    int cameraBoundaryX1 = -1600, cameraBoundaryX2 = 3200
            , cameraBoundaryY1 = 8000, cameraBoundaryY2 =0;
    Vector2 dragOld, dragNew;
    public static boolean evolvingTilling;
    public static boolean optionSelected=false;

    //ShapeRenderer shapeRenderer;
    public static Texture texture;
    public static  Pixmap pixmap;
    int random;

    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();
        //shapeRenderer = new ShapeRenderer();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher")) {
            checkIfFileExists(imageNameToBeSavedMGG2);
        }


        createPixmap();
        createContent();
        createCamera();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);

        batch.setProjectionMatrix(camera.combined);
        //shapeRenderer.setProjectionMatrix(camera.combined);
        //camera.update();


        cameraMovingMethod();



        if(optionSelected){evolvingTilling = false;}
        if(!evolvingTilling) {
            if (patternStyle.equals("SquareTillingLauncher")) {
                SquareRendering();
            }
            if (patternStyle.equals("HexagonalTillingLauncher")) {
                HexagonalRendering();
            }
            if (patternStyle.equals("TriangullarTillingLauncher")) {
                TriangullargleRendering();
            }
            optionSelected=false;
            evolvingTilling=false;
        }

        if(evolvingTilling){
            if (patternStyle.equals("SquareTillingLauncher")) {
                PixmapDrawRectangles();
            }
            if (patternStyle.equals("SquareTillingLauncher2")) {
                PixmapDrawRectangles2();
            }
        }


    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        //* height/width;
        camera.update();
    }
    public void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(1.5f * camera.viewportWidth, 1.5f * camera.viewportHeight, 0);
        camera.update();

        cameraBoundaryX1= Gdx.graphics.getWidth();
        cameraBoundaryX2= (int) (2f * Gdx.graphics.getWidth());
        cameraBoundaryY1= (int) (2f * Gdx.graphics.getHeight());
        cameraBoundaryY2= Gdx.graphics.getHeight();

        //camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        //input = new Vector3(x1, y1, 0);

        camera.update();

    }
    public void createContent() {
        AssetManager manager;
        manager = new AssetManager(new ExternalFileHandleResolver());
        if (patternStyle.equals("SquareTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangullarTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
            manager.load(pictureAddress2, Texture.class);
        }
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
    public void SquareRendering() {
        batch.begin();
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                batch.draw(square1Img, ((square1Img.getWidth() + 5) * i) - Gdx.graphics.getWidth()
                        , ((square1Img.getHeight() + 5) * ii) - Gdx.graphics.getHeight());
            }
        }
        batch.end();
    }
    public void HexagonalRendering() {
        batch.begin();
        batch.enableBlending();
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                if (i % 2 == 0) {
                    batch.draw(square1Img,
                            (((square1Img.getWidth() + 5) * 3 / 4) * i) - ((square1Img.getWidth() + 5) * 3 / 4) / 2
                            , (square1Img.getHeight() + 5) * ii);
                }
                if (i % 2 != 0) {
                    batch.draw(square1Img,
                            (((square1Img.getWidth() + 5) * 3 / 4) * i) - ((square1Img.getWidth() + 5) * 3 / 4) / 2
                            , (((square1Img.getHeight() + 5) * ii) - square1Img.getHeight() / 2) - 2);
                }
            }
        }
        batch.disableBlending();
        batch.end();
    }
    public void TriangullargleRendering() {
        batch.begin();
        batch.enableBlending();
        //the + and - 3 are because of the white lines
        for (int i = 0; i < 15; i++) {
            for (int ii = 0; ii < 27; ii++) {
                if (ii % 2 == 0) batch.draw(square1Img,
                        ((square1Img.getWidth()) * i),
                        (square1Img.getHeight()) * ii);
                if (ii % 2 != 0) batch.draw(square1Img,
                        (((square1Img.getWidth() * i) - square1Img.getWidth() / 2)),
                        ((square1Img.getHeight()) * ii));
                sprite.setRotation(180f);
                if (ii % 2 == 0)
                    sprite.setPosition(((sprite.getWidth()) * i - square1Img.getWidth() / 2), (sprite.getHeight()) * ii);
                if (ii % 2 != 0)
                    sprite.setPosition(((sprite.getWidth()) * i - square1Img.getWidth()), (sprite.getHeight()) * ii);
                sprite.draw(batch);
            }
        }
        batch.disableBlending();
        batch.end();
    }
    public void cameraMovingMethod() {

        if (Gdx.input.justTouched()) {
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }

        if (Gdx.input.isTouched()) {
            //Gdx.app.log("somelog", " "+ " " + camera.position.x + " " +  camera.position.y + " " +
            //        Gdx.input.getX() + " " + Gdx.input.getY());

            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());

            if (!dragNew.equals(dragOld)) {
                Gdx.app.log("somelog1122", " " + dragOld.x  + " " + dragNew.x + " ");
                float x11 = dragOld.x - dragNew.x;
                float y11 = dragOld.y - dragNew.y;


                spriteForDynamicDrawing = dinamicallyChangeColor();
                spriteForDynamicDrawing2 = dinamicallyChangeColor();

                Gdx.app.log("somelog11", " " + x11 + " " + y11);


                if (camera.position.x > cameraBoundaryX1 && camera.position.y < cameraBoundaryY1 &&
                        camera.position.x < cameraBoundaryX2 && camera.position.y > cameraBoundaryY2) {//&& camera.position.x < 3200 && camera.position.y > 0
                    camera.translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
                    camera.update();
                } else {
                    if (camera.position.x < cameraBoundaryX1 ) {
                        if (x11 > 0) {//&& camera.position.x < -1600
                            camera.translate(dragOld.x - dragNew.x, 0);
                            camera.update();
                        }
                    }
                    if (camera.position.x > cameraBoundaryX2) {
                        if (x11 < 0) {//&& camera.position.x < -1600
                            camera.translate(dragOld.x - dragNew.x, 0);
                            camera.update();
                        }
                    }
                    if (camera.position.y > cameraBoundaryY1) {
                        if (y11 > 0) {//&& camera.position.x < -1600
                            camera.translate(0, dragNew.y - dragOld.y);
                            camera.update();
                        }
                    }
                    if (camera.position.y < cameraBoundaryY2 ) {
                        if (y11 < 0) {//&& camera.position.x < -1600
                            camera.translate(0, dragNew.y - dragOld.y);
                            camera.update();
                        }
                    }



                }
                dragOld = dragNew;
            }
        }
    }
    public void PixmapDrawRectangles() {
        batch.begin();
        for (int i = 0; i < 50; i++) {
            for (int ii = 0; ii < 50; ii++) {
                spriteForDynamicDrawing.draw(batch);
                spriteForDynamicDrawing.setPosition((spriteForDynamicDrawing.getWidth() + 5) * i
                        ,(spriteForDynamicDrawing.getHeight() + 5) * ii);
            }
        }
        batch.end();
    }
    public void PixmapDrawRectangles2() {
        batch.begin();
        for (int i = 0; i < 30; i++) {
            for (int ii = 0; ii < 30; ii++) {


                if (ii % 2 == 0) spriteForDynamicDrawing.draw(batch);
                spriteForDynamicDrawing.setPosition((spriteForDynamicDrawing.getWidth() + 5) * i
                        ,(spriteForDynamicDrawing.getHeight() + 5) * ii);
                if (ii % 2 != 0)spriteForDynamicDrawing2.draw(batch);
                spriteForDynamicDrawing2.setPosition((spriteForDynamicDrawing2.getWidth() + 5) * i
                        ,(spriteForDynamicDrawing2.getHeight() + 5) * ii);
            }
        }
        batch.end();
    }
    public void createPixmap(){
        pixmap = new Pixmap(256,256, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.RED);
        pixmap.fill();

        texture = new Texture(pixmap);
        //pixmap.dispose();
        spriteForDynamicDrawing = new Sprite(texture);
        spriteForDynamicDrawing2 = new Sprite(texture);
    }
    @Override
    public void dispose () {
        batch.dispose();
    }
    public Sprite dinamicallyChangeColor(){
        //pixmap.setColor(Color.GREEN);
        Random r = new Random();
        int Low = 0;
        int High = 15;
        random = r.nextInt(High-Low) + Low;
        Color[] randomElement ={Color.BLUE,Color.GREEN,Color.RED,Color.CYAN,Color.BLACK,Color.DARK_GRAY,
        Color.GRAY,Color.MAGENTA,Color.MAROON,Color.NAVY,Color.OLIVE,Color.ORANGE,Color.PINK,Color.PURPLE,
        Color.TEAL,Color.YELLOW};
        pixmap.setColor(randomElement[random]);
        pixmap.fill();
        texture = new Texture(pixmap);
        //pixmap.dispose();
        return new Sprite(texture);
    }

}
