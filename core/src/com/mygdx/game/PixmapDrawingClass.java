package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created by XelnectMobileUser on 2/12/2015.
 */
public class PixmapDrawingClass extends MyGdxGame {

    public static void PixmapDrawRectangles() {
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
    public static void PixmapDrawHexagons() {
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
    public static void PixmapDrawTrianglles(){


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
    public static void PixmapDrawRectangles2() {
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
    public static void PixmapDrawRectangles3(){
        batch.begin();
        for (int i = 0; i < 30; i++) {
            for (int ii = 0; ii < 30; ii++) {


                if (ii % 2 == 0 && i % 2 ==0) spriteForDynamicDrawing.draw(batch);
                spriteForDynamicDrawing.setPosition((spriteForDynamicDrawing.getWidth()) * i
                        ,(spriteForDynamicDrawing.getHeight()) * ii);
                if (ii % 2 != 0 && i % 2 !=0)spriteForDynamicDrawing2.draw(batch);
                spriteForDynamicDrawing2.setPosition((spriteForDynamicDrawing2.getWidth()) * i
                        ,(spriteForDynamicDrawing2.getHeight()) * ii);
                spriteForDynamicDrawing3.draw(batch);
                spriteForDynamicDrawing3.setPosition((spriteForDynamicDrawing3.getWidth() + 64) * i
                        ,(spriteForDynamicDrawing3.getHeight() + 64) * ii);
            }
        }
        batch.end();
    }
    public static void createPixmap(){
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
    public static void createPixmapHexagon(){

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
    public static void createPixmapTrianglle(){


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
    public static Sprite dinamicallyChangeColor(){
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
    public static PolygonSprite dinamicallyChangeColorPoly(){
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
    public static PolygonSprite dinamicallyChangeColorTrianglle(){
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
    public static void spriteSetRandomColor(Sprite spriteToBeTinted){
        Random r = new Random();
        int Low = 0;
        int High = 15;
        random = r.nextInt(High-Low) + Low;
        Color[] randomElement ={Color.BLUE,Color.GREEN,Color.RED,Color.CYAN,Color.BLACK,Color.DARK_GRAY,
                Color.GRAY,Color.MAGENTA,Color.MAROON,Color.NAVY,Color.OLIVE,Color.ORANGE,Color.PINK,Color.PURPLE,
                Color.TEAL,Color.YELLOW};
        if(spriteToBeTinted != null)  spriteToBeTinted.setColor(randomElement[random]);
        else
        {
            // do something else
        }
    }
    public static void spriteSetRandomRotation(Sprite spriteToBeTinted){
        Random r = new Random();
        int Low = 0;
        int High = 4;
        int random1 = r.nextInt(High-Low) + Low;
        float[] rotationDegree ={0f,90f,180f,270f,360f};
        if(spriteToBeTinted != null)  spriteToBeTinted.rotate(rotationDegree[random1]);
        else
        {
            // do something else
        }
    }
}

