package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/2/2015.
 */
public class TriangullarTillingLauncher extends AIOLauncher {

    public TriangullarTillingLauncher() {
        AIOLauncher.imageNameToBeSaved = "data/ii_triangular_tilling.png";
        //AIOLauncherFor2Options.imageNameToBeSaved2 = "data/ii_triangular_tilling_2.png";
        MyGdxGame.evolvingTilling = false;
    }
}