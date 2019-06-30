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
curl http://localhost:8080

```

  The rest of the services require a form login if you are accessing via browser. So the API is accessed via a 
  OpenAPI standard Swagger Tool on the url <http://localhost:8080/swagger-ui.html> on the browser, which 
  redirects 
  initially to a form login page , which can be logged in with below credentials
  
  The API is protect by default security users with below credentials
  * user@ebi.ac.uk/user - with access only to GET methods
  * admin@ebi.ac.uk/admin - with access to all methods. 
  
  In order to access endpoints via command line curl follow the below steps
```
curl -c cookie.txt -F 'username=user@ebi.ac.uk' -F 'password=user' localhost:8080/login

curl -b cookie.txt localhost:8080/persons

curl -b cookie.txt localhost:8080/logout 

```
To add more security users we can post user entity logged in using admin credentials.

```
 curl -c cookie.txt -F 'username=admin@ebi.ac.uk' -F 'password=admin' localhost:8080/login
 
 curl -b cookie.txt -X POST "http://localhost:8080/securityUsers" -H "Content-Type: application/json" -d "{ \"email\": \"newuser@ebi.ac.uk\", \"password\": \"newuser\", \"role\": \"ROLE_USER\"}"
 ```


### Building Docker image 

If we don't want to install maven or jdk locally we can create a container for the application
with dependencies like maven jdk , build and run the container.

#### Prerequesities
  * Docker installed
  
#### Build the docker image

  Change directory to the folder where Dockerfile is present and run the below command

  ```
  > docker build -t restapitest . 
  
  > docker run -p 8080:8080 -d restapitest
  
  ```
  
  

