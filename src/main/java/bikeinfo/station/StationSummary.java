package bikeinfo.station;

public class StationSummary {
    private int stationId;
    private String stationName = "";
    private int numberOfBikesAvailable;
    private int numberOfDocksAvailable;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getNumberOfBikesAvailable() {
        return numberOfBikesAvailable;
    }

    public void setNumberOfBikesAvailable(int numberOfBikesAvailable) {
        this.numberOfBikesAvailable = numberOfBikesAvailable;
    }

    public int getNumberOfDocksAvailable() {
        return numberOfDocksAvailable;
    }

    public void setNumberOfDocksAvailable(int numberOfDocksAvailable) {
        this.numberOfDocksAvailable = numberOfDocksAvailable;
    }
}
