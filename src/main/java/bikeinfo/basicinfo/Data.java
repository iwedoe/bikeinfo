package bikeinfo.basicinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private Nb nb;

    public Nb getNb() {
        return nb;
    }

    public void setNb(Nb nb) {
        this.nb = nb;
    }
}
