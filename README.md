# Calculardora Rest



### Tecnologias usadas

- Spring 2
- Java 11
- H2
- Spring Data
- Spring Security
- Maven

### Rota

 - POST http://localhost:8080/expressao/calcular
 - Headers
   - "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
   - "Content-Type": "application/json"


Exemplo request body:
```
{
    "expressao":"3+2.212"
}
```


Exemplo response body:
```
{
    "resultado": "5.22"
}
```