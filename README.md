BOOKS REACTIVE REPOSITORY WITH JPA-H2, REST AND GUI
==============

Spring Boot application that implements a book's repository using 
CRUD (Create, Read, Update, Delete) operations to create and recover
objects (books) stored in a H2 data base using Spring Data JPA.
The CRUD operations are made via RESTful.
The GUI is made using the framework Vaadin.

Modules:
========
- Spring Boot
- Spring Data JPA
- Spring Boot - HATEOAS for RESTful Services
- Spring Boot Web 
- Vaadin - Java web framework - https://vaadin.com
- H2 In-Memory Database
- RxJava2

Build the jar:
-------------------------
./gradlew build

Run the jar:
-------------------------
java -jar build/libs/rxjava-jpa-h2-rest-gui-0.0.1-SNAPSHOT.jar

Test the application:
-------------------------
You can work with CRUD operations in two ways:

1. Connect to the server via http://localhost:8080/books using
for example a Restful API tester, like Postman, and check the 
CRUD operations.

Note: the json input for PUT or POST should be like:

{
	"author" : "book´s author",
	"title" : "book´s title"
}

2. Connect to the server http://localhost:8080 and use the GUI
to check the CRUD operations.


You can see that the application is reactive, opening more than 
one connection to the server http://localhost:8080 and, check that 
any time you made a CRUD operation with one client (web browser 
or postman) the others clients are automatically updated.