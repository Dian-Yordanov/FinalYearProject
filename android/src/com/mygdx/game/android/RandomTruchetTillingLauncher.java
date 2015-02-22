package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/22/2015.
 */
public class RandomTruchetTillingLauncher extends AIOLauncherNoOptions {
    public RandomTruchetTillingLauncher(){
        AIOLauncherNoOptions.imageNameToBeSaved = "data/ii_truchet_tilling.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;

        //MyGdxGame.evolvingTilling = false;
    }
}