package com.registerService.domain.model;

import com.registerService.domain.exception.device.InvalidDeviceValuesException;
import com.registerService.domain.model.enums.DeviceType;

import java.sql.Timestamp;
import java.util.UUID;

public class Device {

    private String id;

    private DeviceType type;

    private int relayPin;

    private int currentPin;

    private int voltagePin;

    private String name;

    private Smartmeter smartmeter;

    private Timestamp created_at;

    public int getRelayPin() {
        return relayPin;
    }

    public void setRelayPin(int relayPin) {
        this.relayPin = relayPin;
    }

    public int getCurrentPin() {
        return currentPin;
    }

    public void setCurrentPin(int currentPin) {
        this.currentPin = currentPin;
    }

    public int getVoltagePin() {
        return voltagePin;
    }

    public void setVoltagePin(int voltagePin) {
        this.voltagePin = voltagePin;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() < 5 || name.length() > 100){
            throw new InvalidDeviceValuesException("name "+name+" invalid");
        }
        this.name = name;
    }

    public Smartmeter getSmartmeter() {
        return smartmeter;
    }

    public void setSmartmeter(Smartmeter smartmeter) {
        this.smartmeter = smartmeter;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
