package energy_api.energy_iot_service.domain.model;

import org.bson.types.ObjectId;

import java.time.Instant;

public class DeviceMeasurementDailySummary {

    private String deviceId;

    private String smartmeterId;

    private double totalEnergyKWH;

    private double averageCurrentA;

    private double averagePowerW;

    private int sampleCount;

    private Instant date;

    public DeviceMeasurementDailySummary(String deviceId, String smartmeterId, double totalEnergyKWH, double averageCurrentA, double averagePowerW, int sampleCount, Instant date) {
        this.deviceId = deviceId;
        this.smartmeterId = smartmeterId;
        this.totalEnergyKWH = totalEnergyKWH;
        this.averageCurrentA = averageCurrentA;
        this.averagePowerW = averagePowerW;
        this.sampleCount = sampleCount;
        this.date = date;
    }

    public DeviceMeasurementDailySummary() {
    }

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

    public double getTotalEnergyKWH() {
        return totalEnergyKWH;
    }

    public void setTotalEnergyKWH(double totalEnergyKWH) {
        this.totalEnergyKWH = totalEnergyKWH;
    }

    public double getAverageCurrentA() {
        return averageCurrentA;
    }

    public void setAverageCurrentA(double averageCurrentA) {
        this.averageCurrentA = averageCurrentA;
    }

    public double getAveragePowerW() {
        return averagePowerW;
    }

    public void setAveragePowerW(double averagePowerW) {
        this.averagePowerW = averagePowerW;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
