package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class EvolvingTriangullarTillingLauncher extends AIOLauncherFor2Options {
    public EvolvingTriangullarTillingLauncher(){
        AIOLauncherFor2Options.imageNameToBeSaved = "data/ii_triangular_tilling.png";
        AIOLauncherFor2Options.imageNameToBeSaved2 = "data/ii_triangular_tilling_2.png";
        if(!MyGdxGame.optionSelected){MyGdxGame.evolvingTilling = true;}
        //else{MyGdxGame.evolvingTilling = false;}
    }
}