<h1>Human Resources Solution System 
<img src="https://linuxbsdos.com/wp-content/uploads/2015/11/oracle-java-700x410.png" height="5%"/> 
</h1>

### REST application to manage whole recruitment process.

#### Technologies
- Java 8
- Spring Boot
- Spring Security
- JPA
- PostgreSQL
- Lombok
- Mapstruct
- PBKDF2 (password hashing)
- JWT

Setup
-----
- Clone repository.

       git clone https://github.com/Crycald/HumanResourcesSolutionSystem_back-end.git

- Download Gmail credentials from [here](https://drive.google.com/file/d/1_PJJMYIftk7xB50_izhSGq81SFqbV2XN/view?usp=sharing).
- Download Database credentials from [here](https://drive.google.com/file/d/19A7zVSPSsssPkcUrjSvIQ-eOMCY1h_kC/view?usp=sharing).
- Install the latest version of [Java 8](https://java.com), [Maven](https://maven.apache.org/download.cgi) and [PostgreSQL](https://www.postgresql.org).
- Set the environment variables for sending emails: `EMAIL_USER` `EMAIL_PASSWORD`.
- Create Database with name `hrss`.
- Set the environment variables for Database: `POSTGRESQL_USER` `POSTGRESQL_PWD`.
- You may need to set your `JAVA_HOME`.

```bash
cd HumanResourcesSolutionSystem_back-end
# Compile and run
mvn compile install
mvn spring-boot:run
```
