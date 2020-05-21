# SERVLET JSP SAMPLE CRUD AND USER LOGIN

This is a sample servlet CRUD application that runs on tomcat server. 

## Update
-Make necessary datasource setup in src/main/resources/META-INF/persistence.xml

## Deploy Project
Change directory to project folder and  run `mvn clean compile package` to create war file.
Deploy the war file in tomcat application server and go to 'http://127.0.0.1:<port>/<war file name>/home'. Mine is http://127.0.0.1:8083/ServletCrudSample-1.0/home

## What's Included 
- Create User
- Login to app
- Logout
- Perform CRUD Operations

This is a listing
[<img src="screen/list.png" alt="Sample list"  />]

This is a user registration
[<img src="screen/register.png" alt="Sample registration"  />]

This is a login screen
[<img src="screen/login.png" alt="Sample login"  />]


## Used Tech
- Servlet
- JSP
- JPA(Hibernate)
- Mysql


### From Developers

I am always happy to receive your feedback!
FInd me on [Twitter](https://twitter.com/julian_geniuz)!