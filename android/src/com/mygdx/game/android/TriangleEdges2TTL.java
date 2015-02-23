package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

/**
 * Created by XelnectMobileUser on 2/23/2015.
 */
public class TriangleEdges2TTL extends AIOLauncherNoOptions {
    public TriangleEdges2TTL(){
        AIOLauncherNoOptions.imageNameToBeSaved = "data/ii_truchet_tilling_triangle_edges.png";
        MyGdxGame.intToBeReduced1=1;
        MyGdxGame.intToBeReduced2=1;
        MyGdxGame.doneOnce=false;

        //MyGdxGame.evolvingTilling = false;
    }
}