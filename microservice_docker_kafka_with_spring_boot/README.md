# SAMPLE MICROSERVICE WITH SPRING BOOT

This app will run as is but you can make chanegs as you wish. 

## How to run
It is assumed  that you have docker engine and docker compose installed. If not, install to continue.

**The simplest way to run all the services in Docker:**
`Spring-microservice-sample-with-DOCKER>./setup-docker.sh` 

This will compile and package every service into jar file then create docker images for all required services from their respective Dockerfiles as configured in 'docker-compose.yml' file and the start their instances (containers).
Wait for a minute for all services to start and then move to next(open Test.rest file)

## What's Included on client side
- We have Student service to perform CRUD operation for students. (gender is either 'MALE' or 'FEMALE' in capital letters)
- We have Fees service to update fee payment by students.
- Exam service to record students scores per subject (subject either 'MATHS' or 'LANGUAGES' or 'ARTS').
- Just as a sample, total fees is 1000. You will not be able to enter student scores unless they have fully paid the 1000 fees amount.
- Apache ActiveMQ is used messaging among services (Update student when fees is fully paid and remove student exam records and fees records when Student is deleted)
- Eureka service registry server
- Netflix Zuul Api Gateway service.

* MySQL container:
     * hostname: mysql-server
     * Ports : 3306:3306 (host_port : container_port)
     * Username/Password: root/root

* Apache ActiveMQ:
     * hostname: activemq-server
     * Ports: 61616:61616, 8161:8161
     * Admin UI: http://localhost:8161
     * Username/password: admin/admin

* zuul-apigateway-server:
    * hostname: zuul-apigateway-server
    * Ports: 8762:8762
    * URL: http://localhost:8762/**

* service-registry:
    * hostname: eureka-server
    * Ports: 8761:8761
    * URL: http://localhost:8761/

* student-service:
    * hostname: student-service
    * Ports: 8082:8082
    * URL: http://localhost:8082
    
* fees-service   
    * hostname: fees-service
    * Ports: 8084:8084
    * URL: http://localhost:8084
    
* exams-service  
    * hostname: exams-service
    * Ports: 8083:8083
    * URL: http://localhost:8083 


## REST API
We access the api via 'zuul-apigateway-server' service.
Open Test.rest file for api request description. If you use vcode, you can test the rest api in it's rest extension.

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
