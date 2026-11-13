const appUser = process.env.MONGODB_APP_USER;
const appPwd = process.env.MONGODB_APP_PASSWORD;
const dataBase = process.env.MONGODB_DATABASE;

if (!appUser || !appPwd) {
    throw new Error("MONGODB_APP_USER and MONGODB_APP_PASSWORD not be null");
}

db = db.getSiblingDB(dataBase);

db.createCollection("devices_measurements_daily_summary");

db.createCollection("devices_measurements", {
    timeseries: {
        timeField: "measured",
        metaField: "metadata",
        granularity: "seconds"
    },
    expireAfterSeconds: 172800
});

db.createCollection("devices");

db.createCollection("smartmeters");

db.createUser({
    user: appUser,
    pwd: appPwd,
    roles: [ { role: "readWrite", db: dataBase } ]
});

db.smartmeters.createIndex({ "user_email": 1 });