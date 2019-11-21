package bikeinfo.basicinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nb {
    private Feed[] feeds;

    public Feed[] getFeeds() {
        return feeds;
    }

    public void setFeeds(Feed[] feeds) {
        this.feeds = feeds;
    }
}
