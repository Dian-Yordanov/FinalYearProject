package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by XelnectMobileUser on 3/18/2015.
 */
public class Background extends Table {
    public final float width;
    public final float height;

    TextureAtlas atlas;
    NinePatch ninePatch;
    Sprite color;
    Sprite bevel;
    Image border;
    Image combinedBackground;

    private final float rgbMax = 255;
    Color backgroundColor = new Color(86 / rgbMax, 86 / rgbMax, 86 / rgbMax, 1);

    public Background(float width, float height) {
        this.width = width;
        this.height = height;

        initBackground();
    }

    private void initBackground() {
        // BACKGROUND COLOR
        Texture texture = new Texture(Gdx.files.internal("data/108pstriangle.png"));
        TextureRegion whitePixel = new TextureRegion(texture);
        color = new Sprite(whitePixel);
        color.setSize(width, height);
        color.setColor(backgroundColor);
        Texture texture1 = new Texture(Gdx.files.internal("data/ps36angle.png"));
        TextureRegion whitePixel1 = new TextureRegion(texture1);
        bevel = new Sprite(whitePixel1);
        bevel.setSize(width, height);
        bevel.setColor(backgroundColor);

        // BEVEL
        /*atlas = new TextureAtlas(Gdx.files.internal("background.atlas"));
        ninePatch = atlas.createPatch("background");
        bevel = new Image(ninePatch);
        bevel.setSize(width - 2, height - 2);
        bevel.setPosition(getX() + 1, getY() + 1);

        // BORDER
        atlas = new TextureAtlas(Gdx.files.internal("border.atlas"));
        ninePatch = atlas.createPatch("border");
        border = new Image(ninePatch);
        border.setSize(width, height);*/

        // MERGE
        mergeBackground();
    }

    public void mergeBackground() {
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        Batch batch = new SpriteBatch();

        buffer.begin();
        batch.enableBlending();
        Gdx.gl.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(1, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        color.draw(batch);
        bevel.draw(batch, 0.3f);
        /*border.draw(batch, 1f);*/

        batch.end();
        buffer.end();

        TextureRegion combinedTexture = new TextureRegion(buffer.getColorBufferTexture());
        combinedTexture.flip(false, true);

        combinedBackground = new Image(combinedTexture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());

        combinedBackground.draw(batch, parentAlpha);

        resetTransform(batch);

        super.draw(batch, parentAlpha);
    }
}