package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

/**
 * Created by XelnectMobileUser on 2/17/2015.
 */
public class SnubTrihexagonalTillingRending extends MyGdxGame {

    static Array<Sprite> componentSprites;
    static Sprite sp0;
    static Sprite sp1;
    static Sprite sp2;
    static Sprite sp3;
    static Sprite sp4;
    static Sprite sp5;
    static Sprite sp6;
    static Sprite sp7;
    static Sprite sp8;

    public static void SnubTrihexagonaltillingLauncher() {
        batch.begin();
        batch.enableBlending();
        //the + and - 3 are because of the white lines
        //drawn=true;
        for (int i = 0; i < 30; i++) {//15
            for (int ii = 0; ii < 40; ii++) {//27
                //even thought i am considering doing stuff in another way it is still to be noted that this gave me a texture with random color
                //groupSpriteSTT.setPosition(groupSpriteSTT.getWidth()*i, groupSpriteSTT.getHeight()*ii);
                //groupSpriteSTT.draw(batch);


                positionSpritesForDrawing(ii, i*(sp0.getWidth()+sp4.getWidth()/2), -sp4.getHeight()*i);


            }
        }
        batch.disableBlending();
        batch.end();
    }
    public static void CreateCompositeSprite() {
        sp0 = new Sprite(square1Img, square1Img.getWidth(), square1Img.getHeight());
        spriteFortintingSnubTrihexagonalTillingsMiddleTriangle = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp2 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp3 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp4 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp5 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp6 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp7 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());
        sp8 = new Sprite(square2Img, square2Img.getWidth(), square2Img.getHeight());

        componentSprites = new Array<Sprite>();
        componentSprites.add(MyGdxGame.spriteFortintingSnubTrihexagonalTillingsMiddleTriangle);


    }
    public static void setupCompositeSprites(){

        CreateCompositeSprite();
        sp1 = componentSprites.get(0);
        //sp8 = componentSprites.get(1);

        //sp1.setRotation(180f);
        sp1.setColor(0, 0, 1, 1);
        sp1.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp1.setOrigin(sp1.getWidth()/2, sp1.getHeight()/2);
        sp1.setRotation(180f);

        sp2.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));

        sp3.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
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

        sp8.setColor(1, 0, 1, 1);
        sp8.setSize((sp0.getWidth()/2),(sp0.getHeight()/2));
        sp8.setOrigin(sp8.getWidth()/2, sp8.getHeight()/2);

    }
    public static void positionSpritesForDrawing(int ii, float xToBeTranslated, float yToBeTranslated){
        int iii=3000;

        sp0.setPosition(((sp0.getWidth() * ii)+xToBeTranslated)-iii ,// - sp0lastX*i //- sp0lastY*ii*1/4 //- sp0lastY*ii// - sp0lastX*i
                ((sp0.getHeight()* ii)+yToBeTranslated));//sp0lastY/10 // - sp0lastX/10
        sp0.draw(batch);
//region Triangles

        sp1.setPosition((sp0.getX()+sp0.getWidth()*1/4)+sp2.getWidth()/2,sp0.getY()+sp0.getHeight());
        sp1.draw(batch);

        sp2.setPosition((sp0.getX()+sp0.getWidth()*1/4),sp0.getY()+sp0.getHeight());
        sp2.draw(batch);

        sp3.setPosition((sp0.getX()+sp0.getWidth()*1/4),sp0.getY()-sp3.getHeight());
        sp3.draw(batch);

        sp4.setPosition((sp0.getX()+sp0.getWidth()*3/4)+7,sp0.getY()-sp5.getHeight()-15+sp0.getHeight()/2);
        sp4.draw(batch);

        sp5.setPosition((sp0.getX()+sp0.getWidth()*3/4)+7,sp0.getY()+15+sp0.getHeight()/2);
        sp5.draw(batch);

        sp6.setPosition((sp0.getX()-sp0.getWidth()*1/4)+9,sp0.getY()-sp6.getHeight()-15+sp0.getHeight()/2);
        sp6.draw(batch);

        sp7.setPosition((sp0.getX()-sp0.getWidth()*1/4)+9,sp0.getY()+15+sp0.getHeight()/2);
        sp7.draw(batch);

        sp8.setPosition((sp0.getX()+sp0.getWidth()*1/4)-sp2.getWidth(),sp0.getY()+sp0.getHeight());
        sp8.draw(batch);
//endregion

    }
}
