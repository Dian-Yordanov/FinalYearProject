package com.dian.androidclasses;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Environment;
import android.provider.MediaStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zyan on 01/02/15.
 */
public class LogicalClassForRenderCallingActivites extends AndroidApplication {
    public static void setupPatternStyle(String style){
        MyGdxGame.patternStyle=style;
    }
    public void deleteUsedImage(String selectedFilePath){
        File file = new File(selectedFilePath);
        boolean deleted = file.delete();
        //selectedImagePath ="";
    }
    public static Texture Deprecated__convertBitmapToTexture(Bitmap bitmap){
        Texture tex = new Texture(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureObjectHandle());
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        bitmap.recycle();
        return tex;
    }
    public static void useImage(Texture tex1){
        MyGdxGame.square1Img=tex1;
    }
    public static void useSelectedImage(String selectedImagePath1){
        MyGdxGame.square1Img = new Texture(Gdx.files.absolute(selectedImagePath1));
    }
    public static Texture method2(Bitmap bmp){
        Texture tex=null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        try {
            Pixmap pmap=new Pixmap(byteArray, 0, byteArray.length);
            tex=new Texture(pmap);
            Sprite face=new Sprite(tex);
        } catch(Exception e) {
            Gdx.app.log("KS", e.toString());
            e.printStackTrace();
        }
        return tex;
    }

}
