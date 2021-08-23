package gately.christopher.testermatching.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Device {

    @Id
    private Integer id;
    private String description;

    @ManyToMany(mappedBy = "devices")
    private Set<Tester> testers = new HashSet<>();

    @OneToMany(mappedBy = "device")
    private Set<Bug> bugs = new HashSet<>();

    public Device() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tester> getTesters() {
        return testers;
    }

    public void setTesters(Set<Tester> testers) {
        this.testers = testers;
    }

    public Set<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(Set<Bug> bugs) {
        this.bugs = bugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        return id != null ? id.equals(device.id) : device.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
