# crud-hateoas-rest-api
Sample Spring Boot REST API with HATEOAS, JPA, MySQL/AWS.

## Guidelines
1. Create a MySQL database named "corebanking".

2. Change "src\main\resources\application.properties" file for MySQL/AWS configuration:
```bash
spring.datasource.url = jdbc:mysql://localhost:3306/corebanking?useSSL=false
#spring.datasource.url = jdbc:mysql:/[YOUR-AWS-RDS-ADDRESS].rds.amazonaws.com:3306/corebanking?useSSL=false
spring.datasource.username = root
spring.datasource.password = q1w2e3r4
```

3. Compile:
```
mvnw clean package
```

4. Run:
```
java -jar target/crud-hateoas-rest-api-0.0.1-SNAPSHOT.jar
```
