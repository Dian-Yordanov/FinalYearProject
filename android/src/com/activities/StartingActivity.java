package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mygdx.game.android.AndroidLauncher;
import com.mygdx.game.android.R;

public class StartingActivity extends Activity {
    public static Button option1Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);
        option1Button = (Button) findViewById(R.id.chooseOption1);
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View arg0) {
                Intent i = new Intent(StartingActivity.this,AndroidLauncher.class);
                startActivity(i);
            }
        });
    }

}
