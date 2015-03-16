package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/24/2015.
 */
public class PenroseTillingLauncher extends AIOLauncherNoOptionsPenrose {
    public PenroseTillingLauncher(){
        AIOLauncherNoOptionsPenrose.imageNameToBeSaved = "data/ii_truchet_tilling.png";
        AIOLauncherNoOptionsPenrose.imageNameToBeSaved2 = "data/ii_truchet_tilling.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;
        // MyGdxGame.arraySpriteX = new Array<Sprite>();
        //MyGdxGame.evolvingTilling = false;
    }
}
