package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/24/2015.
 */
public class PenroseTillingLauncher  extends AIOLauncherNoOptions {
    public PenroseTillingLauncher(){
        AIOLauncherNoOptions.imageNameToBeSaved = "data/ii_truchet_tilling_round_edges_2.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;

        //MyGdxGame.evolvingTilling = false;
    }
}
