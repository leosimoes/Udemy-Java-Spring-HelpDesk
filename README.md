# Udemy - Help Desk - Back-end with Spring

## Project Starter
![IntelliJ-Project-Configuration](imgs/IntelliJ-Spring-Initializr-1.jpg)

![IntelliJ-Project-Dependencies](imgs/IntelliJ-Spring-Initializr-2.jpg)


## MongoDB
- Download MongoDB Community Server: https://www.mongodb.com/try/download/community
- Install MongoDB Community Server with MongoDB Compass.
- Run MongoDB Compass and connect:

![Image-MongoDbCompass](imgs/MongoDbCompass.jpg)


## Steps
1. Install JDK, IDE (IntelliJ) and MongoDB.
2. Create project with the specifications shown previously.
3. Add MongoDB to the project:
- Add dependency `implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'` in `build.grade`.
- Configure `application.properties`:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=local
```
4. Add validator:
- Add the dependency `implementation group: 'javax.validation', name: 'validation-api', version: '2.0.1.Final'` in `build.grade`.
5. Create `ProfileEnum`, with 3 possible values, and in the `api.enums` package:

![Image-UML-Diagram-Class-ProfileEnum](imgs/UML-Diagram-Class-ProfileEnum.jpg)

6. Add User class:
- with attributes `id`, `email`, `password` and `profile`;
- in the `api.entities` package;
- annotated with `@Document` for MongoDB;
- annotated with `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` for Lombok.

![Image-UML-Diagram-Class-User](imgs/UML-Diagram-Class-User.jpg)

7. Create `PriorityEnum`, with 3 possible values, and in the `api.enums` package:

![Image-UML-Diagram-Class-PriorityEnum](imgs/UML-Diagram-Class-PriorityEnum.jpg)

8. Create `StatusEnum`, with 6 possible values, and in the `api.enums` package:
   
![Image-UML-Diagram-Class-StatusEnum](imgs/UML-Diagram-Class-StatusEnum.jpg)

9. Add `Ticket` class:
- with attributes `user`, `assignedUser`, `date`, `title`, `number`, `status`, `priority`, `description`, `image`;
- in the `api.entities` package;
- annotated with `@Document` for MongoDB;
- annotated with `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` for Lombok.

![Image-UML-Diagram-Class-Ticket](imgs/UML-Diagram-Class-Ticket.jpg)

10. Add `ChangeStatus` class:
- with attributes `id`, `ticket`, `userChange`, `dateChangeStatus`, `status`;
- in the `api.entities` package;
- annotated with `@Document` for MongoDB;
- annotated with `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` for Lombok.
- as a List attribute in the `Ticket` class and annotated with `@Transient`.

![Image-UML-Diagram-Class-ChangeStatus](imgs/UML-Diagram-Class-ChangeStatus.jpg)

11. Create UserRepository interface
- in the `api.repositories` package;
- extends `MongoRepository`;
- annotated with `@Repository`;
- with `findByEmail` method.

![Image-UML-Diagram-Interface-UserRepository](imgs/UML-Diagram-Interface-UserRepository.jpg)

12. Create `TicketRepository` interface:
- in the `api.repositories` package;
- extends `MongoRepository`;
- annotated with `@Repository`;
- with methods:
    * `findByUserIdOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc`;
    * `findByNumber`.

![Image-UML-Diagram-Interface-TicketRepository](imgs/UML-Diagram-Interface-TicketRepository.jpg)

13. Create `ChangeStatusRepository` interface:
- in the `api.repositories` package;
- extends `MongoRepository`;
- annotated with `@Repository`;
- with `findByTicketIdOrderByDateChangeStatusDesc` method.

![Image-UML-Diagram-Interface-ChangeStatusRepository](imgs/UML-Diagram-Interface-ChangeStatusRepository.jpg)

14. Add `UserService` interface and `UserServiceImpl` class:
- in the `api.services` package;
- with methods `findByEmail`, `findById`, `createOrUpdate`, `delete`, `findAll`.

![Image-UML-Diagram-Class-Interface-UserService](imgs/UML-Diagram-Class-Interface-UserService.jpg)

Note: In stateless authentication (does not maintain states), which does not depend on session,
jwt contains user access data that must be stored somewhere
to be shared between requests.

15. Add jwt requirements for application security:
- dependency `implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.12.3'` on `build.gradle`;
- settings to `application.properties`:
```properties
jwt.secret=helpDesk_leo
jwt.expiration=604800
```

16. Create `JwtUser` class:
- in the `api.security.jwt` package;
- implements `UserDetais`;
- with a `static final long serialVersionUID = -7471177486146141709L` attribute;
- with other attributes `id`, `username`, `password`, `authorities`.

![Image-UML-Diagram-Class-JwtUser](imgs/UML-Diagram-Class-JwtUser.jpg)

17. Create `JwrUserFactory` class:
- in the `api.security.jwt` package;
- with `static JwtUser create(User user)` method;
- with method `static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum)`.

![Image-UML-Diagram-Class-JwtUserFactory](imgs/UML-Diagram-Class-JwtUserFactory.jpg)


## References
Udemy - Angular 5, JWT, Spring Boot,REST,Security,Data e MongoDB - Francis Klay Rocha:
https://www.udemy.com/course/angular-5-jwt-spring-rest/

MVN Repository - JJWT :: API » 0.12.3:
https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.12.3