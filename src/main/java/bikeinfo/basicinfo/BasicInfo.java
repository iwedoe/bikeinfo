package bikeinfo.basicinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicInfo {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getUrlFromBasicInfo(String searchString) {
        if (searchString == null) {
            return null;
        }

        if (data == null) {
            return null;
        }

        Nb nb = data.getNb();

        if (nb == null) {
            return null;
        }

        Feed[] feeds = nb.getFeeds();

        if (feeds == null) {
            return null;
        }

        String searchUrl = null;

        for (Feed feed : feeds) {
            String name = feed.getName();
            if (searchString.equals(name)) {
                searchUrl = feed.getUrl();
            }
        }
        return searchUrl;
    }
}

/*
{
    "data":{
        "nb":{
            "feeds":[
                {
                    "url":"https://gbfs.urbansharing.com/oslobysykkel.no/system_information.json",
                    "name":"system_information"
                },
                {
                    "url":"https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json",
                    "name":"station_information"
                },
                {
                    "url":"https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json",
                    "name":"station_status"
                }
            ]
        }
    },
    "ttl":10,
    "last_updated":1553592630
}
*/
