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
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColor;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorPoly;
import static com.mygdx.game.PixmapDrawingClass.dinamicallyChangeColorTrianglle;

public class MyGdxGame extends ApplicationAdapter {
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

    Array<Sprite> componentSprites;
    Sprite sp0;
    Sprite sp1;
    Sprite sp2;
    Sprite sp3;
    Sprite sp4;
    Sprite sp5;
    Sprite sp6;
    Sprite sp7;
    //endregion
    @Override
    public void create() {
        MyGdxGame.batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();
        polyBatch2 = new PolygonSpriteBatch();

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
        polyBatch.setProjectionMatrix(camera.combined);
        polyBatch2.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        cameraMovingMethod();

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
                SnubTrihexagonaltillingLauncher();
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

            setupCompositeSprites();
        }
    }
    public void checkIfFileExists(String imageNameToBeSavedMGG1) {
        if (!Gdx.files.external(imageNameToBeSavedMGG1).exists()) {
            FileHandle from = Gdx.files.internal(imageNameToBeSavedMGG1);
            from.copyTo(Gdx.files.external(imageNameToBeSavedMGG1));
        }
    }
    public void SnubTrihexagonaltillingLauncher() {
        batch.begin();
        batch.enableBlending();
        //the + and - 3 are because of the white lines
        for (int i = 0; i < 2; i++) {//15
            for (int ii = 0; ii < 2; ii++) {//27
            //even thought i am considering doing stuff in another way it is still to be noted that this gave me a texture with random color
               //groupSpriteSTT.setPosition(groupSpriteSTT.getWidth()*i, groupSpriteSTT.getHeight()*ii);
                //groupSpriteSTT.draw(batch);

                positionSpritesForDrawing(i,ii);



                /*
                if (ii % 2 == 0) batch.draw(spriteSTTCornerTriangles,
                        ((spriteSTTCornerTriangles.getWidth()) * i),
                        (spriteSTTCornerTriangles.getHeight()) * ii);
                if (ii % 2 != 0) batch.draw(spriteSTTCornerTriangles,
                        (((spriteSTTCornerTriangles.getWidth() * i) - spriteSTTCornerTriangles.getWidth() / 2)),
                        ((spriteSTTCornerTriangles.getHeight()) * ii));

                if (ii % 2 == 0)
                    spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.setPosition(
                            ((spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.getWidth()) * i
                            - spriteSTTCornerTriangles.getWidth() / 2), (spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.getHeight()) * ii);
                if (ii % 2 != 0)
                    spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.setPosition(((spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.getWidth()) * i
                            - spriteSTTCornerTriangles.getWidth()), (spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.getHeight()) * ii);
                spriteFortintingSnubTrihexagonalTillingsMiddleTriangle.draw(batch);
               */
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
                PixmapDrawingClass.spriteSetRandomColor(spriteFortintingSnubTrihexagonalTillingsMiddleTriangle);

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
    public void CreateCompositeSprite() {
        sp0 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
        spriteFortintingSnubTrihexagonalTillingsMiddleTriangle = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp2 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp3 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp4 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp5 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp6 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp7 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());

        componentSprites = new Array<Sprite>();
        componentSprites.add(MyGdxGame.spriteFortintingSnubTrihexagonalTillingsMiddleTriangle);
        //componentSprites.add(MyGdxGame.spriteForDynamicDrawingHexagon);

        //componentSprites.add(MyGdxGame.spriteSTTCornerTriangles);
        //componentSprites.add(MyGdxGame.spriteSTTCornerTriangles);
    }
    public void setupCompositeSprites(){

        CreateCompositeSprite();
        sp1 = componentSprites.get(0);
        //sp1 = componentSprites.get(0);
        //sp2 = componentSprites.get(2);
        //sp3 = componentSprites.get(3);
        //componentSprites.get(3).equals(componentSprites.get(2));

        sp1.setRotation(180f);
        sp1.setColor(0, 0, 1, 1);

        //this methods gives problems with memory
        //PixmapDrawingClass.spriteSetRandomColor(sp1);
       // resize(sp2,sp0);

        sp2.setSize((sp0.getWidth()/2)+10,(sp0.getHeight()/2) + 10);

        sp3.setSize((sp0.getWidth()/2)+10,(sp0.getHeight()/2) + 10);
        sp3.setOrigin(sp3.getWidth()/2, sp3.getHeight()/2);
        sp3.setRotation(180f);

        //sp4.setColor(0, 0, 1, 1);
        sp4.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp4.setOrigin(sp4.getWidth()/2, sp4.getHeight()/2);
        sp4.setRotation(240f);

        sp5.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp5.setOrigin(sp5.getWidth()/2, sp5.getHeight()/2);
        sp5.setRotation(300f);

        sp6.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp6.setOrigin(sp6.getWidth()/2, sp6.getHeight()/2);
        sp6.setRotation(240);

        sp7.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp7.setOrigin(sp7.getWidth()/2, sp7.getHeight()/2);
        sp7.setRotation(300f);

    }
    public void positionSpritesForDrawing(int i, int ii){
        int iii=2000;

        sp0.setPosition((sp0.getWidth()*i)+iii,(sp0.getHeight()*ii)+iii);
        sp0.draw(batch);

        sp1.setPosition(sp0.getX()-1000,sp0.getY()-1000);
        sp1.draw(batch);

        sp2.setPosition((sp0.getX()+sp0.getWidth()*1/4)-5,sp0.getY()+sp0.getHeight());
        sp2.draw(batch);

        sp3.setPosition((sp0.getX()+sp0.getWidth()*1/4)-5,sp0.getY()-sp3.getHeight());
        sp3.draw(batch);

        sp4.setPosition((sp0.getX()+sp0.getWidth()*3/4)+7,sp0.getY()-sp5.getHeight()-15+sp0.getHeight()/2);
        sp4.draw(batch);

        sp5.setPosition((sp0.getX()+sp0.getWidth()*3/4)+7,sp0.getY()+15+sp0.getHeight()/2);
        sp5.draw(batch);

        sp6.setPosition((sp0.getX()-sp0.getWidth()*1/4)+9,sp0.getY()-sp6.getHeight()-15+sp0.getHeight()/2);
        sp6.draw(batch);

        sp7.setPosition((sp0.getX()-sp0.getWidth()*1/4)+9,sp0.getY()+15+sp0.getHeight()/2);
        sp7.draw(batch);

    }
}