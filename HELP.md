# Getting Started

# Webserver - Weather API System

Weather API System is a combination of few APIs such as for a given city, returns the current temperature, air pressure and an indicator of whether you should take an umbrella with you or not

## Architectural Thoughts

I have follwed SOLID principle before designing it so that each layer does have their own responsibilities like as follows 

```
REST LAYER [End point for external systems] <---> SERVICE LAYER [RESPONSIBLE FOR BUSINESS LOGIC , AND PASS THROUGH DATA TO DAO LAYER] <--> DAO LAYER [RESPONSIBLE FOR DATA BASE OPERATIONS]

```

## Database Design

Weather API System is an API based approch where we have to keep track for city wise historical data

As per the design please find below table approach which I kept in Database.

Table 1 : weather_history responsible for storing Weather history

```
+-----------+--------------+------+-----+---------+------------------------------------------------+
| Field     | Type         | Null | Key | Default | Extra 										   |
+-----------+--------------+------+-----+---------+------------------------------------------------+
| id        | int(11)      | NO   | PRI | NULL    | Auto Incremented Unique Id for each entry      |
| city_name | varchar(255) | YES  |     | NULL    | City name for weather information      		   |
| pressure  | double       | YES  |     | NULL    | Air pressure of city   					       |
| temp      | double       | YES  |     | NULL    | temperture of city in degree celcius     	   |
| umbrella  | bit(1)       | YES  |     | NULL    | Flag to indicate whether take umbrella or not  |
+-----------+--------------+------+-----+---------+------------------------------------------------+

```

### Assumptions

While designing APIs I tried to fulfill the scenarios given in Assignment PDF but still I kept some assumptions as briefed below.

```
Period considered as duration between From-To-End date i.e. User will pass Start date to End date for fulfilling data criteria for few APIs
From-TO-End dates are allowed in form of YYYY-MM-DD format only wherever applicable
Net pay for Hourly empolyees are calculated as hourly_wage*working_hours
```
#There should be one API to retrieve a json object with information about the money that should be paid out to an employee in a certain period.

```
For Above mentioned API I considered to provide DATA for all the employees for certain period

```
#The system should allow getting payrolls for an employee for a certain period

```
For Above mentioned API I considered to provide DATA for only an employee for certain period

```

### Prerequisites

For using APIs , seek below softwares to be available in system

```
JAVA [>=1.8]
MAVEN 3
REST CLIENT [IF CURL DOESN'T WORK]
INTERNET BROWSER [For accessing APIs / Swagger UI]
MYSQL
Eclipse [for code walkthrough]

```

Either you may allow to create schema by Application itself or Import Payroll System schema [available in ../resources/payroll_system.sql] in mysql using below command :

```
mysql > source ../resources/payroll_system.sql;

```

### Installing

Please see through below steps for system to be up and running

For Preparing WAR file

```
[code directory]>mvn clean install

```
As I am using Spring-boot it does have embedded Tomcat which we may run using below command :

```
[code directory]>mvn spring-boot:run

```
Please access at given URL for more operations : 

```
http://localhost:8080/api/staffMembers/{endpoint}

```

By Default it would be accessible on 8080 port which may be changed in application.properties by following value change :

```
[code directory]>src>main>resources>application.properties

server.port=[desired port no]

```
For Importing this as Maven Project

```
[code directory]>mvn eclipse:clean
[code directory]>mvn eclipse:eclipse

```
Please import it now as Existing Project

## Running the tests

To See the code Coverage I used the JACOCO library which needs below command to be run :

```
[code directory]>mvn clean verify

```
Above command would build the WAR file as well JACOCO index.html file which would give insight for Test coverage :

```
[code directory]/test/>mvn clean verify

```
Open following file in browser to see the code Coverage percentage :

```
file://[code directory]/target/site/jacoco/index.html

```

### API Docs

API docs are also embedded & available in form of SWAGGER OPEN API docs which may be accessible at below URL once Server is up and running :

```
http://localhost:{port-no}/swagger-ui/index.html

```

## Built With

* [Spring-boot](https://start.spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - Database used
* [iText] (https://itextpdf.com/en) - PDF Generation

## Author

* **Pushpank Tugnawat**  - [Github](https://github.com/pushpanktugnawat)




### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

