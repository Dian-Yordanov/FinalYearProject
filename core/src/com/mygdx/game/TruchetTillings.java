package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Created by XelnectMobileUser on 2/24/2015.
 */
public class TruchetTillings extends MyGdxGame {
    public static void TruchetTillingRendering(){
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
    public static void RandomTruchetTillingRendering(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void RoundEdges1TTR(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                //PixmapDrawingClass.spriteSetRandomColor(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void RoundEdges2TTR(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                //PixmapDrawingClass.spriteSetRandomColor(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void TriangleEdges1TTR(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                //PixmapDrawingClass.spriteSetRandomColor(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void TriangleEdges2TTR(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                PixmapDrawingClass.spriteSetRandomColor(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void RoundEdgesRandomColorTTR(){
        batch.begin();
        for(int i=0;i<50;i++){
            for(int ii=0;ii<50;ii++){
                truchetTileSquare.setPosition(((truchetTileSquare.getWidth())*i),((truchetTileSquare.getHeight())*ii));
                spriteSetRandomRotationModified(truchetTileSquare);
                PixmapDrawingClass.spriteSetRandomColor(truchetTileSquare);
                truchetTileSquare.draw(batch);
            }
        }
        batch.end();
    }
    public static void RecursiveTruchetRendering(){
        batch.begin();

        countdown(0,0);
        Gdx.app.postRunnable(new Thread(new MyGdxGame()));
        //illicountdown(10,10);

        batch.end();
    }
    public static void createContentForPredrawing(){
        Gdx.graphics.setContinuousRendering(false);
        //Gdx.graphics.requestRendering();
    }
    public void illicountdown(int n){
        /*draws a trianglle shaped structure by drawing every row like a diagonal*/
        //Gdx.graphics.setContinuousRendering(false);
        for(int i=0;i<n;i++){
            for(int ii=i;ii<n;ii++){

                /*PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare1);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare2);
                PixmapDrawingClass.spriteSetRandomRotation(truchetTileSquare3);*/

                truchetTileSquare1.setPosition(((truchetTileSquare1.getWidth())*i),((truchetTileSquare1.getHeight())*ii));
                arraySpriteX.add(truchetTileSquare1);
                PixmapDrawingClass.spriteSetRandomRotation(arraySpriteX.get(ii));
                arraySpriteX.get(ii).draw(batch);
                //truchetTileSquare1.draw(batch);
            }
        }

        //Gdx.graphics.setContinuousRendering(false);
    }
    public static void countdown(int n, int m) {
        /*draws a rectangle shaped structure by drawing every element from either the right or the bot of the one behind it and doing that recursivelly*/
        if (n != 5) {
            if (m != 5) {
                oldSchoolDrawing(n,m);
                countdown (n,m+1);
                countdown (n+1,m);
            }
        }
    }
    public static void oldSchoolDrawing(int n, int m){
        truchetTileSquare1.setPosition(((truchetTileSquare1.getWidth())*n),((truchetTileSquare1.getHeight())*m));
        truchetTileSquare1.draw(batch);
    }
    public static void spriteSetRandomRotationModified(Sprite spriteToBeRotated){
        Random r = new Random();
        int Low = 0;
        int High = 3;
        random = r.nextInt(High-Low) + Low;
        switch (random) {
            case 0:
                spriteToBeRotated.rotate90(true);
                break;
            case 1:
                spriteToBeRotated.rotate90(true);
                spriteToBeRotated.rotate90(true);
                break;
            case 2:
                spriteToBeRotated.rotate90(true);
                spriteToBeRotated.rotate90(true);
                spriteToBeRotated.rotate90(true);

                break;
            default:  spriteToBeRotated.rotate90(true);
                break;
        }
    }
}
