package bikeinfo.station.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationStatus {
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

/*
{
  "last_updated": 1540219230,
  "data": {
    "stations": [
      {
        "is_installed": 1,
        "is_renting": 1,
        "num_bikes_available": 7,
        "num_docks_available": 5,
        "last_reported": 1540219230,
        "is_returning": 1,
        "station_id": "175"
      },
      {
        "is_installed": 1,
        "is_renting": 1,
        "num_bikes_available": 4,
        "num_docks_available": 8,
        "last_reported": 1540219230,
        "is_returning": 1,
        "station_id": "47"
      },
      {
        "is_installed": 1,
        "is_renting": 1,
        "num_bikes_available": 4,
        "num_docks_available": 9,
        "last_reported": 1540219230,
        "is_returning": 1,
        "station_id": "10"
      }
    ]
  }
}
 */
