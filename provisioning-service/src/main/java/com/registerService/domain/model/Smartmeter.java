package com.registerService.domain.model;

import com.registerService.domain.exception.smartmeter.InvalidSmartmeterValuesException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class Smartmeter {

    private String id;

    private String name;

    private String macAddress;

    private List<Device> devices;

    private User user;

    private Timestamp created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() < 5 || name.length() > 100){
            throw new InvalidSmartmeterValuesException("name "+name+" invalid");
        }
        this.name = name;
        return;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        if(macAddress.isBlank() || macAddress.length() < 12 || macAddress.length() > 17){
            throw new InvalidSmartmeterValuesException("macAddress "+macAddress+" invalid");
        }
        this.macAddress = macAddress;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
