package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class EvolvingSquareTillingLauncher extends AIOLauncher {
    public EvolvingSquareTillingLauncher(){
        AIOLauncher.imageNameToBeSaved = "data/ii_square_tilling.png";
        MyGdxGame.evolvingTilling = true;
    }
}
