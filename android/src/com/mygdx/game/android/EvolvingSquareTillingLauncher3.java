package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class EvolvingSquareTillingLauncher3 extends AIOLauncher {
    public EvolvingSquareTillingLauncher3(){
        AIOLauncher.imageNameToBeSaved = "data/ii_square_tilling.png";
        if(!MyGdxGame.optionSelected){MyGdxGame.evolvingTilling = true;}
        //else{MyGdxGame.evolvingTilling = false;}
    }
}
