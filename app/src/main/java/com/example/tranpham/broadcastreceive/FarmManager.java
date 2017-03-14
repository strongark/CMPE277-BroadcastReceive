package com.example.tranpham.broadcastreceive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FarmManager extends Activity {

    final static String TURN_FAN_ACTION_STRING ="com.example.tranpham.broadcastreceive.fan";
    final static String TURN_SPRINKLER_ACTION_STRING ="com.example.tranpham.broadcastreceive.sprinkler";
    private StatusArchiver statusArchiver = StatusArchiver.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_manager);
        updateStatus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

    private void updateStatus() {
        ((TextView)findViewById(R.id.txt_humidity)).setText(statusArchiver.getHumidity().toString());
        ((TextView)findViewById(R.id.txt_temparature)).setText(statusArchiver.getTemparature().toString());
        ((ToggleButton)findViewById(R.id.tbt_fan)).setChecked(statusArchiver.getFan_status());
        ((ToggleButton)findViewById(R.id.tbt_Sprinkler)).setChecked(statusArchiver.getSprinkler_status());
    }

    public static class ReceiveFarmManager extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Integer temparature = intent.getIntExtra("temparature",0);
            Integer humidity = intent.getIntExtra("humidity",0);
            StatusArchiver.getInstance().setHumidity(humidity);
            StatusArchiver.getInstance().setTemparature(temparature);
            //Run through some logic here to determine when to turn on or off the fan and sprinkler
            Intent fanBroadcastIntent = new Intent(TURN_FAN_ACTION_STRING);

            if(temparature>90){
                fanBroadcastIntent.putExtra("status",Boolean.TRUE);
            }
            else
            {
                fanBroadcastIntent.putExtra("status",Boolean.FALSE);
            }

            LocalBroadcastManager.getInstance(context).sendBroadcast(fanBroadcastIntent);

            Intent sprinklerBroadcastIntent = new Intent(TURN_SPRINKLER_ACTION_STRING);

            if(humidity<25){
                sprinklerBroadcastIntent.putExtra("status",Boolean.TRUE);
            }
            else
            {
                sprinklerBroadcastIntent.putExtra("status",Boolean.FALSE);
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(sprinklerBroadcastIntent);
        }
    }

    //manually turn fan
    public void onTurnFan(View view){
        Boolean fan_status = StatusArchiver.getInstance().getFan_status();
        StatusArchiver.getInstance().setFan_status(!fan_status);
        Intent fanBroadcastIntent = new Intent(TURN_FAN_ACTION_STRING);
        fanBroadcastIntent.putExtra("status",!fan_status);
        sendBroadcast(fanBroadcastIntent);

    }

    //manually turn sprinkler
    public void onTurnSprinkler(View view){
        Boolean sprinkler_status = StatusArchiver.getInstance().getSprinkler_status();
        StatusArchiver.getInstance().setSprinkler_status(!sprinkler_status);
        Intent sprinklerBroadcastIntent = new Intent(TURN_SPRINKLER_ACTION_STRING);
        sprinklerBroadcastIntent.putExtra("status",!sprinkler_status);
        sendBroadcast(sprinklerBroadcastIntent);

    }

}
