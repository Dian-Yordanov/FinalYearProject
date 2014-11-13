package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
    Texture square1Img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
        square1Img = new Texture("Square1.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        for(int i=0;i<100;i++) {
            for (int ii = 0; ii < 100; ii++) {
                batch.draw(square1Img, (square1Img.getWidth() + 5 )* i,  (square1Img.getHeight() +5)* ii);
            }

        }
//hello laptop
        batch.end();
	}
}
