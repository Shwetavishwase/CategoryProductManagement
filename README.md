# CategoryProductManagement

spring.application.name=CategoryProjectManagement

server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/CategoryProductMgnt
spring.datasource.username=root
spring.datasource.password=Shwet@15
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true




How to run machine code

1.	Open Eclipse IDE and open your spring boot project
2.	Open the base package and find the class which ends with Application (ex. CategoryProjectMgntApplication ) and this main class should annotate with @SpringBootApplication.
3.	Right click on the main class and you will get the option as Run as.
4.	Click on Run as -> Spring Boot App.
5.	Check the eclipse console and verify that whether the application is started or not.
6.	For testing API’s Open Postman and make folders for each entity
7.	Add a request for each API and hit the request to check whether the API is working or not.
