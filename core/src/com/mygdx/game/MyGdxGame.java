package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColor;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorPoly;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorTrianglle;
public class MyGdxGame extends ApplicationAdapter implements Runnable {
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
    //public static Background bg;
    //public static Sprite groupSpriteSTT;

    private static OrthographicCamera camera;
    int cameraBoundaryX1 = -1600, cameraBoundaryX2 = 3200, cameraBoundaryY1 = 8000, cameraBoundaryY2 = 0;
    Vector2 dragOld, dragNew;
    public static boolean evolvingTilling;
    public static boolean optionSelected = false;

    //ShapeRenderer shapeRenderer;

    public static Texture texture;
    public static Texture textureSmall;

    public static Pixmap pixmap;
    public static Pixmap pixmapSmall;

    public static Pixmap pixmapTrianglle;
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

    public static Sprite penroseTile1;
    public static Sprite penroseTile2;
    public static Sprite penroseTile3;

    int doRandomRotation;
    public static boolean doneOnce;
    public static int intToBeReduced1;
    public static int intToBeReduced2;

    float cameraZoomLevel = 0;
    public static int sumaryXi = 0;
    public static Array<Sprite> arraySpriteX;
    public static Texture foreground;
    public static Sprite penroseTile4;
    public static Sprite combinedSprite;
    public static Array<Sprite> penroseSprites = new Array<Sprite>();
    public static int globalInt=0;
    //endregion
    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();
        polyBatch2 = new PolygonSpriteBatch();

        //intToBeReduced1=1;
        //intToBeReduced2=1;
        doRandomRotation = 0;

        doneOnce = false;

        //PeriodicTillingsRendering PTR = new PeriodicTillingsRendering();

        checkIfFileExists(imageNameToBeSavedMGG);
        if (patternStyle.equals("TriangullarTillingLauncher") ||
                patternStyle.equals("SnubTrihexagonalTilingLauncher") ||
                patternStyle.equals("PenroseTilling")) {
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
      /*  createStage();*/
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        cameraMovingMethod();
        zoomableCamera();


        if (optionSelected) {
            evolvingTilling = false;
        }
        if (!evolvingTilling) {
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
                TruchetTillings.TruchetTillingRendering();
            }
            if (patternStyle.equals("RandomTruchetTillingLauncher")) {
                TruchetTillings.RandomTruchetTillingRendering();
            }
            if (patternStyle.equals("RoundEdges1TTL")) {
                TruchetTillings.RoundEdges1TTR();
            }
            if (patternStyle.equals("RoundEdges2TTL")) {
                TruchetTillings.RoundEdges2TTR();
            }
            if (patternStyle.equals("TriangleEdges1TTL")) {
                TruchetTillings.TriangleEdges1TTR();
            }
            if (patternStyle.equals("TriangleEdges2TTL")) {
                TruchetTillings.TriangleEdges2TTR();
            }
            if (patternStyle.equals("RoundEdgesRandomColorTTL")) {
                TruchetTillings.RoundEdgesRandomColorTTR();
            }
            if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
                TruchetTillings.RecursiveTruchetRendering();
            }
            if (patternStyle.equals("PenroseTilling")) {
                penroseTillingRendering();
            }
            optionSelected = false;
            evolvingTilling = false;
        }

        if (evolvingTilling) {
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
    public void dispose() {
        batch.dispose();
    }
    @Override
    public void run() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                //countdown(0,0);
                Gdx.app.error("runnable", "111");
                /*see fps*/
                Gdx.app.error("fps", "" + Gdx.graphics.getFramesPerSecond());
            }
        });
    }
    public void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(1.5f * camera.viewportWidth, 1.5f * camera.viewportHeight, 0);
        camera.update();

        //from x1 to x2 and from y1 to y2 was 1 2 2 1
        cameraBoundaryX1 = Gdx.graphics.getWidth() / 2;
        cameraBoundaryX2 = (int) (3f * Gdx.graphics.getWidth());
        cameraBoundaryY1 = (int) (3f * Gdx.graphics.getHeight());
        cameraBoundaryY2 = Gdx.graphics.getHeight() / 2;

        camera.update();

    }
    public void createContent() {
        //region ContentCreation
        AssetManager manager;
        manager = new AssetManager(new ExternalFileHandleResolver());
        if (patternStyle.equals("SquareTillingLauncher") ||
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
        if (patternStyle.equals("RandomTruchetTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("RoundEdges1TTL")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("RoundEdges2TTL")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangleEdges1TTL")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangleEdges2TTL")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("RoundEdgesRandomColorTTL")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("RecursiveTruchetTillingLauncher")) {
            manager.load(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("PenroseTilling")) {
            manager.load(pictureAddress, Texture.class);
            manager.load(pictureAddress2, Texture.class);
        }
        manager.finishLoading();
        if (patternStyle.equals("SquareTillingLauncher") ||
                patternStyle.equals("SquareTillingLauncher2") ||
                patternStyle.equals("SquareTillingLauncher3")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("HexagonalTillingLauncher") ||
                patternStyle.equals("EvolvingHexagonalTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);
        }
        if (patternStyle.equals("TriangullarTillingLauncher") ||
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
       /* if (patternStyle.equals("PenroseTilling")) {
            square1Img = manager.get(pictureAddress, Texture.class);
            square2Img = manager.get(pictureAddress2, Texture.class);

            penroseTile1 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            penroseTile2 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        }*/

        if (patternStyle.equals("RandomTruchetTillingLauncher")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        if (patternStyle.equals("RoundEdges1TTL")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        if (patternStyle.equals("RoundEdges2TTL")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        if (patternStyle.equals("TriangleEdges1TTL")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        if (patternStyle.equals("TriangleEdges2TTL")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        if (patternStyle.equals("RoundEdgesRandomColorTTL")) {
            square1Img = manager.get(pictureAddress, Texture.class);

            truchetTileSquare = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            truchetTileSquare.setOrigin(truchetTileSquare.getWidth() / 2, truchetTileSquare.getHeight() / 2);

            TruchetTillings.createContentForPredrawing();
        }
        //endregion
        if (patternStyle.equals("PenroseTilling")) {
            square1Img = manager.get(pictureAddress, Texture.class);
            penroseTile1 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            penroseTile3 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
            square2Img = manager.get(pictureAddress2, Texture.class);
            penroseTile2 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
            TruchetTillings.createContentForPredrawing();
            /*bg = new Background(800,800);*/
            penroseSprites.addAll(penroseTile1, penroseTile2, penroseTile3);
            createThePenroseTiles();
        }
    }
    public void checkIfFileExists(String imageNameToBeSavedMGG1) {
        if (!Gdx.files.external(imageNameToBeSavedMGG1).exists()) {
            FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG1);
            from.copyTo(Gdx.files.external(imageNameToBeSavedMGG1));
        }
    }
    public void zoomableCamera() {
        for (int i = 0; i < 20; i++) { // 20 is max number of touch points
            if (Gdx.input.isTouched(i) && i == 1) {
                int sumaryXii = Math.abs(Gdx.input.getX(i) - Gdx.input.getX(i - 1));
                Gdx.app.log("sss", "" + cameraZoomLevel);

                if (sumaryXi >= sumaryXii && i != 2) {
                    camera.zoom += 0.05;
                    cameraZoomLevel += 0.05;
                }
                if (cameraZoomLevel > -0.40) {
                    if (sumaryXi < sumaryXii && i != 2) {
                        camera.zoom -= 0.05;
                        cameraZoomLevel -= 0.05;
                    }
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
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)) {
                //region cameraOnTouchModifications
                Gdx.app.log("somelog1122", " " + dragOld.x + " " + dragNew.x + " ");
                float x11 = dragOld.x - dragNew.x;
                float y11 = dragOld.y - dragNew.y;

                if (patternStyle.equals("SquareTillingLauncher") ||
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

                }

                //endregion
                /*if (patternStyle.equals("RandomTruchetTillingLauncher")) {
                    spriteSetRandomRotationModified(truchetTileSquare);
                }*/
                //region cameraBoundaries
                if (camera.position.x >= cameraBoundaryX1 && camera.position.y <= cameraBoundaryY1 &&
                        camera.position.x <= cameraBoundaryX2 && camera.position.y >= cameraBoundaryY2) {//&& camera.position.x < 3200 && camera.position.y > 0
                    camera.translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
                    camera.update();
                } else {
                    if (camera.position.x < cameraBoundaryX1) {
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
                    if (camera.position.y < cameraBoundaryY2) {
                        if (y11 < 0) {//&& camera.position.x < -1600
                            camera.translate(0, dragNew.y - dragOld.y);
                            camera.update();
                        }
                    }
                }
                dragOld = dragNew;
                //endregion
            }
        }
    }
    public void penroseTillingRendering() {

        batch.begin();
        for (int i = 0; i < 1; i++) {
            for (int ii = 0; ii < 1; ii++) {



                //createPixmapFromSprite();

                /*penroseTile4.setPosition(2000,2500);
                penroseTile4.draw(batch);*/


                 /*bg.draw(batch,1);
                mergeSprites(penroseTile1,penroseTile2);*/
                penroseSprites.get(0).setPosition(1000,1000);
                penroseSprites.get(0).draw(batch);
                penroseSprites.get(1).setPosition(1000,1500);
                penroseSprites.get(1).draw(batch);
                penroseSprites.get(2).setPosition(1000,2000);
                penroseSprites.get(2).draw(batch);
                penroseSprites.get(3).setPosition(1000,2500);
                penroseSprites.get(3).draw(batch);
               /* penroseSprites.get(4).setPosition(1000,2000);
                penroseSprites.get(4).draw(batch);*/
              /*  penroseSprites.get(4).setPosition(1000,2000);
                penroseSprites.get(4).draw(batch);
                penroseSprites.get(5).setPosition(1000,2000);
                penroseSprites.get(5).draw(batch);*/
                combinedSprite.setPosition(2000,2000);
                combinedSprite.draw(batch);
                /*penroseSprites.get(4).setPosition(2000,2000);
                penroseSprites.get(4).draw(batch);*/


            }
        }
        batch.end();
    }
    public static void createTypeATile() {//int i, int ii, //int posX,int posY, Batch batch
        penroseSprites.get(globalInt+1).setPosition(
                penroseSprites.get(globalInt).getX() - penroseSprites.get(globalInt+1).getWidth(),
                penroseSprites.get(globalInt).getY() - penroseSprites.get(globalInt+1).getHeight() / 2);

        penroseSprites.get(globalInt+1).setOrigin(penroseSprites.get(globalInt+1).getWidth()
                , penroseSprites.get(globalInt+1).getHeight() / 2);
        penroseSprites.get(globalInt+1).setRotation(-126);
       /* penroseTile2.draw(batch,1f);
        penroseTile1.draw(batch,1f);*/
    }
    public static void createTypeBTile() {//int i, int ii, //int posX,int posY, Batch batch
        /*createTypeATile(posX,posY,batch);//i,ii,*/

        //remove this and make it with tile combination
        /*penroseSprites.get(globalInt+1).setPosition(
                penroseSprites.get(globalInt).getX() - penroseSprites.get(globalInt+1).getWidth(),
                penroseSprites.get(globalInt).getY() - penroseSprites.get(globalInt+1).getHeight() / 2);

        penroseSprites.get(globalInt+1).setOrigin(penroseSprites.get(globalInt+1).getWidth(),
                penroseSprites.get(globalInt+1).getHeight() / 2);
        penroseSprites.get(globalInt+1).setRotation(-126);*/


        /*penroseTile2.draw(batch,1f);
        penroseTile1.draw(batch,1f);*/
        //

       /* penroseSprites.get(globalInt+1).setOrigin(0, 0);
        penroseSprites.get(globalInt+1).setPosition(penroseSprites.get(globalInt).getX(), penroseSprites.get(globalInt).getY());
        penroseSprites.get(globalInt+1).setRotation(180);
        penroseSprites.get(globalInt+1).setOrigin(penroseSprites.get(globalInt+1).getWidth() / 2, penroseSprites.get(globalInt+1).getHeight());
        penroseSprites.get(globalInt+1).setPosition(penroseSprites.get(globalInt).getX() + penroseSprites.get(globalInt).getWidth() / 2
                , penroseSprites.get(globalInt).getY() + penroseSprites.get(globalInt).getHeight());
        penroseSprites.get(globalInt+1).rotate(36);*/

        /*penroseSprites.get(2).setOrigin(0, 0);
        penroseSprites.get(2).setPosition(penroseSprites.get(3).getX(), penroseSprites.get(3).getY());
        penroseSprites.get(2).setRotation(180);
        penroseSprites.get(2).setOrigin(penroseSprites.get(2).getWidth() / 2, penroseSprites.get(2).getHeight());
        penroseSprites.get(2).setPosition(penroseSprites.get(3).getX() + penroseSprites.get(3).getWidth() / 2
                , penroseSprites.get(3).getY() + penroseSprites.get(3).getHeight());
        penroseSprites.get(2).rotate(36);*/

       /* penroseSprites.get(2).setPosition(
                penroseSprites.get(1).getX() - penroseSprites.get(2).getWidth(),
                penroseSprites.get(1).getY() - penroseSprites.get(2).getHeight() / 2);

        penroseSprites.get(2).setOrigin(penroseSprites.get(2).getWidth()
                , penroseSprites.get(2).getHeight() / 2);*/

        //penroseSprites.get(1).setRotation(22);


        penroseSprites.get(2).setRotation(36);
        /*penroseSprites.get(4).setRotation(-126);
        penroseSprites.get(5).setRotation(-126);*/

       // penroseTile3.draw(batch,1f);
        //penroseSprites.get(globalInt).rotate(36);

        //xyengine.logGdx(globalInt);
        //penroseSprites.get(globalInt).rotate(50);
        //penroseSprites.get(globalInt).setPosition(penroseSprites.get(globalInt+1).getX()+100
        //        , penroseSprites.get(globalInt+1).getY()+100);

        //penroseSprites.get(globalInt).translate(0,100);

        /*penroseSprites.get(globalInt).rotate(50);
        penroseSprites.get(globalInt+1).rotate(50);*/

        //penroseSprites.get(globalInt+1).translate(0,100);
    }
    public static Sprite mergeSprites(Sprite sprite1,Sprite sprite2) {
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        Batch batch = new SpriteBatch();

        buffer.begin();
        batch.enableBlending();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();

        createTheSpritesPositionAndRotation(sprite1,sprite2,batch);

        batch.end();
        buffer.end();

        TextureRegion combinedTexture = new TextureRegion(buffer.getColorBufferTexture());
        combinedTexture.flip(false, true);

        //combinedSprite = new Sprite(combinedTexture);
        return new Sprite(combinedTexture);
    }
    public static void createTheSpritesPositionAndRotation(Sprite sprite1,Sprite sprite2,Batch batch){
        sprite1.draw(batch,1f);
        sprite2.draw(batch,1f);
    }
    public static void createThePenroseTiles(){
        //xyengine.logGdx(globalInt);
        createTypeATile(); //2000,2000,batch
        combinedSprite = mergeSprites(penroseSprites.get(0),penroseSprites.get(1));//penroseSprites.get(globalInt),penroseSprites.get(globalInt+1)
        penroseSprites.add(combinedSprite);

        globalInt=penroseSprites.size-2;
        /*penroseSprites.addAll(penroseSprites.get(0),penroseSprites.get(1));*/

        createTypeBTile(); //2000,2000,batch
        combinedSprite = mergeSprites(penroseSprites.get(2),combinedSprite);//penroseSprites.get(globalInt),penroseSprites.get(globalInt+1)
        penroseSprites.add(combinedSprite);

        //xyengine.logGdx(globalInt);
        /*createTypeBTile(); //2000,2500,batch
        combinedSprite = mergeSprites();//penroseSprites.get(globalInt),penroseSprites.get(globalInt+1)
        penroseSprites.add(combinedSprite);*/

       /* penroseSprites.addAll(penroseTile1, penroseTile2);penroseSprites.addAll(penroseTile1, penroseTile2);
        mergeSprites(penroseSprites.get(0),penroseSprites.get(1));
        Sprite combinedSpriteForArray = combinedSprite;
        penroseSprites.add(combinedSpriteForArray);
        createTypeBTile(1500, 1500,batch);*/
    }
}