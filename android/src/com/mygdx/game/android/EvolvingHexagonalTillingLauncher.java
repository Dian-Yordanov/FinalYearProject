package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/9/2015.
 */
public class EvolvingHexagonalTillingLauncher  extends AIOLauncher {
    public EvolvingHexagonalTillingLauncher() {
        AIOLauncher.imageNameToBeSaved = "data/ii_hexagonal_tilling.png";
        if (!MyGdxGame.optionSelected) {
            MyGdxGame.evolvingTilling = true;
        }
        //else{MyGdxGame.evolvingTilling = false;}
    }
}