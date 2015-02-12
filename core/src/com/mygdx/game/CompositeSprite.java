package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

/**
 * Created by XelnectMobileUser on 2/12/2015.
 */
public class CompositeSprite {

    Array<Sprite> componentSprites;

    public CompositeSprite() {
        componentSprites = new Array<Sprite>();
        componentSprites.add(MyGdxGame.spriteForDynamicDrawingHexagon);
        componentSprites.add(MyGdxGame.spriteFortintingSnubTrihexagonalTillingsMiddleTriangle);
        componentSprites.add(MyGdxGame.spriteSTTCornerTriangles);
        componentSprites.add(returnShapeforColoring(MyGdxGame.spriteFortintingSnubTrihexagonalTillingsMiddleTriangle));
    }

    public void addComponentSprite(Sprite sprite) {
        componentSprites.add(sprite);
    }
    public Sprite returnShapeforColoring(Sprite cornertriangleSprite){
        Sprite combinedSprite;
        combinedSprite = cornertriangleSprite;
        return combinedSprite;
    }

    //// ... other methods left out for brevity
//
  //  public void draw(SpriteBatch spriteBatch) {
    //    for (Sprite sprite : componentSprites) {
      //      sprite.draw(batch)
      //  }
   // }

}