# Restapitest
Simple REST API test with Swagger UI

#### What you need are the following:

* Jdk 
* Maven

#### Building and running the project

* Clone the project from github using the below command

```
> git clone https://github.com/selvaebi/restapitest
```

* Build / Package the project to create a standalone jar

``` 
> mvn clean package
```

* Run the jar to start the application

```
> java -jar target/rest-api-test-1.0-SNAPSHOT.jar
```

#### Accessing the endpoints
  Root url / is a unsecured service that can be accessed without credentials , i.e.

```
> curl http://localhost:8080

```

  The rest of the services are protected by JWT Role Based Authorization with Spring Security. So the API is can be 
  via a OpenAPI standard Swagger Tool on the url <http://localhost:8080/swagger-ui.html> on the browser
  
  The API is protect by default security users with below credentials
  * user@ebi.ac.uk/user - with access only to GET methods having ony ROLE_USER
  * admin@ebi.ac.uk/admin - with access to all methods having ROLE_ADMIN
  
  In order to access endpoints we need to get the access token from login endpoint
```
> curl -X POST -d 'email=user@ebi.ac.uk&password=user' localhost:8080/login

Returns Bearer Token -> {"Authorization":"Bearer ey...."}
```
  Copy paste the token including word Bearer in the value box after clicking Authorize button in the top right corner
   of Swagger. eg. "Bearer eyfhj.kslkf" and click close. And so afterwards you can try the endpoints.
   
   Can try using curl command as well passing the token in the header like below,

```
> curl -X GET "http://localhost:8080/persons" -H "accept: application/json" -H "Authorization: Bearer eyJh"

```
New users can signup using /securityUsers/sign-up url.

```
 > curl -X POST -d 'email=user1@ebi.ac.uk&password=user' localhost:8080/securityUsers/sign-up
 
 > curl -X POST -d 'email=user1@ebi.ac.uk&password=user' localhost:8080/login
 ```
 A user's role can only be changed by admin using /securityUsers/changeRoles url
 ```
 > curl -X POST -d 'email=admin@ebi.ac.uk&password=admin' localhost:8080/login
  
 Returns Bearer Token -> {"Authorization":"Bearer ey...."}
  ```
  
  ```
  Then change role of user1
   
 > curl -X POST -d 'email=user1@ebi.ac.uk&role=ROLE_ADMIN' localhost:8080/securityUsers/changeRoles \
 -H "Authorization:Bearer eyJh"
 ```


### Building Docker image 

If we don't want to install maven or jdk locally we can create a container for the application
with dependencies like maven jdk , build and run the container.

#### Prerequisite
  * Docker installed
  
#### Build the docker image

  Change directory to the folder where Dockerfile is present and run the below command

  ```
  > docker build -t restapitest . 
  
  > docker run -p 8080:8080 -d restapitest
  
  ```
  
  

