# Bus Reservation Microservice Project
![Architecture of Microservice](https://media.licdn.com/dms/image/D5612AQHHTTICSfxTgg/article-cover_image-shrink_720_1280/0/1693153639814?e=2147483647&v=beta&t=7i57JjfMjD4uvyQuY4L9pIC3DxNpcZBHVOnFPbFnILE)
Welcome to the Bus Reservation Microservice project! This project provides functionalities for users to view bus details and make reservations.

## Overview

The project consists of two microservices:

1. **Bus Data Service**: This microservice manages bus details such as routes, schedules, and available seats. Users can retrieve bus information from this service.

2. **Booking Data Service**: Responsible for managing bus reservations. It communicates with the Bus Data Service to check the availability of seats and handles booking requests.

Both microservices implement JWT (JSON Web Tokens) for authentication and authorization.

## Features
- <img align="top" alt="Coding" width="400" src="https://shareurcodes.com/photos//rest-api.jpg">

- Users can view bus details including routes, schedules, and available seats.
- Users can make reservations for buses.
- Authentication and authorization are implemented using JWT.

## Technologies Used

- **Spring Boot**: Framework for building microservices.
- **JWT (JSON Web Tokens)**: Used for authentication and authorization.
- **Spring Security**: Provides security features for Spring applications.
- **Eureka Server**: Service registry for registering microservices.
- **OpenFeign**: Used for communication between microservices.


## Authentication and Authorization

JWT is used for authentication and authorization in this project. Users must obtain a JWT token by authenticating with the system before accessing protected resources. The token must be included in the request headers for authorization.
![Architecture of Microservice](https://miro.medium.com/v2/resize:fit:2000/1*RvUzEHQi5JEifWCBY4Rkng.png)

## Contributors

- [Prashanthan v]



