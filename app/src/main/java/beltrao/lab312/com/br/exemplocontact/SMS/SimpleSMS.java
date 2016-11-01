package beltrao.lab312.com.br.exemplocontact.SMS;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;

import beltrao.lab312.com.br.exemplocontact.Receiver.SMSReceiver;

/**
 * Created by Rudda Beltrao on 13/10/2016.
 */
public class SimpleSMS {
    private  SmsManager smsManager;
    private PendingIntent peddingIntent;
    private Context com;
    private static SimpleSMS simpleSMS;

    public static SimpleSMS build(Context com){

        if(simpleSMS ==null){

            return simpleSMS = new SimpleSMS(com);

        }

        return simpleSMS;


    }

    public  SimpleSMS (Context com){

        this.com = com;
       // this.peddingIntent = PendingIntent.getActivity(this.com, 0,new Intent(this.com, cls ), 0);

    }


    public boolean sendSMS(String destinationAddress, String text){

        if (isPhoneNumber(destinationAddress)){

            smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(destinationAddress, null, text, null, null);
            return true;
        }
            return false;

    }

    private boolean isPhoneNumber(String phone){

        return PhoneNumberUtils.isWellFormedSmsAddress(phone);

    }






}
