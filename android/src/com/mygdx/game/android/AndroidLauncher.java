package com.mygdx.game.android;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AndroidLauncher extends AndroidApplication {
    private static Intent intent;
    private static Bitmap bMap;
    private static final int SELECT_PICTURE = 1;
    public static String selectedImagePath;
    public static String imageNameToBeSaved = "data/initialization_image.png";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        MyGdxGame.pictureAddress = imageNameToBeSaved;

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setContentView(R.layout.activity_main);
        initialize(new MyGdxGame(), config);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                runTheGalleryChoosingMethods();

                return true;
            case R.id.option2:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);
            if (requestCode == SELECT_PICTURE) {
                try {
                    FileInputStream fileis=new FileInputStream(selectedImagePath);
                    BufferedInputStream bufferedstream=new BufferedInputStream(fileis);
                    byte[] bMapArray= new byte[bufferedstream.available()];
                    bufferedstream.read(bMapArray);
                    bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
                    if (fileis != null){
                        fileis.close();
                    }
                    if (bufferedstream != null){
                        bufferedstream.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        useSelectedImage(selectedImagePath);
        saveBitmapToFile(bMap);
        useImage(method2(bMap));
        goToRenderingActivity();
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        } catch(Exception e) {
            Gdx.app.log("KS", e.toString());
            e.printStackTrace();
        }
        return tex;
    }
    public void saveBitmapToFile(Bitmap bmp1) {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        File file = new File(path, imageNameToBeSaved); // the File to save to
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

        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void goToRenderingActivity(){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MyGdxGame(), config);
    }
    public void useSelectedImage(String selectedImagePath1){
        MyGdxGame.square1Img = new Texture(Gdx.files.absolute(selectedImagePath1));
    }
    public void runTheGalleryChoosingMethods(){
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, SELECT_PICTURE);
    }
}
