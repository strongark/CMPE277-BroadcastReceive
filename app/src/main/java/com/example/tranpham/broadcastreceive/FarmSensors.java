package com.example.tranpham.broadcastreceive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FarmSensors extends Activity {

    final String SENSOR_ACTION_STRING = "com.example.tranpham.broadcastreceive.sensor";
    StatusArchiver statusArchiver = StatusArchiver.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_sensors);
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

    public void broadcastSensor(View view)
    {
        Integer temparature = Integer.parseInt(((EditText)findViewById(R.id.edt_temp)).getText().toString());
        Integer humidity = Integer.parseInt(((EditText)findViewById(R.id.edt_humidity)).getText().toString());
        statusArchiver.setHumidity(humidity);
        statusArchiver.setTemparature(temparature);
        Intent sensorBroadcastIntent = new Intent(SENSOR_ACTION_STRING);
        sensorBroadcastIntent.putExtra("temparature",temparature);
        sensorBroadcastIntent.putExtra("humidity",humidity);
        sendBroadcast(sensorBroadcastIntent);
    }

    private void updateStatus() {
        ((EditText)findViewById(R.id.edt_humidity)).setText(statusArchiver.getHumidity().toString());
        ((EditText)findViewById(R.id.edt_temp)).setText(statusArchiver.getTemparature().toString());
    }
}
