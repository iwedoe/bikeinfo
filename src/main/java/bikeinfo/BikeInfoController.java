package bikeinfo;

import bikeinfo.basicinfo.BasicInfo;
import bikeinfo.station.StationSummary;
import bikeinfo.station.information.Station;
import bikeinfo.station.information.StationInformation;
import bikeinfo.station.status.StationStatus;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class BikeInfoController {

    @GetMapping("/bikeinfo")
    public String getBikeInfo(Model model) {
        String basicInfoUrl = "https://gbfs.urbansharing.com/oslobysykkel.no/gbfs.json";
        ResponseEntity<BasicInfo> basicInfoResponse = getResponseEntityFromUrl(BasicInfo.class, basicInfoUrl);

        if (basicInfoResponse == null || basicInfoResponse.getStatusCode() != HttpStatus.OK || basicInfoResponse.getBody() == null) {
            addErrorMessage(model);
            return "bikeinfo";
        }

        BasicInfo basicInfo = basicInfoResponse.getBody();

        String stationInformationUrl = basicInfo.getUrlFromBasicInfo("station_information");
        ResponseEntity<StationInformation> stationInformationResponse = getResponseEntityFromUrl(StationInformation.class, stationInformationUrl);

        if (stationInformationResponse == null || stationInformationResponse.getStatusCode() != HttpStatus.OK || stationInformationResponse.getBody() == null) {
            addErrorMessage(model);
            return "bikeinfo";
        }

        String stationStatusUrl = basicInfo.getUrlFromBasicInfo("station_status");
        ResponseEntity<StationStatus> stationStatusResponse = getResponseEntityFromUrl(StationStatus.class, stationStatusUrl);

        if (stationStatusResponse == null || stationStatusResponse.getStatusCode() != HttpStatus.OK || stationStatusResponse.getBody() == null) {
            addErrorMessage(model);
            return "bikeinfo";
        }

        Map<Integer, StationSummary> namesAndStatusFromStations = getNamesAndStatusFromStations(model, stationInformationResponse.getBody(), stationStatusResponse.getBody());

        if (namesAndStatusFromStations == null) {
            addErrorMessage(model);
            return "bikeinfo";
        }

        List<StationSummary> stationList = new ArrayList<>(namesAndStatusFromStations.values());
        stationList.sort(Comparator.comparing(StationSummary::getStationName));
        model.addAttribute("stations", stationList);
        return "bikeinfo";
    }

    private Map<Integer, StationSummary> getNamesAndStatusFromStations(Model model, StationInformation stationInformation, StationStatus stationStatus) {
        Map<Integer, StationSummary> stationMap = new HashMap<>();

        if (stationInformation.getStations() == null) {
            return null;
        }

        getNamesFromStationInformation(stationMap, stationInformation.getStations());

        if (stationStatus.getStations() == null) {
            return null;
        }

        getStatusFromStationStatus(stationMap, stationStatus.getStations());

        return stationMap;
    }

    private void getStatusFromStationStatus(Map<Integer, StationSummary> stationMap, bikeinfo.station.status.Station[] stationsFromStationStatus) {
        for (bikeinfo.station.status.Station station : stationsFromStationStatus) {
            int stationId = station.getStationId();
            int numberOfBikesAvailable = station.getNumberOfBikesAvailable();
            int numberOfDocksAvailable = station.getNumberOfDocksAvailable();

            StationSummary stationSummary = stationMap.get(stationId);

            if (stationSummary == null) {
                stationSummary = new StationSummary();
                stationSummary.setStationId(stationId);
                stationMap.put(stationId, stationSummary);
            }

            stationSummary.setNumberOfBikesAvailable(numberOfBikesAvailable);
            stationSummary.setNumberOfDocksAvailable(numberOfDocksAvailable);
        }
    }

    private void getNamesFromStationInformation(Map<Integer, StationSummary> stationMap, Station[] stationsFromStationInformation) {
        for (Station station : stationsFromStationInformation) {
            int stationId = station.getStationId();
            String name = station.getName();

            StationSummary stationSummary = stationMap.get(stationId);

            if (stationSummary == null) {
                stationSummary = new StationSummary();
                stationSummary.setStationId(stationId);
                stationMap.put(stationId, stationSummary);
            }

            stationSummary.setStationName(name);
        }
    }

    private void addErrorMessage(Model model) {
        model.addAttribute("errorMessage", "Kunne ikke hente informasjon fra ekstern tjeneste. Prøv igjen senere.");
    }

    private <T> ResponseEntity<T> getResponseEntityFromUrl(Class<T> clazz, String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-Identifier", "example-bikeinfo");
        HttpEntity<String> request = new HttpEntity<>("body", headers);

        ResponseEntity<T> entity = null;
        try {
            entity = restTemplate.exchange(url, HttpMethod.GET, request, clazz);
        } catch (RestClientException e) {
            return null;
        }

        return entity;
    }
}
