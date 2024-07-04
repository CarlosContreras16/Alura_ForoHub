# Proyecto Alura ForoHub
Foro Hub es un proyecto de una API Rest para consumir datos y testearlos en una aplicación tipo Postman. Creado con el lenguaje de programación Java que es utlizado en la web del lado del servidor(back end).

Base de datos
La API tiene una conexión a una base de datos ralacional MYSQL que se describe a continuación:

1. Tabla User: Contiene los usuarios que se logean y usan esos topicos del foro.
2. Tabla Course: Contiene los cursos que se imparten en el foro.
3. Tabla Profile: Contiene los diferentes tipos de perfiles de los usuarios (Administrator, Expert, Junior).
4. Tabla Topic:  Es la parte fundamental del challenge, crea el foro o la consulta que tiene un usuario en el sistema.
5. Tabla Status: Contiene el estado de los tópicos (Active, Inactive).
6. Tabla Responses: Contiene las respuestas a cada de los tópicos del sistema.


<h3> 🔨 Tecnologias Utlizadas</h3>

- Lombok
- JDK 17.
- Maven.
- Spring Web
- Spring Boot.
- MySQL.
- Spring Data JPA.
- Flyway Migration
- Validation
- Spring Security - Auth0 para la creación de JWT.
- Swagger UI.
