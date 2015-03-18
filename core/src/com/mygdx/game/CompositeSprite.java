package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

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
    public void addComponentSprite(Array<Sprite> componentSprites1) {
        //for (Sprite sprite : componentSprites1) {
        for(int i=0;i<componentSprites1.size;i++){
        //componentSprites.add(componentSprites1.get(0));
        //componentSprites.add(componentSprites1.get(1));
            xyengine.logGdx(componentSprites.size);
            componentSprites.add(componentSprites1.get(i));
        }

    }

    // ... other methods left out for brevity

    public void draw(SpriteBatch spriteBatch) {
        for (Sprite sprite : componentSprites) {
            sprite.draw(spriteBatch);
        }
    }
    public Sprite generateSpriteFromBufferContents(int x, int y, int width, int height) {
        // I assume you've already drawn what you want with !SpriteBatch at this point...


// grab the framebuffer contents from pixel location [x, y] to [x+width, y+height]:
        TextureRegion frameBufferRegion = ScreenUtils.getFrameBufferTexture(x, y, width, height);
        return new Sprite(frameBufferRegion);


// You can safely clear the framebuffer with glClear after this
        // and draw anything else you want (including the Sprite you
        // just created above)...
    }


}