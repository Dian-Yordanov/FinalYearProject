package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by XelnectMobileUser on 3/18/2015.
 */
public class PenroseRenderingActivity extends ApplicationAdapter {

    private static OrthographicCamera camera;
    int cameraBoundaryX1 = -1600, cameraBoundaryX2 = 3200, cameraBoundaryY1 = 8000, cameraBoundaryY2 = 0;
    Vector2 dragOld, dragNew;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    private Stage stage;
    private Group group;

    float cameraZoomLevel = 0;
    public static int sumaryXi = 0;

    private float rotSpeed = 5;

    @Override
    public void create() {


        // Create a stage
        createCamera();

        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        stage.getViewport().setCamera(camera);
        //stage.setProjectionMatrix(camera.combined);
        // Create a group and add it to the stage.
        group = new Group();
        stage.addActor(group);


        // Create images and add them to the group.
        final Texture region = new Texture(Gdx.files.internal("data/108pstriangle.png"));
        final Texture region2 = new Texture(Gdx.files.internal("data/ps36angle.png"));
        Image img = new Image(region);
        Image img2 = new Image(region);
        Image img3 = new Image(region);

        img2.setColor(new Color(1, 1, 0, 1));
        img3.setColor(new Color(0, 0, 1, 1));


        group.addActor(img2);
        group.addActor(img3);
        group.addActor(img);

        // Images are positioned relative to the group...

        img.setPosition(2000, 2000);
        img2.setPosition(2200,2200);
        img3.setPosition(2400,2400);
        // Group is positioned relative to the stage...
        group.setPosition(WIDTH / 2 - img.getWidth() / 2,
                HEIGHT / 2 - img.getHeight() / 2);
        group.setOrigin(img.getWidth() / 2, img.getHeight() / 2);

        Container wrapper = new Container(group);
        wrapper.setTransform(true);
        wrapper.setOrigin(wrapper.getPrefWidth() / 2, wrapper.getPrefHeight() / 2);
        wrapper.setRotation(45);
        wrapper.setScaleX(1.5f);
        stage.addActor(wrapper);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraMovingMethod();
        zoomableCamera();
        //stage.act(Gdx.graphics.getDeltaTime());

        //group.rotateBy(45);
        //group.setRotation(45);
        stage.draw();

        /*if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            group.rotateBy(rotSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            group.rotateBy(-rotSpeed);
        }*/
    }

    public void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(1.5f * camera.viewportWidth, 1.5f * camera.viewportHeight, 0);
        camera.update();

        //from x1 to x2 and from y1 to y2 was 1 2 2 1
        cameraBoundaryX1 = Gdx.graphics.getWidth() / 2;
        cameraBoundaryX2 = (int) (3f * Gdx.graphics.getWidth());
        cameraBoundaryY1 = (int) (3f * Gdx.graphics.getHeight());
        cameraBoundaryY2 = Gdx.graphics.getHeight() / 2;

        camera.update();

    }

    public void cameraMovingMethod() {
        if (Gdx.input.justTouched()) {
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }
        if (Gdx.input.isTouched()) {
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)) {
                //region cameraOnTouchModifications
                Gdx.app.log("somelog1122", " " + dragOld.x + " " + dragNew.x + " ");
                float x11 = dragOld.x - dragNew.x;
                float y11 = dragOld.y - dragNew.y;
                //region cameraBoundaries
                if (camera.position.x >= cameraBoundaryX1 && camera.position.y <= cameraBoundaryY1 &&
                        camera.position.x <= cameraBoundaryX2 && camera.position.y >= cameraBoundaryY2) {//&& camera.position.x < 3200 && camera.position.y > 0
                    camera.translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
                    camera.update();
                } else {
                    if (camera.position.x < cameraBoundaryX1) {
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
                    if (camera.position.y < cameraBoundaryY2) {
                        if (y11 < 0) {//&& camera.position.x < -1600
                            camera.translate(0, dragNew.y - dragOld.y);
                            camera.update();
                        }
                    }
                }
                dragOld = dragNew;
                //endregion
            }
        }
    }

    public void zoomableCamera() {
        for (int i = 0; i < 20; i++) { // 20 is max number of touch points
            if (Gdx.input.isTouched(i) && i == 1) {
                int sumaryXii = Math.abs(Gdx.input.getX(i) - Gdx.input.getX(i - 1));
                Gdx.app.log("sss", "" + cameraZoomLevel);

                if (sumaryXi >= sumaryXii && i != 2) {
                    camera.zoom += 0.05;
                    cameraZoomLevel += 0.05;
                }
                if (cameraZoomLevel > -0.40) {
                    if (sumaryXi < sumaryXii && i != 2) {
                        camera.zoom -= 0.05;
                        cameraZoomLevel -= 0.05;
                    }
                }
                sumaryXi = sumaryXii;
            }
        }
    }
}