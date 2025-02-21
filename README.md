# Desafio Backend: Autenticação

Resolução do desafio proposto pelo repositório Backend Brasil, detalhes do desafio [aqui](https://github.com/backend-br/desafios/blob/master/authentication/PROBLEM.md)

## Tecnologias utilizadas

- Java 21
- Quarkus 
- Junit

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