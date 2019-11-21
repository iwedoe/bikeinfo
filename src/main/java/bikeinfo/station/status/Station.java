package bikeinfo.station.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    @JsonProperty("station_id")
    private int stationId;

    @JsonProperty("num_bikes_available")
    private int numberOfBikesAvailable;

    @JsonProperty("num_docks_available")
    private int numberOfDocksAvailable;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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
