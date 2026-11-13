package energy_api.energy_iot_service.domain.model;

import energy_api.energy_iot_service.domain.enums.DeviceState;

import java.time.Instant;

public class DeviceMeasurement {

    private String deviceId;

    private String smartmeterId;

    private double voltageV;

    private double currentA;

    private double powerW;

    private DeviceState state;

    private Instant measured;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSmartmeterId() {
        return smartmeterId;
    }

    public void setSmartmeterId(String smartmeterId) {
        this.smartmeterId = smartmeterId;
    }

    public double getVoltageV() {
        return voltageV;
    }

    public void setVoltageV(double voltageV) {
        this.voltageV = voltageV;
    }

    public double getCurrentA() {
        return currentA;
    }

    public void setCurrentA(double currentA) {
        this.currentA = currentA;
    }

    public double getPowerW() {
        return powerW;
    }

    public void setPowerW(double powerW) {
        this.powerW = powerW;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public Instant getMeasured() {
        return measured;
    }

    public void setMeasured(Instant measured) {
        this.measured = measured;
    }
}
