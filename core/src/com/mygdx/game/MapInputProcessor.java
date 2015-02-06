package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MapInputProcessor implements InputProcessor {
    static Vector3 last_touch_down = new Vector3();

    public boolean touchDragged(int x, int y, int pointer,OrthographicCamera camera) {
        moveCamera( x, y, camera);
        return false;
    }

    public static void moveCamera(int touch_x, int touch_y, OrthographicCamera camera) {
        Vector3 new_position = getNewCameraPosition( touch_x, touch_y,camera);

       // if( !cameraOutOfLimit( new_position ) )
            camera.translate( new_position.sub( camera.position ) );

        last_touch_down.set( touch_x, touch_y, 0);
    }

    public static Vector3 getNewCameraPosition(int x, int y, OrthographicCamera camera) {
        Vector3 new_position = last_touch_down;
        new_position.sub(x, y, 0);
        new_position.y = -new_position.y;
        new_position.add( camera.position );

        return new_position;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
/*
    private boolean cameraOutOfLimit( Vector3 position ) {
        int x_left_limit = WINDOW_WIDHT / 2;
        int x_right_limit = terrain.getWidth() - WINDOW_WIDTH / 2;
        int y_bottom_limit = WINDOW_HEIGHT / 2;
        int y_top_limit = terrain.getHeight() - WINDOW_HEIGHT / 2;

        if( position.x < x_left_limit || position.x > x_right_limit )
            return true;
        else if( position.y < y_bottom_limit || position.y > y_top_limit )
            return true;
        else
            return false;
    }
*/

}