# SAMPLE MICROSERVICE WITH SPRING BOOT

This app will run as is but you can make chanegs as you wish. 

## What's Included on client side
- We have Student service to perform CRUD operation for students. (gender is either 'MALE' or 'FEMALE' in capital letters)
- We have Fees service to update fee payment by students.
- Exam service to record students scores per subject (subject either 'MATHS' or 'LANGUAGES' or 'ARTS').
- Just as a sample, total fees is 1000. You will not be able to enter student scores unless they have fully paid the 1000 fees amount.
- Apache ActiveMQ is used messaging among services (Update student when fees is fully paid and remove student exam records and fees records when Student is deleted)
- Eureka service discovery server
- Netflix Zuul Api Gateway service.


## How to run
It is assumed  that you have docker engine and docker compose installed. If not, install to continue.
Change directory to 'Spring-microservice-sample-with-DOCKER' folder and  run `./setup-docker.sh` 
This will compile and package every service into jar file then create docker images for all required services from their respective Dockerfiles as configured in 'docker-compose.yml' file and the start their instances (containers).
Wait for a minute for all services to start and then move to next(open Test.rest file)

## REST API
Open Test.rest file for data entry. If you use vcode, you can test the rest api in it's rest extension.
Or test it using 

## Used Tech
- Spring Boot 2.3.0 
- Java Messaging Service (Apache ActiveMQ)
- Microservices
- Docker Containerization
- JPA(Hibernate)
- Mysql
- Restful Api



### From Developers

I am always happy to receive your feedback!
Find me on [Twitter](https://twitter.com/julian_geniuz)!
