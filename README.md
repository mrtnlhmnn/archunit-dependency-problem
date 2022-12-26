# archunit-dependency-problem
ArchUnit, problem with unwanted dependencies (which are not reported as expected)

## What is this?
Description for a potential issue in ArchUnit with detecting and reporting unwanted dependencies between packages.

# ArchUnit Issue
See https://github.com/TNG/ArchUnit/issues/1012
Will not be fixed as #768 is sufficient, see discussion at #1012

## Dependencies
We have a Spring Boot application with different packages - quite similar to a standard Onion architecture.

The app includes 
* JPA-based persistence, 
* REST controller and Web Controller as APIs 
* and some application services and domain classes. 

We want to ensure with an ArchUnit test that
* neither the REST controllers
* nor the Web controllers 
have direct access to the persistence data, packages, entities etc.

The JUnit test OnionDependenciesTest shows four violations where the RestControllerIncorrectlyUsingPersistenceData 
uses the persistence entity directly. This is expected, great.

But why is the same test not also showing the exact same violations for WebControllerIncorrectlyUsingPersistenceData? 
Obviously, the WebControllerIncorrectlyUsingPersistenceData does not expose the persistence class EntityData as 
it puts its usage to an generic org.springframework.ui.Model only - so that a Web frontend (like one using Thymeleaf) can 
retrieve the data from there.

Still, the unwanted dependency from WebControllerIncorrectlyUsingPersistenceData is there and should be reported 
in the architecture test. Is there any way of enforcing that? 

Only when commenting in line 36 in class WebControllerIncorrectlyUsingPersistenceData, the dependency violation is shown.

