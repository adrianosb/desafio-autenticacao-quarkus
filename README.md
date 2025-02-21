# Desafio Backend: Autenticação

Resolução do desafio proposto pelo repositório Backend Brasil, detalhes do desafio [aqui](https://github.com/backend-br/desafios/blob/master/authentication/PROBLEM.md)

## Tecnologias utilizadas

- Java 21
- Quarkus 
- Junit

## Desafios realizados

|     Desafio    | Tecnologias                   |                             |
|----------------|-------------------------------|-----------------------------|
|  Autenticação  |`Java 21 - Spring Boot`        |[link](https://github.com/adrianosb/desafio-autenticacao-spring-boot)           |
| Autenticação   |`Java 21 - Quarkus`            |[link](https://github.com/adrianosb/desafio-autenticacao-quarkus)           |

## Endpoint

/signin

```
POST {{host}}/signin
Content-Type: application/json

{
    "username": "{{username}}",
    "password": "{{password}}"
}
```

/foo-bar

```
GET {{host}}/foo-bar
Authorization: Bearer token
```