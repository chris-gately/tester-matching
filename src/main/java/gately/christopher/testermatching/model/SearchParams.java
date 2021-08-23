package gately.christopher.testermatching.model;

import java.util.List;

public class SearchParams {
    private List<Integer> deviceIds;
    private List<String> countries;

    public List<Integer> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Integer> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "devices=" + deviceIds +
                ", countries=" + countries +
                '}';
    }
}
