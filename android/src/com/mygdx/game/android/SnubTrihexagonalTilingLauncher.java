package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/12/2015.
 */
public class SnubTrihexagonalTilingLauncher extends AIOLauncherFor2Options {

    public SnubTrihexagonalTilingLauncher() {
        AIOLauncherFor2Options.imageNameToBeSaved = "data/ii_triangular_tilling.png";
        AIOLauncherFor2Options.imageNameToBeSaved2 = "data/ii_triangular_tilling_2.png";
        MyGdxGame.evolvingTilling = false;
    }
}