CREATE TABLE devices (
    id VARCHAR(14) PRIMARY KEY,
    smartmeter_id VARCHAR(14) NOT NULL,
    type device_type NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_devices_smartmeters
    FOREIGN KEY (smartmeter_id)
    REFERENCES smartmeters(id)
    ON DELETE CASCADE
);