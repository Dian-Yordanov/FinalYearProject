package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by XelnectMobileUser on 3/18/2015.
 */
public class PenroseRenderingActivity extends ApplicationAdapter {


    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    private Stage stage;
    private Group group;

    private float rotSpeed = 5;

    @Override
    public void create() {


        // Create a stage
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));

        // Create a group and add it to the stage.
        group = new Group();
        stage.addActor(group);

        // Create images and add them to the group.
        final Texture region = new Texture(Gdx.files.internal("data/108pstriangle.png"));
        Image img = new Image(region);
        Image img2 = new Image(region);
        Image img3 = new Image(region);

        img2.setColor(new Color(0, 1, 0, 1));
        img3.setColor(new Color(0, 0, 1, 1));


        group.addActor(img2);
        group.addActor(img3);
        group.addActor(img);

        // Images are positioned relative to the group...

        img.setPosition(0, 0);
        img2.setPosition(img.getWidth() / 2, 0);
        img3.setPosition(-img.getWidth() / 2, 0);
        // Group is positioned relative to the stage...
        group.setPosition(WIDTH / 2 - img.getWidth() / 2,
                HEIGHT / 2 - img.getHeight() / 2);
        group.setOrigin(img.getWidth() / 2, img.getHeight() / 2);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        /*if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            group.rotateBy(rotSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            group.rotateBy(-rotSpeed);
        }*/
    }
}