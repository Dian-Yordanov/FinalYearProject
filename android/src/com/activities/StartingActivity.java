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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;
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

public class StartingActivity extends Activity {
    private static Intent intent;
    private static Bitmap bMap;
    private static final int SELECT_PICTURE = 1;
    private static String selectedImagePath;
    public static Button option1Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);
        option1Button = (Button) findViewById(R.id.chooseOption1);
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View arg0) {

                createTheImageAndDir();


            }
        });
    }
    public void createTheImageAndDir(){
        //Environment.getExternalStorageDirectory().toString();
        File f = new File(AndroidLauncher.imageNameToBeSaved);
        if(f.exists() && !f.isDirectory()) {
            Log.v("fff", "fasfas");
        }
        else{
            runTheGalleryChoosingMethods();
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
        goToTheClass();
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        File file = new File(path, AndroidLauncher.imageNameToBeSaved); // the File to save to
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
    public void goToTheClass(){
        Intent i = new Intent(StartingActivity.this,AndroidLauncher.class);
        startActivity(i);
    }

}
