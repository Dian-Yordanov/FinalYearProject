package com.mygdx.game.android;

import com.mygdx.game.MyGdxGame;

public class SquareTillingLauncher extends AIOLauncher {
   public SquareTillingLauncher(){
       AIOLauncher.imageNameToBeSaved = "data/ii_square_tilling.png";
       MyGdxGame.evolvingTilling = false;
   }
}
