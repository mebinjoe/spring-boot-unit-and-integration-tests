# spring-boot-unit-and-integration-tests

Project demonstrating how to write tests unit and integration in a Spring Boot environment.

**Insights**

`Unit testing`: "In computer programming, unit testing is a software testing method by which individual units of source code, sets of one or more computer program modules together with associated control data, usage procedures, and operating procedures, are tested to determine whether they are fit for use."

`Integration testing`: "Integration testing (sometimes called integration and testing, abbreviated I&T) is the phase in software testing in which individual software modules are combined and tested as a group."

##Spring Boot Testing Features

Spring boot offers a great class to make testing easier: `@SpringBootTest` annotation

This annotation can be specified on a test class that runs Spring Boot based tests.
Provides the following features over and above the regular Spring TestContext Framework:

- Uses `SpringBootContextLoader` as the default ContextLoader when no specific `@ContextConfiguration (loader=…)` is defined.
- Automatically searches for a `@SpringBootConfiguration` when nested `@Configuration` is not used, and no explicit classes are specified.
- Allows custom Environment properties to be defined using the properties attribute.
- Provides support for different web environment modes, including the ability to start a fully running web server listening on a defined or random port.
- Registers a `TestRestTemplate` and/or `WebTestClient` bean for use in web tests that are using a fully running web server.

##Running Unit and Integration Tests

```
$ mvn test
```

##Running the application locally

```
$ mvn spring-boot:run
```

**Need to have**

- [Java 1.8 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3 or above](https://maven.apache.org)