package gately.christopher.testermatching.setup;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import gately.christopher.testermatching.model.*;
import gately.christopher.testermatching.repositories.BugRepository;
import gately.christopher.testermatching.repositories.DeviceRepository;
import gately.christopher.testermatching.repositories.TesterRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class setup {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;
    private final BugRepository bugRepository;

    public setup(TesterRepository testerRepository, DeviceRepository deviceRepository, BugRepository bugRepository) {
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
        this.bugRepository = bugRepository;
    }

    @PostConstruct
    private void setup() {
        loadTesters();
        loadDevices();
        loadTesterDevice();
        loadBugs();
    }

    private void loadTesters() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/testers.csv")).withSkipLines(1).build();

            List<Tester> testerList = reader.readAll().stream().map(data-> {
                Tester testerObject = new Tester();
                testerObject.setId(Integer.parseInt(data[0]));
                testerObject.setFirstName(data[1]);
                testerObject.setLastName(data[2]);
                testerObject.setCountry(data[3]);
                testerObject.setLastLogin(Timestamp.valueOf(data[4]));
                return testerObject;

            }).collect(Collectors.toList());
            testerRepository.saveAll(testerList);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void loadDevices() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/devices.csv")).withSkipLines(1).build();
            List<Device> deviceList = reader.readAll().stream().map(data-> {
                Device deviceObject = new Device();
                deviceObject.setId(Integer.parseInt(data[0]));
                deviceObject.setDescription(data[1]);
                return deviceObject;

            }).collect(Collectors.toList());
            deviceRepository.saveAll(deviceList);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void loadTesterDevice() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/tester_device.csv")).withSkipLines(1).build();
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                Tester tester = testerRepository.getTester(Integer.parseInt(nextRecord[0]));
                Device device = deviceRepository.findById(Integer.parseInt(nextRecord[1])).orElse(null);
                tester.getDevices().add(device);
                testerRepository.save(tester);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void loadBugs() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/bugs.csv")).withSkipLines(1).build();
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                int bugId = Integer.parseInt(nextRecord[0]);
                Device device = deviceRepository.findById(Integer.parseInt(nextRecord[1])).orElse(null);
                Tester tester = testerRepository.findById(Integer.parseInt(nextRecord[2])).orElse(null);

                if (tester != null && device != null) {
                    Bug bug = new Bug(bugId, device, tester);
                    bugRepository.save(bug);
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
