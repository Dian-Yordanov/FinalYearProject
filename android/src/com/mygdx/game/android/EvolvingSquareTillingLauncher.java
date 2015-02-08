package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class EvolvingSquareTillingLauncher extends AIOLauncher {
    public EvolvingSquareTillingLauncher(){
        AIOLauncher.imageNameToBeSaved = "data/ii_square_tilling.png";
        if(!MyGdxGame.optionSelected){MyGdxGame.evolvingTilling = true;}
        //else{MyGdxGame.evolvingTilling = false;}
    }
}
