package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dian.androidclasses.LogicalClassForRenderCallingActivites;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.android.HexagonalTillingLauncher;
import com.mygdx.game.android.SquareTillingLauncher;
import com.mygdx.game.android.R;
import com.mygdx.game.android.TriangullarTillingLauncher;

public class StartingActivity extends Activity {
    public static Button SquareTillingLauncherButton;
    public static Button HexagonalTillingLauncherButton;
    public static Button TriangullarTillingLauncherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);

        callClassSquareTillingLauncher(SquareTillingLauncherButton, R.id.squareTillingLauncher
                ,"SquareTillingLauncher", "com.mygdx.game.android.SquareTillingLauncher"
                ,"data/ii_square_tilling.png");
        callClassSquareTillingLauncher(HexagonalTillingLauncherButton, R.id.hexagonalTillingLauncher
                ,"HexagonalTillingLauncher", "com.mygdx.game.android.HexagonalTillingLauncher"
                ,"data/ii_hexagonal_tilling.png");
        callClassSquareTillingLauncher(TriangullarTillingLauncherButton, R.id.triangullarTillingLauncher
                ,"TriangullarTillingLauncher", "com.mygdx.game.android.TriangullarTillingLauncher"
                ,"data/ii_triangular_tilling.png","data/ii_triangular_tilling_2.png");

    }

    public void callClassSquareTillingLauncher(Button buttonTobeUsed, int RidObjectToBeCalled,
                                                final String nameOfThisLauncher, final String nameOfclassToLaunch
            ,final String image1Name ){
        //TriangullarTillingLauncher = (Button) findViewById(R.id.TriangullarTillingLauncher);
        buttonTobeUsed = (Button) findViewById(RidObjectToBeCalled);
        buttonTobeUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LogicalClassForRenderCallingActivites.setupPatternStyle(nameOfThisLauncher);
                MyGdxGame.imageNameToBeSavedMGG=image1Name;
                try {
                    Class<?> act = Class.forName(nameOfclassToLaunch);
                    Intent i = new Intent(StartingActivity.this,act);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void callClassSquareTillingLauncher(Button buttonTobeUsed, int RidObjectToBeCalled,
                                               final String nameOfThisLauncher, final String nameOfclassToLaunch
            ,final String image1Name,final String image2Name ){
        //TriangullarTillingLauncher = (Button) findViewById(R.id.TriangullarTillingLauncher);
        buttonTobeUsed = (Button) findViewById(RidObjectToBeCalled);
        buttonTobeUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LogicalClassForRenderCallingActivites.setupPatternStyle(nameOfThisLauncher);
                MyGdxGame.imageNameToBeSavedMGG=image1Name;
                MyGdxGame.imageNameToBeSavedMGG2=image2Name;
                try {
                    Class<?> act = Class.forName(nameOfclassToLaunch);
                    Intent i = new Intent(StartingActivity.this,act);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
