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
- dependency on `build.gradle`:
```
implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.3'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.3'
```
- settings to `application.properties`:
```properties
jwt.secret=helpDeskLeo
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

18. Create `JwtTokenUtil` class:
- implements `Serializable`;
- with the attributes:
  * `private static final long serialVersionUID = 1L`;
  * `static final String CLAIM_KEY_USERNAME = "sub"`;
  * `static final String CLAIM_KEY_CREATED = "create"`;
  * `static final String CLAIM_KEY_EXPIRED = "exp"`;
  * `private String secret`;
  * `private Long expiration`;
- annotated with `@Component`.

![Image-UML-Diagram-Class-JwtTokenUtil](imgs/UML-Diagram-Class-JwtTokenUtil.jpg)

19. Create class `JwtUserDetailsServiceImpl`:
- implement `UserDetailsService`;
- has an attribute of the `UserService` class.

![Image-UML-Diagram-Class-JwtUserDetailsServiceImpl](imgs/UML-Diagram-Class-JwtUserDetailsServiceImpl.jpg)

20. Create `JwtAuthenticationEntryPoint` class:
- implements `AuthenticationEntryPoint` and `Serializable`;
- has a `private static final long serialVersionUID = 1L` attribute.

![Image-UML-Diagram-Class-JwtAuthenticationEntryPoint](imgs/UML-Diagram-Class-JwtAuthenticationEntryPoint.jpg)

21. Create `JwtAuthenticationTokenFilter` class:
- extends `OncePerRequestFilter`;
- has attributes from the `UserDetailsService` and `JwtTokenUtil` classes.
- annotated with `@Component`.

![Image-UML-Diagram-Class-JwtAuthenticationTokenFilter](imgs/UML-Diagram-Class-JwtAuthenticationTokenFilter.jpg)

22. Add `WebSecurityConfiguration` class:
- with attributes of the classes `JwtAuthenticationEntryPoint`, `UserDetailsService`, `JwtAuthenticationTokenFilter`;
- has the methods `protected SecurityFilterChain filterChain(HttpSecurity http)` and `public PasswordEncoder encoder()`.
- annotated with `@Configuration` and `@EnableWebSecurity`.

![Image-UML-Diagram-Class-WebSecurityConfiguration](imgs/UML-Diagram-Class-WebSecurityConfiguration.jpg)

23. Add `JwtAuthenticationRequest` class:
- with attributes `private static final long serialVersionUID = 1L`, `private String email`, `private String password`;
- with default constructor and constructor with parameters `String email`, `String password`.

![Image-UML-Diagram-Class-JwtAuthenticationRequest](imgs/UML-Diagram-Class-JwtAuthenticationRequest.jpg)

24. Add `CurrentUser` class:
- in the `api.security.models` package;
- with attributes `private String token`, `private User user`;
- with default constructor and constructor with parameters `String token`, `User user`.

![Image-UML-Diagram-Class-CurrentUser](imgs/UML-Diagram-Class-CurrentUser.jpg)

25. Add `AuthenticationRestController` class:
- in the `api.security.controllers` package;
- with attributes of the classes `AuthenticationManager`, `JwtTokenUtil`, `UserDetailsService`, `UserService`;
- with the constructor with all attributes as parameters;
- annotated with `@RestController` and `@CrossOrigin(origins = "*")`.

![Image-UML-Diagram-Class-AuthenticationRestController](imgs/UML-Diagram-Class-AuthenticationRestController.jpg)

26. Change `UdemyJavaSpringHelpDeskApplication` class:
- add method `void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder)`;
- add method `CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder)` annotated with `@Bean`.

![Image-UML-Diagram-Class-UdemyJavaSpringHelpDeskApplication](imgs/UML-Diagram-Class-UdemyJavaSpringHelpDeskApplication.jpg)

27. Add `SimpleCORSFilter` class:
- in the `api.security.filters` package;
- implements `Filter`;
- with attribute `private final Log logger = LogFactory.getLog(this.getClass())`;
- annotated with `@@Component` and `@Order(Ordered.HIGHEST_PRECEDENCE)`.

![Image-UML-Diagram-Class-SimpleCORSFilter](imgs/UML-Diagram-Class-SimpleCORSFilter.jpg)


## References
Udemy - Angular 5, JWT, Spring Boot,REST,Security,Data e MongoDB - Francis Klay Rocha:
https://www.udemy.com/course/angular-5-jwt-spring-rest/

MVN Repository - JJWT :: API » 0.12.3:
https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.12.3

MVN Repository - JJWT :: Impl » 0.12.3:
https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl/0.12.3

MVN Repository - JJWT :: Extensions :: Jackson » 0.12.3
https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson/0.12.3

Stack Overflow - Question 72381114:
https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring

Alura - Fórum - 228994:
https://cursos.alura.com.br/forum/topico-springboot-2-7-injetar-authenticationmanager-sem-o-websecurityconfigureradapter-228994

GitHub -  klayrocha - angular-spring-api:
https://github.com/klayrocha/angular-spring-api/tree/master

Java Doc - io.jsonwebtoken - jjwt-api - 0.12.3:
https://javadoc.io/doc/io.jsonwebtoken/jjwt-api/latest/deprecated-list.html

Apps developer blog - Add and validate custom claims in jwt
https://www.appsdeveloperblog.com/add-and-validate-custom-claims-in-jwt/