package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by XelnectMobileUser on 2/13/2015.
 */
public class PeriodicTillingsRendering extends MyGdxGame {
    public static void SquareRendering() {
        batch.begin();
        for (int i = 0; i < 100; i++) {
            for (int ii = 0; ii < 100; ii++) {
                batch.draw(square1Img, ((square1Img.getWidth() + 5) * i) - Gdx.graphics.getWidth()
                        , ((square1Img.getHeight() + 5) * ii) - Gdx.graphics.getHeight());
            }
        }
        batch.end();
    }
    public static void HexagonalRendering() {
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
    public static void TriangullargleRendering() {
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
}
