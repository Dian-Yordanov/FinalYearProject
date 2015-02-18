package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/17/2015.
 */
public class TruchetTillingLauncher extends AIOLauncherNoOptions {
    public TruchetTillingLauncher(){
        AIOLauncherNoOptions.imageNameToBeSaved = "data/ii_truchet_tilling.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;
        //MyGdxGame.evolvingTilling = false;
    }
}