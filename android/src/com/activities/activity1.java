package com.activities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.android.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by XelnectPC on 11/15/2014.
 */
public class activity1 extends Activity{
    private static final int SELECT_PICTURE = 1;
    private String  selectedImagePath;
    ImageView image;
    Button button;
    public static boolean finished=false;
    public static Bitmap bMap;
    static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        image = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, SELECT_PICTURE);

            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE)
            {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                try {
                    FileInputStream fileis=new FileInputStream(selectedImagePath);
                    BufferedInputStream bufferedstream=new BufferedInputStream(fileis);
                    byte[] bMapArray= new byte[bufferedstream.available()];
                    bufferedstream.read(bMapArray);
                    bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
                    //Here you can set this /Bitmap image to the button background image

                    if (fileis != null)
                    {
                        fileis.close();
                    }
                    if (bufferedstream != null)
                    {
                        bufferedstream.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        saveBitmapToFile(bMap);
        useImage(method2(bMap));
    }


    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public static Texture convertBitmapToTexture(Bitmap bitmap){
        Texture tex = new Texture(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureObjectHandle());
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        bitmap.recycle();
        return tex;
    }
    public static void useImage(Texture tex1){
        com.mygdx.game.MyGdxGame.square1Img=tex1;

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
            // game.setScreen(new GameScreen(game, batcher, face));
        } catch(Exception e) {
            Gdx.app.log("KS", e.toString());
            e.printStackTrace();
        }
        return tex;
    }
    public void saveBitmapToFile(Bitmap bmp1) {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        File file = new File(path, "data/" + "Square4" + ".png"); // the File to save to
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bmp1.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // do not forget to close the stream

        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}