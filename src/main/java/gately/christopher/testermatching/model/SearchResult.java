package gately.christopher.testermatching.model;

public class SearchResult {
    private Integer testerId;
    private String testerFirstName;
    private String testerLastName;
    private String country;
    private long experience;

    public SearchResult(Integer testerId, String testerFirstName, String testerLastName, String country, long experience) {
        this.testerId = testerId;
        this.testerFirstName = testerFirstName;
        this.testerLastName = testerLastName;
        this.country = country;
        this.experience = experience;
    }

    public Integer getTesterId() {
        return testerId;
    }

    public void setTesterId(Integer testerId) {
        this.testerId = testerId;
    }

    public String getTesterFirstName() {
        return testerFirstName;
    }

    public void setTesterFirstName(String testerFirstName) {
        this.testerFirstName = testerFirstName;
    }

    public String getTesterLastName() {
        return testerLastName;
    }

    public void setTesterLastName(String testerLastName) {
        this.testerLastName = testerLastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "SearchResults{" +
                "testerId=" + testerId +
                ", testerFirstName='" + testerFirstName + '\'' +
                ", testerLastName='" + testerLastName + '\'' +
                ", experience=" + experience +
                '}';
    }
}
