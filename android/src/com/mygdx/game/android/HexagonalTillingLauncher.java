package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class HexagonalTillingLauncher  extends AIOLauncher {
    public HexagonalTillingLauncher() {
        AIOLauncher.imageNameToBeSaved = "data/ii_hexagonal_tilling.png";
        MyGdxGame.evolvingTilling = false;
    }
}
