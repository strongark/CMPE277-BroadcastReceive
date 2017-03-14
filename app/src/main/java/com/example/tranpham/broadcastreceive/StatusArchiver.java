package com.example.tranpham.broadcastreceive;

/**
 * Created by tranpham on 3/12/17.
 * This class purpose is to stored the status of activity
 */

public class StatusArchiver {
    private static StatusArchiver statusArchiver = new StatusArchiver();
    private Integer temparature =0;
    private Integer humidity = 0;
    private Boolean fan_status=Boolean.FALSE;
    private Boolean sprinkler_status=Boolean.FALSE;

    public static StatusArchiver getInstance()
    {
        return statusArchiver;
    }

    public Integer getTemparature() {
        return temparature;
    }

    public void setTemparature(Integer temparature) {
        this.temparature = temparature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Boolean getFan_status() {
        return fan_status;
    }

    public void setFan_status(Boolean fan_status) {
        this.fan_status = fan_status;
    }

    public Boolean getSprinkler_status() {
        return sprinkler_status;
    }

    public void setSprinkler_status(Boolean sprinkler_status) {
        this.sprinkler_status = sprinkler_status;
    }
}
