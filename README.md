# stackplatform
Author: Jordan Buttkevitz

## Build requirements
  * Java 1.8
  * Maven
  * MySQL 5.6 with login and database specified in application.properties - Liquibase will create the database.** goal to move to a in-memory database for testing.

## Configure
  Defaults found at:
  * application.properties

## Run
  1. mvn clean install
  2. mvn spring-boot:run

## Test
  Example: get all teams
  curl -v http://localhost:8080/api/team/
  
## Tech used:
  * Spring Boot
  * Spring Data 
  * Spring Security
  * Hibernate
  * Netflix Hystrix
  * Katharsis HATEOAS - serializes JSON string from resource objects as per JSONAPI spec http://jsonapi.org/format/
  * Liquibase - maintains and tracks database schema changes
  * Mockito/PowerMockito/JUnit/RestAssured for unit testing
  
 ## Future goals:
  * Spring Security ACL tables
  * Netflix Eureka - service discovery
  * Leverage Netflix Hystrix fallbacks
  * Incorporate a AuthServer using JWT to restrict API calls 
  
