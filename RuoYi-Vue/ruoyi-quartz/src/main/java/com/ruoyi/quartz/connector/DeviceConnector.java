package com.ruoyi.quartz.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;

import java.util.ArrayList;

public interface DeviceConnector {
    /**
     * query device info by device_id
     * @param deviceId
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}")
    Object getById(@Path("device_id") String deviceId);

    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean commands(@Path("device_id") String deviceId, @Body Object commands);

    @GET("/v1.0/iot-03/devices/{device_id}/status")
    ArrayList<Object> getDeviceStatus (@Path("device_id") String deviceId);
}
