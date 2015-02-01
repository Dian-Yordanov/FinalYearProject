package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mygdx.game.android.HexagonalTillingLauncher;
import com.mygdx.game.android.SquareTillingLauncher;
import com.mygdx.game.android.R;

public class StartingActivity extends Activity {
    public static Button SquareTillingLauncherButton;
    public static Button HexagonalTillingLauncherButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);
        SquareTillingLauncherButton = (Button) findViewById(R.id.SquareTillingLauncher);
        HexagonalTillingLauncherButton = (Button) findViewById(R.id.HexagonalTillingLauncher);

        //dosomeDesignEnhancements();
        callTheClickListeners();
    }

    public void goToTheClass(String className){
        Log.v("sssss", className);
        className.replaceAll("^.className","");
        Log.v("ssss", className);
        //SquareTillingLauncher.class
        Class<?> c = null;
        if(className != null) {
            try {
                c = Class.forName(className );
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public void dosomeDesignEnhancements(){
        SquareTillingLauncherButton.setMinimumWidth(HexagonalTillingLauncherButton.getWidth());
    }
    public void callTheClickListeners(){
        SquareTillingLauncherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(StartingActivity.this,SquareTillingLauncher.class);
                startActivity(i);
            }
        });
        HexagonalTillingLauncherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(StartingActivity.this,HexagonalTillingLauncher.class);
                startActivity(i);
            }
        });
    }


}
