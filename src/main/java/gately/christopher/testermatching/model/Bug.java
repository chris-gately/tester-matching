package gately.christopher.testermatching.model;

import javax.persistence.*;

@Entity
public class Bug {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    private Tester tester;

    public Bug() {
    }

    public Bug(Integer id, Device device, Tester tester) {
        this.id = id;
        this.device = device;
        this.tester = tester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bug bug = (Bug) o;

        return id != null ? id.equals(bug.id) : bug.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
