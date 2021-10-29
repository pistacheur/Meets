package master.project.meets;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.security.Permission;

public class SendSms {
    private String phoneNumber;
    private String msg;

    public SendSms(String phoneNumber, String msg) {
        this.phoneNumber = phoneNumber;
        this.msg = msg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void send(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS},101);
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
                System.out.println("Message was sent ");
            } catch (Exception ex){
                System.out.println("Message cannot be send "+ex.toString());
            }
    }


    }


