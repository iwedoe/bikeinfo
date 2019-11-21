package bikeinfo.station.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private Station[] stations;

    public Station[] getStations() {
        return stations;
    }

    public void setStations(Station[] stations) {
        this.stations = stations;
    }
}
