package com.dian.androidclasses;

import android.content.Intent;

import com.activities.StartingActivity;
import com.mygdx.game.android.R;

import java.io.*;
import java.net.URL;

/**
 * Created by zyan on 06/04/15.
 */
public class downloadPicture extends StartingActivity{
    public downloadPicture(String message) throws IOException, NullPointerException{
        URL url = new URL(message);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("/sdcard/data/penrose.png");
        fos.write(response);
        fos.close();
        //commit
        restartTheActivity();
    }
    public void restartTheActivity() throws NullPointerException{
        Intent intent = new Intent("com.mygdx.game.android.PenroseTillingLauncher");
        startActivity(intent);
    }
}
