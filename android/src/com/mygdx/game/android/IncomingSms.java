package com.mygdx.game.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.dian.androidclasses.downloadPicture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by XelnectMobileUser on 3/10/2015.
 */
public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    SmsMessage currentMessage;
    String phoneNumber;
    String senderNum;
    static String message;
    public static ArrayList<String> smsMessage;
    public String pastSMSText;

    public void onReceive(Context context, Intent intent) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            String msgBody = "";
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                       /* msgBody.add(msgs[i].getMessageBody());*/
                        Log.v("ww", Integer.toString(msgs.length));
                        msgBody=msgBody+msgs[i].getMessageBody();
                    }
                   new LongOperation(){
                       @Override public void onPostExecute(String result)
                       {
                           try {
                               doStuffWithIncomingSMS(result);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   }.execute(msgBody);

                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }

        // Retrieves a map of extended data from the intent.
        /*final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (Object aPdusObj : pdusObj) {
                    currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                    phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    smsMessage.add(message);

                   *//* MainActivity.updateView(MainActivity.timeView, "Message recieved on: " + IncomingSms.getCurrentTime());
                    MainActivity.updateView(MainActivity.smsView, IncomingSms.message);*//*

                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }*/
    public void doStuffWithIncomingSMS(String msgBody1) throws IOException {
            new downloadPicture(msgBody1);
    }
    public String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        //.out.println( sdf.format(cal.getTime()) );
        return hour + ":" + minute + ":" + second;
    }


    private class LongOperation extends AsyncTask<String, Void ,String> {

        @Override
        protected String doInBackground(String... params) {
          /*  for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";*/

            Log.e("",params[0]);
            String stringTobeDecompressed = params[0].replaceFirst("Sent from your Twilio trial account - ","");
            //String stringTobeDecompressed1 = stringTobeDecompressed.replaceFirst(" ","");
            Log.e("StringToBeDecompressed:","" + stringTobeDecompressed);
            //String stringBeingDecompressed = LZString.decompress(stringTobeDecompressed);

            /*String decompressedMessage = null;
            try {
                decompressedMessage = LZString.decompressFromBase64(stringTobeDecompressed);

            Log.e("","" + decompressedMessage);*/
            pastSMSText = stringTobeDecompressed;
            /*Log.e("","" + pastSMSText);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
           /* try {
                String decompressedMessage = LZString.decompressFromUTF16(stringTobeDecompressed1);
                Log.e("","" + decompressedMessage);
                pastSMSText = decompressedMessage;
                Log.e("","" + pastSMSText);
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);

            }*/

            //pastSMSText = LZString.decompress(pastSMSText);

            //doStuffWithIncomingSMS(params[0]);
           // doStuffWithIncomingSMS(pastSMSText);
            return pastSMSText;
        }


        protected String onPostExecute(String... result) {
           // MainActivityTextView.setText(pastSMSText);
           //  return null;// txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        return pastSMSText;
        }
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {
           // doStuffWithIncomingSMS(values.toString());
        }


    }
    //test change
/*    public static String getMessage(){
        return smsMessage;
    }
    private static void setMessage(String message){
        smsMessage = message;
    }*/
}