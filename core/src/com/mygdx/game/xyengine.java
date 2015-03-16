package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by XelnectMobileUser on 3/14/2015.
 */
public abstract class xyengine {
// For the swarm!!!
//xyengine as it is now can be used for methods but it is not to be used for other types of information


    public static void helloWorldExample(){
        System.out.print("hello world");
    }

    public static void helloWorldExample1(){
        System.out.print("hello world");
    }

    public static void helloWorldExample2(){
        System.out.print("hello world");
    }
    /**
     *javadoc test
     */

public static void helloWorldExample(String stringToBeAppended){
        System.out.print("hello world" + stringToBeAppended);
    }

/*public void log(String stringToBeLogged){
        Log.e("xyengineLogging",stringToBeLogged);
    }
    public void log(int stringToBeLogged){
        Log.e("xyengineLogging","" + stringToBeLogged);
    }
    public void log(String logParameter,String stringToBeLogged){
        Log.e(logParameter,stringToBeLogged);
    }
    public void log(String logParameter,int stringToBeLogged){
        Log.e(logParameter,"" + stringToBeLogged);
    }*/

public static void logGdx(String stringToBeLogged){
        Gdx.app.error("xyengineLogging",stringToBeLogged);
    }
    public static void logGdx(int stringToBeLogged){
        Gdx.app.error("xyengineLogging","" + stringToBeLogged);
    }
    public static void logGdx(String logParameter,String stringToBeLogged){
        Gdx.app.error(logParameter,stringToBeLogged);
    }
    public static void logGdx(String logParameter,int stringToBeLogged){
        Gdx.app.error(logParameter,"" + stringToBeLogged);
    }
}
