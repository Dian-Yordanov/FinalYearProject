package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import java.util.Iterator;
import java.util.Random;

import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColor;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorPoly;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorTrianglle;

public class MyGdxGame extends ApplicationAdapter implements Runnable{
    //region Constructors
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
    public static Sprite spriteFortintingSnubTrihexagonalTillingsMiddleTriangle;
    public static Sprite spriteSTTCornerTriangles;

    //public static Sprite groupSpriteSTT;

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
    static Texture textureSolidTrinaglle;

    static PolygonSprite polyTrinaglle;
    static PolygonSprite polyTrinaglle2;

    static PolygonSpriteBatch polyBatch2;

    static PolygonSprite poly;
    static PolygonSpriteBatch polyBatch;
    static Texture textureSolid;

    static int random;

    public static Sprite truchetTileSquare;
    public static Sprite truchetTileSquare1;
    public static Sprite truchetTileSquare2;
    public static Sprite truchetTileSquare3;


    int doRandomRotation;
    public static boolean doneOnce;
    public static int intToBeReduced1;
    public static int intToBeReduced2;


    int sumaryXi=0;
    public static Array<Sprite> arraySpriteX;
    //endregion
    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();
        polyBatch2 = new PolygonSpriteBatch();

        //intToBeReduced1=1;
        //intToBeReduced2=1;
        doRandomRotation=0;

        //doneOnce=false;

        //PeriodicTillingsRendering PTR = new PeriodicTillingsRendering();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher") ||
                patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
            checkIfFileExists(imageNameToBeSavedMGG2);
        }


        PixmapDrawingClass.createPixmapHexagon();
        PixmapDrawingClass.createPixmap();
        poly = dinamicallyChangeColorPoly();

        PixmapDrawingClass.createPixmapTrianglle();
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

        try {
            polyBatch.setProjectionMatrix(camera.combined);
            polyBatch2.setProjectionMatrix(camera.combined);
            batch.setProjectionMatrix(camera.combined);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        cameraMovingMethod();
        zoomableCamera();

        /*Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);
            }

        },3,1);*/

        if(optionSelected){evolvingTilling = false;}
        if(!evolvingTilling) {
            if (patternStyle.equals("SquareTillingLauncher") ||
                    patternStyle.equals("SquareTillingLauncher2") ||
                    patternStyle.equals("SquareTillingLauncher3")) {
                PeriodicTillingsRendering.SquareRendering();
            }
            if (patternStyle.equals("HexagonalTillingLauncher")
                    || patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
                PeriodicTillingsRendering.HexagonalRendering();
            }
            if (patternStyle.equals("TriangullarTillingLauncher")
                    || patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
                PeriodicTillingsRendering.TriangullargleRendering();
            }
            if (patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
                SnubTrihexagonalTillingRending.SnubTrihexagonaltillingLauncher();
            }
            if (patternStyle.equals("TruchetTillingLauncher")) {
                TruchetTillingRendering();
            }
            if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
                RecursiveTruchetRendering();
            }
            optionSelected=false;
            evolvingTilling=false;
        }

        if(evolvingTilling){
            if (patternStyle.equals("SquareTillingLauncher")) {
                PixmapDrawingClass.PixmapDrawRectangles();
            }
            if (patternStyle.equals("SquareTillingLauncher2")) {
                PixmapDrawingClass.PixmapDrawRectangles2();
            }
            if (patternStyle.equals("SquareTillingLauncher3")) {
                PixmapDrawingClass.PixmapDrawRectangles3();
            }
            if (patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
                PixmapDrawingClass.PixmapDrawHexagons();
            }
            if (patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
                PixmapDrawingClass.PixmapDrawTrianglles();
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

        //from x1 to x2 and from y1 to y2 was 1 2 2 1
        cameraBoundaryX1= Gdx.graphics.getWidth()/2;
        cameraBoundaryX2= (int) (3f * Gdx.graphics.getWidth());
        cameraBoundaryY1= (int) (3f * Gdx.graphics.getHeight());
        cameraBoundaryY2= Gdx.graphics.getHeight()/2;

        //camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        //input = new Vector3(x1, y1, 0);

        camera.update();

    }
    public void createContent() {
        //PixmapDrawingClass PDC = new PixmapDrawingClass();
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
        if (patternStyle.equals("TruchetTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
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

            SnubTrihexagonalTillingRending.setupCompositeSprites();
        }
        if (patternStyle.equals("TruchetTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare1 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare2 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare3 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());

            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);

        }
        if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare1 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare2 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare3 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());

            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
            PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);

        }
    }
    public void checkIfFileExists(String imageNameToBeSavedMGG1) {
        if (!Gdx.files.external(imageNameToBeSavedMGG1).exists()) {
            FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG1);
            from.copyTo(Gdx.files.external(imageNameToBeSavedMGG1));
        }
    }
    public void zoomableCamera(){
        for (int i = 0; i < 20; i++) { // 20 is max number of touch points
            if (Gdx.input.isTouched(i) && i==1) {
                int sumaryXii=Math.abs(Gdx.input.getX(i)-Gdx.input.getX(i-1));

                if(sumaryXi>=sumaryXii && i!=2){
                    camera.zoom +=0.05;
                }
                if(sumaryXi<sumaryXii && i!=2){
                    camera.zoom -=0.05;
                }
                sumaryXi = sumaryXii;
            }
        }
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

                if (patternStyle.equals("SquareTillingLauncher")||
                        patternStyle.equals("SquareTillingLauncher2") ||
                        patternStyle.equals("SquareTillingLauncher3")) {
                    spriteForDynamicDrawing = dinamicallyChangeColor();
                    spriteForDynamicDrawing2 = dinamicallyChangeColor();
                    spriteForDynamicDrawing3 = dinamicallyChangeColor();
                }
                if (patternStyle.equals("HexagonalTillingLauncher") ||
                        patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
                    poly = dinamicallyChangeColorPoly();
                }
                if (patternStyle.equals("TriangullarTillingLauncher") ||
                        patternStyle.equals("EvolvingTriangullarTillingLauncher")) {
                    polyTrinaglle = dinamicallyChangeColorTrianglle();
                    polyTrinaglle2 = dinamicallyChangeColorTrianglle();
                }
                if (patternStyle.equals("SnubTrihexagonalTilingLauncher")) {
                    PixmapDrawingClass.spriteSetRandomColor(spriteFortintingSnubTrihexagonalTillingsMiddleTriangle);
                }
                if (patternStyle.equals("TruchetTillingLauncher")) {
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);
                }
                if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
                    PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);


                //camera.rotateAround(camera.position,camera.position,90);
                }

                Gdx.app.log("somelog11", " " + x11 + " " + y11);


                if (camera.position.x >= cameraBoundaryX1 && camera.position.y <= cameraBoundaryY1 &&
                        camera.position.x <= cameraBoundaryX2 && camera.position.y >= cameraBoundaryY2) {//&& camera.position.x < 3200 && camera.position.y > 0
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
    public void TruchetTillingRendering(){
        batch.begin();
        for (int i = 0; i < 50; i++) {
            for (int ii = 0; ii < 50; ii++) {
                //truchetTileSquare.setPosition(((truchetTileSquare.getWidth() + 5) * i) - Gdx.graphics.getWidth()
                //        , ((truchetTileSquare.getHeight() + 5) * ii) - Gdx.graphics.getHeight());
                //truchetTileSquare.draw(batch);

                //createRandomTillingNumbering();
                //PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquareTintedColor.get(0));
                try {
                    if (!doneOnce){
                        PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
                        PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
                        PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
                        PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);
                        doneOnce=true;intToBeReduced1--;intToBeReduced2--;
                    }
                    //PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);

                    if(ii % 2 == 0 && i  % 2 == 0 ){
                        truchetTileSquare.setPosition(((truchetTileSquare.getWidth()) * i)
                                , ((truchetTileSquare.getHeight()) * ii));
                        truchetTileSquare.draw(batch);
                    }
                    if(ii % 2 != 0 && i  % 2 != 0 ){
                        truchetTileSquare1.setPosition(((truchetTileSquare1.getWidth()) * i)
                                , ((truchetTileSquare1.getHeight()) * ii));
                        truchetTileSquare1.draw(batch);
                    }
                    if(ii % 2 != 0 && i  % 2 == 0 ){
                        truchetTileSquare2.setPosition(((truchetTileSquare2.getWidth()) * i)
                                , ((truchetTileSquare2.getHeight()) * ii));
                        truchetTileSquare2.draw(batch);
                    }
                    if(ii % 2 == 0 && i  % 2 != 0 ){
                        truchetTileSquare3.setPosition(((truchetTileSquare3.getWidth()) * i)
                                , ((truchetTileSquare3.getHeight()) * ii));
                        truchetTileSquare3.draw(batch);
                    }

                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        batch.end();
    }
    public void RecursiveTruchetRendering(){
        batch.begin();

        countdown(0,0);
        Gdx.app.postRunnable(new Thread(new MyGdxGame()));
        //illicountdown(10,10);

        batch.end();
    }
    /*draws a trianglle shaped structure by drawing every row like a diagonal*/
    public void illicountdown(int n, int m){
        int nMax=n;
        for(int i=0;i<n;i++){
            for(int ii=i;ii<n;ii++){

                truchetTileSquare1.setPosition(((truchetTileSquare1.getWidth())*i),((truchetTileSquare1.getHeight())*ii));
                truchetTileSquare1.draw(batch);
            }
        }
    }
    /*draws a rectangle shaped structure by drawing every element from either the right or the bot of the one behind it and doing that recursivelly*/
    public void countdown (int n, int m) {
        if (n != 5) {
            if (m != 5) {
                oldSchoolDrawing(n,m);
                countdown (n,m+1);
                countdown (n+1,m);
            }
        }
    }
    public void oldSchoolDrawing(int n, int m){
        truchetTileSquare1.setPosition(((truchetTileSquare1.getWidth())*n),((truchetTileSquare1.getHeight())*m));
        truchetTileSquare1.draw(batch);

    }

    @Override
    public void run() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                //countdown(0,0);
                Gdx.app.error("runnable","111");
                /*see fps*/
                Gdx.app.error("fps","" + Gdx.graphics.getFramesPerSecond());
            }
        });
    }
}