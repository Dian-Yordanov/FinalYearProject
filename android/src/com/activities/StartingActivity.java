package com.activities;

import android.app.Activity;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.mygdx.game.android.AndroidLauncher;
import com.mygdx.game.android.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 1/27/2015.
 */

public class StartingActivity extends Activity {
    public static Button choosePicture;
    static Intent intent;
    private static final int SELECT_PICTURE = 1;
    public static String  selectedImagePath;
    public static Bitmap bMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);
        GdxNativesLoader.load();

        choosePicture = (Button) findViewById(R.id.choosePictureButton);
        choosePicture.setOnClickListener(new View.OnClickListener() {
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
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);
            if (requestCode == SELECT_PICTURE) {


                //useSelectedImage(selectedImagePath);
Log.v("fff","FFF"+selectedImagePath);
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

                //method2(bMap);
                //useImage(convertBitmapToTexture(bMap));
                //useSelectedImage(selectedImagePath);
                //saveBitmapToFile(bMap);
                useSelectedImage(selectedImagePath);
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
    public static Texture convertBitmapToTexture(Bitmap bitmap){
        Texture tex = new Texture(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureObjectHandle());
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        //region = new TextureRegion(texture, 20, 20, 50, 50);
        //sprite = new Sprite(texture, 20, 20, 50, 50);
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
    public void goToRenderingActivity(){
        Intent i = new Intent(StartingActivity.this,
                AndroidLauncher.class);
        startActivity(i);
    }
    public void useSelectedImage(String selectedImagePath1){
        /*
        MyGdxGame.manager = new AssetManager(new ExternalFileHandleResolver());
        String pictureAddress = selectedImagePath1;

        Log.v("ff", "ff" + selectedImagePath1);
        //tex =
        MyGdxGame.manager.load(pictureAddress, Texture.class);
        MyGdxGame.manager.finishLoading();
        //com.mygdx.game.MyGdxGame.square1Img = new Texture(Gdx.files.absolute(pictureAddress));
        MyGdxGame.manager.get(pictureAddress, Texture.class);
*/
    }
}
