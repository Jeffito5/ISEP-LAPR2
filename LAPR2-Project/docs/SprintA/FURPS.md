# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

- Security: All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters,
including three capital letters and two digits (and email);
- The application must support the English language only.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- The user interface must be simple, intuitive and consistent.
- Different interface for staff vs client: Client can only login and see their results.
- Within staff, interfaces must also be adapted. Receptionist can only  deal with client and test registers, for example.


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

- The system should not fail more than 5 days in one year. Whenever the system fails, there should be no data loss.

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

- Any interface between a user and the system shall have a maximum response time of 3 seconds. The system should start up in less than 10 seconds.

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

- Admin can change configuration files
- Unit tests for all methods

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- Java language using the IntelliJ IDE or Netbeans.
- Adopting recognized coding standards (e.g.: CamelCase).
- Javadoc to generate useful documentation for Java code;

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


- The unit tests should be implemented using the JUnit 4 framework
- The JaCoCo plugin should be used to generate the coverage report
- The application should run on all platforms for which there exists a Java Virtual Machine.

- The application should use object serialization to ensure data persistence between two runs of the
application.


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

- The application graphical interface is to be developed in JavaFX 11

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

The application will be deployed to a machine with 8GB of RAM.
