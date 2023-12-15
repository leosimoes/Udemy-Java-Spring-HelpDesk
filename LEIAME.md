# Udemy - Help Desk - Back-end com Spring

## Inicialização do Projeto
![IntelliJ-Project-Configuration](imgs/IntelliJ-Spring-Initializr-1.jpg)

![IntelliJ-Project-Dependencies](imgs/IntelliJ-Spring-Initializr-2.jpg)


## MongoDB
- Baixar o MongoDB Community Server: https://www.mongodb.com/try/download/community
- Instalar o MongoDB Community Server com o MongoDB Compass.
- Executar o MongoDB Compass e conectar:

![Image-MongoDbCompass](imgs/MongoDbCompass.jpg)


## Passos
1. Instalar JDK, IDE (IntelliJ) e MongoDB.
2. Criar projeto com as especificações exibidas anteriormente.
3. Adicionar o MongoDB ao projeto:
- Adicionar a dependência `implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'` em `build.grade`.
- Configurar `application.properties`:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=local
```
4. Adicionar validador:
- Adicionar a dependência `implementation group: 'javax.validation', name: 'validation-api', version: '2.0.1.Final'` em `build.grade`.
5. Criar `ProfileEnum`, com 3 possíveis valores, e no pacote `api.enums`:

![Image-UML-Diagram-Class-ProfileEnum](imgs/UML-Diagram-Class-ProfileEnum.jpg)

6. Adicionar classe User:
- com atributos `id`, `email`, `password` e `profile`;
- no pacote `api.entities`;
- anotada com `@Document` para o MongoDB;
- anotada com `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` para o Lombok.

![Image-UML-Diagram-Class-User](imgs/UML-Diagram-Class-User.jpg)

7. Criar `PriorityEnum`, com 3 possíveis valores, e no pacote `api.enums`:

![Image-UML-Diagram-Class-PriorityEnum](imgs/UML-Diagram-Class-PriorityEnum.jpg)

8. Criar `StatusEnum`, com 6 possíveis valores, e no pacote `api.enums`:

![Image-UML-Diagram-Class-StatusEnum](imgs/UML-Diagram-Class-StatusEnum.jpg)

9. Adicionar classe `Ticket`:
- com atributos `user`, `assignedUser`, `date`, `title`, `number`, `status`, `priority`, `description`, `image`;
- no pacote `api.entities`;
- anotada com `@Document` para o MongoDB;
- anotada com `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` para o Lombok.

![Image-UML-Diagram-Class-Ticket](imgs/UML-Diagram-Class-Ticket.jpg)

10. Adicionar classe `ChangeStatus`:
- com atributos `id`, `ticket`, `userChange`, `dateChangeStatus`, `status`;
- no pacote `api.entities`;
- anotada com `@Document` para o MongoDB;
- anotada com `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` para o Lombok.
- como atributo List na classe `Ticket` e anotada com `@Transient`.

![Image-UML-Diagram-Class-ChangeStatus](imgs/UML-Diagram-Class-ChangeStatus.jpg)

11. Criar interface `UserRepository`:
- no pacote `api.repositories`;
- extende `MongoRepository`;
- anotada com `@Repository`;
- com método `findByEmail`.

![Image-UML-Diagram-Interface-UserRepository](imgs/UML-Diagram-Interface-UserRepository.jpg)

12. Criar interface `TicketRepository`:
- no pacote `api.repositories`;
- extende `MongoRepository`;
- anotada com `@Repository`;
- com métodos:
    * `findByUserIdOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc`;
    * `findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc`;
    * `findByNumber`.

![Image-UML-Diagram-Interface-TicketRepository](imgs/UML-Diagram-Interface-TicketRepository.jpg)

13. Criar interface `ChangeStatusRepository`:
- no pacote `api.repositories`;
- extende `MongoRepository`;
- anotada com `@Repository`;
- com método `findByTicketIdOrderByDateChangeStatusDesc`.

![Image-UML-Diagram-Interface-ChangeStatusRepository](imgs/UML-Diagram-Interface-ChangeStatusRepository.jpg)

14. Adicionar interface `UserService` e classe `UserServiceImpl`:
- no pacote `api.services`
- com métodos `findByEmail`,`findById`,`createOrUpdate`,`delete`,`findAll`.

![Image-UML-Diagram-Class-Interface-UserService](imgs/UML-Diagram-Class-Interface-UserService.jpg)

Obs.: Na autenticação stateless (não mantém estados), que não depende de sessão,
o jwt contém os dados de acesso do usuário que devem ser armazenados em algum lugar 
para serem compartilhados entre as requisições.

15. Adicionar requisitos do jwt para segurança da aplicação:
- dependência ao `build.gradle`:
```
implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.2'
```
- configurações ao `application.properties`:
```properties
jwt.secret=helpDesk_leo
jwt.expiration=604800
```

16. Criar classe `JwtUser`:
- no pacote `api.security.jwt`;
- implementa `UserDetais`;
- com um atributo `static final long serialVersionUID = -7471177486146141709L`;
- com outros atributos `id`, `username`, `password`, `autorities`.

![Image-UML-Diagram-Class-JwtUser](imgs/UML-Diagram-Class-JwtUser.jpg)

17. Criar classe `JwtUserFactory`:
- no pacote `api.security.jwt`;
- com método `static JwtUser create(User user)`;
- com método `static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum)`.

![Image-UML-Diagram-Class-JwtUserFactory](imgs/UML-Diagram-Class-JwtUserFactory.jpg)

18. Criar classe `JwtTokenUtil`:
- implementa `Serializable`;
- com os atributos:
  * `private static final long serialVersionUID = 1L`;
  * `static final String CLAIM_KEY_USERNAME = "sub"`;
  * `static final String CLAIM_KEY_CREATED = "create"`;
  * `static final String CLAIM_KEY_EXPIRED = "exp"`;
  * `private String secret`;
  * `private Long expiration`.

![Image-UML-Diagram-Class-JwtTokenUtil](imgs/UML-Diagram-Class-JwtTokenUtil.jpg)

19. Criar classe `JwtUserDetailsServiceImpl`:
- implementa `UserDetailsService`;
- tem um atributo da classe `UserService`.

![Image-UML-Diagram-Class-JwtUserDetailsServiceImpl](imgs/UML-Diagram-Class-JwtUserDetailsServiceImpl.jpg)

20. Criar classe `JwtAuthenticationEntryPoint`:
- implementa `AuthenticationEntryPoint` e `Serializable`;
- tem um atributo `private static final long serialVersionUID = 1L`.

![Image-UML-Diagram-Class-JwtAuthenticationEntryPoint](imgs/UML-Diagram-Class-JwtAuthenticationEntryPoint.jpg)


## Referências
Udemy - Angular 5, JWT, Spring Boot,REST,Security,Data e MongoDB - Francis Klay Rocha:
https://www.udemy.com/course/angular-5-jwt-spring-rest/

MVN Repository - JJWT :: API » 0.11.2:
https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.11.2