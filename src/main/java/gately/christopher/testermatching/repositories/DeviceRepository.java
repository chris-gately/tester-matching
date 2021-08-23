package gately.christopher.testermatching.repositories;

import gately.christopher.testermatching.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
