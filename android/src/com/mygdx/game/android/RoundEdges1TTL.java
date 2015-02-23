package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/23/2015.
 */
public class RoundEdges1TTL  extends AIOLauncherNoOptions {
    public RoundEdges1TTL(){
        AIOLauncherNoOptions.imageNameToBeSaved = "data/ii_truchet_tilling_round_edges.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;

        //MyGdxGame.evolvingTilling = false;
    }
}