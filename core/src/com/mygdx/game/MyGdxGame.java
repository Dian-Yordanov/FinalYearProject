package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
    public static Sprite spriteForDynamicDrawing3;
    public static Sprite spriteForDynamicDrawingHexagon;

    private OrthographicCamera camera;
    int cameraBoundaryX1 = -1600, cameraBoundaryX2 = 3200
            , cameraBoundaryY1 = 8000, cameraBoundaryY2 =0;
    Vector2 dragOld, dragNew;
    public static boolean evolvingTilling;
    public static boolean optionSelected=false;

    //ShapeRenderer shapeRenderer;

    public static Texture texture;
    public static Texture textureSmall;

    public static  Pixmap pixmap;
    public static  Pixmap pixmapSmall;

    public static  Pixmap pixmapTrianglle;
    Texture textureSolidTrinaglle;

    PolygonSprite polyTrinaglle;
    PolygonSprite polyTrinaglle2;

    PolygonSpriteBatch polyBatch2;

    PolygonSprite poly;
    PolygonSpriteBatch polyBatch;
    Texture textureSolid;

    int random;

    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();
        polyBatch2 = new PolygonSpriteBatch();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher") ||
                patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
            checkIfFileExists(imageNameToBeSavedMGG2);
        }


        createPixmapHexagon();
        createPixmap();
        poly = dinamicallyChangeColorPoly();

        createPixmapTrianglle();
        polyTrinaglle = dinamicallyChangeColorTrianglle();
        polyTrinaglle2 = dinamicallyChangeColorTrianglle();

        createContent();
        createCamera();
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
        polyBatch.setProjectionMatrix(camera.combined);
        polyBatch2.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        //shapeRenderer.setProjectionMatrix(camera.combined);
        //camera.update();


        cameraMovingMethod();



        if(optionSelected){evolvingTilling = false;}
        if(!evolvingTilling) {
            if (patternStyle.equals("SquareTillingLauncher") ||
                    patternStyle.equals("SquareTillingLauncher2") ||
                    patternStyle.equals("SquareTillingLauncher3")) {
                SquareRendering();
            }
            if (patternStyle.equals("HexagonalTillingLauncher")
                    || patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
                HexagonalRendering();
            }
            if (patternStyle.equals("TriangullarTillingLauncher")
                    || patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
                TriangullargleRendering();
            }
            if (patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
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
            if (patternStyle.equals("SquareTillingLauncher3")) {
                PixmapDrawRectangles3();
            }
            if (patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
                PixmapDrawHexagons();
            }
            if (patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
                PixmapDrawTrianglles();
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
    @Override
    public void dispose () {
        batch.dispose();
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
        if (patternStyle.equals("SquareTillingLauncher")||
                patternStyle.equals("SquareTillingLauncher2") ||
                patternStyle.equals("SquareTillingLauncher3")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher") ||
                patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangullarTillingLauncher") ||
                patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
            manager.load(pictureAddress2, Texture.class);
        }
        if (patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
            manager.load(pictureAddress, Texture.class);
            manager.load(pictureAddress2, Texture.class);
        }
        manager.finishLoading();
        if (patternStyle.equals("SquareTillingLauncher")||
                patternStyle.equals("SquareTillingLauncher2") ||
                patternStyle.equals("SquareTillingLauncher3")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher") ||
                patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangullarTillingLauncher")||
                patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
            square2Img = manager.get(pictureAddress2, Texture.class);

            sprite = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());

        }
        if (patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
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
                spriteForDynamicDrawing3 = dinamicallyChangeColor();
                poly = dinamicallyChangeColorPoly();
                polyTrinaglle = dinamicallyChangeColorTrianglle();
                polyTrinaglle2 = dinamicallyChangeColorTrianglle();

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
    public void PixmapDrawHexagons() {
        //poly = dinamicallyChangeColorPoly();
        polyBatch.begin();
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                if (i % 2 == 0) {
                    poly.draw(polyBatch);
                    poly.setPosition((((poly.getWidth() + 5) * 3 / 4) * i) - ((poly.getWidth() + 5) * 3 / 4) / 2
                            , (poly.getHeight() + 5) * ii - 15);
                }
                if (i % 2 != 0) {
                    poly.draw(polyBatch);
                    poly.setPosition((((poly.getWidth() + 5) * 3 / 4) * i) - ((poly.getWidth()  + 5) * 3 / 4) / 2
                            , (((poly.getHeight() + 5) * ii) - poly.getHeight() / 2) - 20);
                }
            }
        }
        polyBatch.end();
    }
    public void PixmapDrawTrianglles(){


        polyBatch2.begin();
        for (int i = 0; i < 25; i++) {
            for (int ii = 0; ii < 25; ii++) {

                polyTrinaglle2.setRotation(180f);
                if (ii % 2 == 0){
                    polyTrinaglle2.setPosition(((polyTrinaglle.getWidth()) * i - polyTrinaglle.getWidth() / 2), (polyTrinaglle.getHeight()) * ii - polyTrinaglle.getWidth() / 2 );
                    polyTrinaglle2.draw(polyBatch2);}
                if (ii % 2 != 0){
                    polyTrinaglle2.setPosition(((polyTrinaglle.getWidth()) * i - polyTrinaglle.getWidth()), (polyTrinaglle.getHeight()) * ii - polyTrinaglle.getWidth() / 2 );
                    polyTrinaglle2.draw(polyBatch2);}
                if (ii % 2 == 0) {

                    polyTrinaglle.setPosition(((polyTrinaglle.getWidth()) * i),
                            (polyTrinaglle.getHeight()) * ii + 13);
                    polyTrinaglle.draw(polyBatch2);
                }
                if (ii % 2 != 0) {

                    polyTrinaglle.setPosition(((polyTrinaglle.getWidth()* i) - polyTrinaglle.getWidth() / 2),
                            ((polyTrinaglle.getHeight()) * ii) + 13);
                    polyTrinaglle.draw(polyBatch2);
                }
            }
        }
        polyBatch2.end();
        //Gdx.app.log("11111","22222222");

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
    public void PixmapDrawRectangles3(){
        batch.begin();
        for (int i = 0; i < 30; i++) {
            for (int ii = 0; ii < 30; ii++) {


                if (ii % 2 == 0 && i % 2 ==0) spriteForDynamicDrawing.draw(batch);
                spriteForDynamicDrawing.setPosition((spriteForDynamicDrawing.getWidth()) * i
                        ,(spriteForDynamicDrawing.getHeight()) * ii);
                if (ii % 2 != 0 && i % 2 !=0)spriteForDynamicDrawing2.draw(batch);
                spriteForDynamicDrawing2.setPosition((spriteForDynamicDrawing2.getWidth()) * i
                        ,(spriteForDynamicDrawing2.getHeight()) * ii);
                /*
                if (ii % 2 == 0 && i % 2 !=0) spriteForDynamicDrawing3.draw(batch);
                spriteForDynamicDrawing3.setPosition((spriteForDynamicDrawing3.getWidth()) * i
                        ,(spriteForDynamicDrawing3.getHeight()) * ii);
                if (ii % 2 != 0 && i % 2 ==0) spriteForDynamicDrawing3.draw(batch);
                spriteForDynamicDrawing3.setPosition((spriteForDynamicDrawing3.getWidth()) * i
                        ,(spriteForDynamicDrawing3.getHeight()) * ii);
                */
                spriteForDynamicDrawing3.draw(batch);
                spriteForDynamicDrawing3.setPosition((spriteForDynamicDrawing3.getWidth() + 64) * i
                        ,(spriteForDynamicDrawing3.getHeight() + 64) * ii);
            }
        }
        batch.end();
    }
    public void createPixmap(){
        pixmap = new Pixmap(256,256, Pixmap.Format.RGBA8888);
        pixmapSmall = new Pixmap(128,128, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.RED);
        pixmap.fill();

        pixmapSmall.setColor(Color.PURPLE);
        pixmapSmall.fill();

        texture = new Texture(pixmap);
        textureSmall = new Texture(pixmapSmall);
        //pixmap.dispose();
        spriteForDynamicDrawing = new Sprite(texture);
        spriteForDynamicDrawing2 = new Sprite(texture);
        spriteForDynamicDrawing3 = new Sprite(textureSmall);

        spriteForDynamicDrawing = dinamicallyChangeColor();
        spriteForDynamicDrawing2 = dinamicallyChangeColor();
    }
    public void createPixmapHexagon(){

        //polyBatch.setProjectionMatrix(camera.combined);
        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN); // DE is red, AD is green and BE is blue.
        pixmap.fill();
        textureSolid = new Texture(pixmap);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[] {         60, -104,            // Vertex 0         3--2
                        -60, -104,          // Vertex 1         | /|
                        -120, 0,        // Vertex 2         |/ |
                        -60, 104 ,
                        60, 104,
                        120,0// Vertex 3         0--1 3         0--1
                }, new short[] {
                0,5,4,
                1, 0, 4,         // Two triangles using vertex indices.
                1, 4, 3,
                1,3,2
                // Take care of the counter-clockwise direction.
        });
        poly = new PolygonSprite(polyReg);

        //poly = dinamicallyChangeColorPoly();
        //poly.setOrigin(0, 0);
        //polyBatch = new PolygonSpriteBatch();
      // polyBatch.setProjectionMatrix(camera.combined);
    }
    public void createPixmapTrianglle(){


        //polyBatch.setProjectionMatrix(camera.combined);
        pixmapTrianglle = new Pixmap(256,256, Pixmap.Format.RGBA8888);
        pixmapTrianglle.setColor(Color.GREEN); // DE is red, AD is green and BE is blue.
        pixmapTrianglle.fill();
        textureSolidTrinaglle = new Texture(pixmapTrianglle);
        PolygonRegion polyReg2 = new PolygonRegion(new TextureRegion(textureSolidTrinaglle),
                new float[] {
                        0, 150,            // Vertex 0         3--2
                        130, -75,          // Vertex 1         | /|
                        -130, -75       // Vertex 2         |/ |
                }, new short[] {
                0,2,1
                // Take care of the counter-clockwise direction.
        });
        polyTrinaglle = new PolygonSprite(polyReg2);
        polyTrinaglle2  = new PolygonSprite(polyReg2);
        //poly = dinamicallyChangeColorPoly();
        //poly.setOrigin(0, 0);
        //polyBatch = new PolygonSpriteBatch();
        // polyBatch.setProjectionMatrix(camera.combined);
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
        pixmapSmall.setColor(randomElement[random]);
        pixmapSmall.fill();
        texture = new Texture(pixmap);
        textureSmall = new Texture(pixmapSmall);
        //pixmap.dispose();
        return new Sprite(texture);
    }
    public PolygonSprite dinamicallyChangeColorPoly(){
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
        textureSolid = new Texture(pixmap);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[] {      // Four vertices
                        60, -104,            // Vertex 0         3--2
                        -60, -104,          // Vertex 1         | /|
                        -120, 0,        // Vertex 2         |/ |
                        -60, 104 ,
                        60, 104,
                        120,0// Vertex 3         0--1
                }, new short[] {
                0,5,4,
                1, 0, 4,         // Two triangles using vertex indices.
                1, 4, 3,
                1,3,2
                // Take care of the counter-clockwise direction.
        });
        return new PolygonSprite(polyReg);
    }
    public PolygonSprite dinamicallyChangeColorTrianglle(){
        //pixmap.setColor(Color.GREEN);

        Random r = new Random();
        int Low = 0;
        int High = 15;
        random = r.nextInt(High-Low) + Low;
        Color[] randomElement ={Color.BLUE,Color.GREEN,Color.RED,Color.CYAN,Color.BLACK,Color.DARK_GRAY,
                Color.GRAY,Color.MAGENTA,Color.MAROON,Color.NAVY,Color.OLIVE,Color.ORANGE,Color.PINK,Color.PURPLE,
                Color.TEAL,Color.YELLOW};
        pixmapTrianglle.setColor(randomElement[random]);
        pixmapTrianglle.fill();
        textureSolidTrinaglle = new Texture(pixmapTrianglle);
        PolygonRegion polyReg2 = new PolygonRegion(new TextureRegion(textureSolidTrinaglle),
                new float[] {
                        0, 150,            // Vertex 0         3--2
                        130, -75,          // Vertex 1         | /|
                        -130, -75       // Vertex 2         |/ |
                }, new short[] {
                0,2,1
                // Take care of the counter-clockwise direction.
        });
        return new PolygonSprite(polyReg2);
    }

}
