package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by XelnectMobileUser on 3/17/2015.
 */
public class CompositeSprite {

    Array<Sprite> componentSprites;

    public CompositeSprite() {
        componentSprites = new Array<Sprite>();
    }

    public void addComponentSprite(Sprite sprite) {
        componentSprites.add(sprite);
    }

    // ... other methods left out for brevity

    public void draw(SpriteBatch spriteBatch) {
        for (Sprite sprite : componentSprites) {
            sprite.draw(spriteBatch);
        }
    }

}