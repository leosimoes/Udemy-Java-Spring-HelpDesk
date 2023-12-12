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


## Referências
Udemy - Angular 5, JWT, Spring Boot,REST,Security,Data e MongoDB - Francis Klay Rocha:
https://www.udemy.com/course/angular-5-jwt-spring-rest/