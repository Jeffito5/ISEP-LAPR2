# Supplementary Specification (FURPS+)

## Functionality

- Security: All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters,
including three capital letters and two digits (and email);
- The application must support the English language only.

## Usability

- The user interface must be simple, intuitive and consistent.
- Different interface for staff vs client: Client can only login and see their results. (perguntar ao cliente)
- Within staff, interfaces must also be adapted. Receptionist can only  deal with client and test registers, for example. (perguntar ao cliente)


## Reliability

- The system should not fail more than 5 days in one year. Whenever the system fails, there should be no data loss.

## Performance

- Any interface between a user and the system shall have a maximum response time of 3 seconds. The system should start up in less than 10 seconds.

## Supportability

- Admin can change configuration files
- Unit tests for all methods

## +

### Design Constraints

- Java language using the IntelliJ IDE or Netbeans.
- Adopting recognized coding standards (e.g.: CamelCase).
- Javadoc to generate useful documentation for Java code;

### Implementation Constraints

- The unit tests should be implemented using the JUnit 4 framework
- The JaCoCo plugin should be used to generate the coverage report
- The application should run on all platforms for which there exists a Java Virtual Machine.

- The application should use object serialization to ensure data persistence between two runs of the
application.


### Interface Constraints

- The application graphical interface is to be developed in JavaFX 11

### Physical Constraints

- The application will be deployed to a machine with 8GB of RAM.
