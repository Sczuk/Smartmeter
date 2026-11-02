package energy_api.energy_iot_service.domain.model;

public class Smartmeter {

    private String id;

    private String userId;

    private String macAddress;

    private String name;

    private boolean active;

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive(){
        return active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
