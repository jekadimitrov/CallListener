package com.example.evgen.callistener;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Log";
    String phoneNumber = "";
    private static boolean incomingCall = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(),
                PhoneStateListener.LISTEN_CALL_STATE);

    }

  /*  public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);


        return true;
    }*/
    class TeleListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:

                        Toast.makeText(getApplicationContext(), "Режим Ожидания",Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Старт" + phoneNumber);
                        incomingCall = false;
                    break;

                case TelephonyManager.CALL_STATE_RINGING:
                    phoneNumber = TelephonyManager.EXTRA_INCOMING_NUMBER;

                    Toast.makeText(getApplicationContext(), "Входящий вызов" + incomingNumber, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Входящий" + phoneNumber);
                    incomingCall = false;
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:

                    Toast.makeText(getApplicationContext(), "Режим Разговора",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Разговор" + phoneNumber);
                    incomingCall = false;
                    break;

                default:
                    break;
            }
        }

    }
}