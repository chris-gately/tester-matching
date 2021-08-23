# Coding Assignment - Tester Matching

The Tester Matching project is a simple matching system that takes two matching criteria (Country and Device) and
presents a sorted list of results sorted by the number of Bug(s) a Tester has filed for the given Device(s).

The project uses Spring, Hibernate, and an in-memory H2 database.

---

## Setup

### Prerequisites

Git, JDK11, Maven

### Check out sources

`git clone https://github.com/chris-gately/tester-matching.git`

### Compile

`mvn clean install`

---

## Running

### Command Line

Run via the command line with `mvn spring-boot:run` in the source directory

### IDE

Run the class `TesterMatchingApplication.java`

Project will run at http://localhost:8080/

In memory database at http://localhost:8080/h2-console/

To login to database make sure the JDBC URL is set to `jdbc:h2:mem:testdb` and leave everything else as default

---

## Next steps
1. Expand API to allow CRUD operations for Tester, Device, and Bug.
2. Build an additional page to view individual Testers and link to the page from the search results. Tester page could
   include all the details of the Tester along with some summary statistics such as total number of Bugs submitted.
   Could include list of Devices with the count of Bugs the Tester has submitted for each device.
3. Build an additional page to view individual Devices. Device page could include all the details of the Device along
   with some summary statistics such as total number of Bugs. Could include list of Testers with the count of Bugs each
   Tester has submitted for the Device.
