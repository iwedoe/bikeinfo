package bikeinfo.station.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationInformation {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Station[] getStations() {
        if (data == null) {
            return null;
        }

        return data.getStations();
    }
}
