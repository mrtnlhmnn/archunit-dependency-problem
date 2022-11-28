# archunit-dependency-problem
ArchUnit, problem with unwanted dependencies (which are not reported as expected)

## What is this?
Description for a potential issue in ArchUnit with detecting and reporting unwanted dependencies between packages.

## Dependencies
We have a Spring Boot application with different packages - quite similar to a standard Onion architecture. 
The app includes JPA-based persistence, REST controller and Web Controller as APIs and some application services (as an adapter between controllers and the persistence). 

We want to ensure that
* neither the REST controllers
* nor the Web controllers 
have direct access to the persistence data, packages, entities etc.

The JUnit test OnionDependenciesTest shows four violations where the RestControllerIncorrectlyUsingPersistenceData uses the persistence entity directly.
But why is this test not also showing the exact same violations for WebControllerIncorrectlyUsingPersistenceData? 

Obviously, the WebControllerIncorrectlyUsingPersistenceData does not expose the persistence class EntityData as it puts its usage to org.springframework.ui.Model only so that a Web interface (like one using Thymeleaf) can retrieve the data.

Still, the dependency from WebControllerIncorrectlyUsingPersistenceData is there and should be reported in the architecture test.
Is there any way of enforcing that?
