package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/24/2015.
 */
public class PenroseTillingLauncher extends AIOLauncherNoOptionsPenrose {
    public PenroseTillingLauncher(){
        AIOLauncherNoOptionsPenrose.imageNameToBeSaved = "data/penrose.png";
        //AIOLauncherNoOptionsPenrose.imageNameToBeSaved2 = "data/ps36angle.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;
        // MyGdxGame.arraySpriteX = new Array<Sprite>();
        //MyGdxGame.evolvingTilling = false;
    }
}
