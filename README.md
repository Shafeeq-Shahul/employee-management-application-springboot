# Employee Management Application - Spring Boot

A simple CRUD (Create, Read, Update, Delete) based Spring Boot application.
This project demonstrates:

* ORM with Spring Data JPA

* Building RESTful APIs

* Basics of Spring Security

# Technologies Used

* Java 21

* Spring Boot 3

* Spring Data JPA

* Spring Security (basic)

* MySQL database

* Maven

# Project Structure

```text
src/
└── main/
    └── java/
        └── com/example/employee/
            ├── config/           → Spring Security configuration
            ├── controller/       → REST Controllers
            ├── dto/              → Data Transfer Objects
            ├── exception/        → Custom exceptions and handlers
            ├── mapper/           → Mapping logic between DTOs and entities
            ├── model/            → Entity classes (e.g., Employee)
            ├── repository/       → Spring Data JPA repositories
            ├── service/          → Business logic layer
            └── EmployeeManagementApplication.java  → Main Spring Boot class
