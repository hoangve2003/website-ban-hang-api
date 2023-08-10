# User Guide - E-commerce Website

Hello! This is a README file that introduces an e-commerce website built using Spring Data JPA, Spring Security, Thymeleaf, AngularJS, and RESTful API. This website supports Microsoft SQL Server as the database. Below is some information and basic instructions to get you started.

## Requirements

- JDK 1.8 or higher
- Apache Maven
- Microsoft SQL Server
- Modern web browser

## Installation

1. Clone the source code from the project repository to your local machine.
1. Open a terminal or command prompt, navigate to the directory containing the source code.
1. Run the following command to install dependencies and build the project:

```bash
mvn clean install
```

## Database Configuration

1. Log in to Microsoft SQL Server and create a new database.
1. Open the `application.properties` file in the source code and modify the database connection parameters:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## OAuth Configuration

1. Access the [Google API Console](https://console.developers.google.com/) and create a new application to obtain the Client ID and Client Secret.
1. Open the `application.properties` file in the source code and provide the OAuth login information:

```properties
spring.security.oauth2.client.registration.google.client-id=your_client_id
spring.security.oauth2.client.registration.google.client-secret=your_client_secret
```

## Spring Security Configuration

1. Open the `SecurityConfig.java` file in the source code and configure access rights and permissions for users.

## Running the Application

1. Open a terminal or command prompt, navigate to the directory containing the source code.
1. Run the following command to start the application:

```bash
mvn spring-boot:run
```

3. Open a web browser and access [http://localhost:8080](http://localhost:8080).

## Features

This e-commerce website includes the following features:

- User authentication and registration using OAuth.
- Product management: add, edit, and delete products.
- Order management: view a list of orders and order details.
- User authorization: manage user roles and permissions.
- Sales: allow users to add products to the shopping cart and make payments.

Explore and discover more functionalities of this e-commerce website. If you encounter any issues or have additional questions, please feel free to contact us.

## Contribution

If you would like to contribute to this project, please create a pull request, and we will review it.

Thank you for using this e-commerce website. We hope you have a great experience!
