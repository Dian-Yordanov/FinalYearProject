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
        SquareTillingLauncherButton = (Button) findViewById(R.id.SquareTillingLauncher);
        HexagonalTillingLauncherButton = (Button) findViewById(R.id.HexagonalTillingLauncher);
        TriangullarTillingLauncherButton = (Button) findViewById(R.id.TriangullarTillingLauncher);

        callTheClickListeners();
    }

    public void callTheClickListeners(){
        SquareTillingLauncherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LogicalClassForRenderCallingActivites.setupPatternStyle("SquareTillingLauncher");
                MyGdxGame.imageNameToBeSavedMGG="data/ii_square_tilling.png";
                Intent i = new Intent(StartingActivity.this,SquareTillingLauncher.class);
                startActivity(i);
            }
        });
        HexagonalTillingLauncherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LogicalClassForRenderCallingActivites.setupPatternStyle("HexagonalTillingLauncher");
                MyGdxGame.imageNameToBeSavedMGG="data/ii_hexagonal_tilling.png";
                Intent i = new Intent(StartingActivity.this,HexagonalTillingLauncher.class);
                startActivity(i);
            }
        });
        TriangullarTillingLauncherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LogicalClassForRenderCallingActivites.setupPatternStyle("TriangullarTillingLauncher");
                MyGdxGame.imageNameToBeSavedMGG="data/ii_triangular_tilling.png";
                MyGdxGame.imageNameToBeSavedMGG2="data/ii_triangular_tilling_2.png";
                Intent i = new Intent(StartingActivity.this,TriangullarTillingLauncher.class);
                startActivity(i);
            }
        });
    }


}
