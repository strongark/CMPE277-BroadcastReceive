package com.example.tranpham.broadcastreceive;

import android.os.Handler;
import android.widget.Switch;

/**
 * Created by tranpham on 3/12/17.
 */

public class Utils {

    private static StatusArchiver statusArchiver = StatusArchiver.getInstance();

    public static void turnSwitch(final Switch swc,final Boolean status){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swc.setChecked(status);
                return;
            }
        },750);
    }
}
