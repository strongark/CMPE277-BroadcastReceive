package com.example.tranpham.broadcastreceive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class FarmActuator extends Activity {

    StatusArchiver statusArchiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_actuator);

        statusArchiver = StatusArchiver.getInstance();
        updateActuator();
    }

    private void updateActuator() {
        Switch swcFan= (Switch)findViewById(R.id.swc_fan);
        Switch swcSprinkler=(Switch)findViewById(R.id.swc_Sprinkler);
        Utils.turnSwitch(swcFan,statusArchiver.getFan_status());
        Utils.turnSwitch(swcSprinkler,statusArchiver.getSprinkler_status());
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateActuator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateActuator();
    }

    public static class ReceiveActuator extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentString = intent.getClass().toString();
            Boolean status = intent.getBooleanExtra("status",Boolean.TRUE);
            switch (intentString){
                case "com.example.tranpham.broadcastreceive.fan":
                    StatusArchiver.getInstance().setFan_status(status);
                    break;
                case "com.example.tranpham.broadcastreceive.sprinkler":
                    StatusArchiver.getInstance().setSprinkler_status(status);
                    break;
            }
        }
    }

    public void turnFan(){
        Toast.makeText(this,"Turn Fan",Toast.LENGTH_LONG).show();
    }

    public void turnSprinkler(){
        Toast.makeText(this,"Turn Sprinkler",Toast.LENGTH_LONG).show();
    }
}
