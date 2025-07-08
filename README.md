# Enterprise-Java-Development-4.02

## Descripción del Proyecto

Este proyecto consiste en desarrollar una API REST en Java utilizando Spring Boot para manejar la información de médicos y pacientes de un hospital multi-ciudad. El proyecto utiliza una base de datos MySQL para almacenar la información.

## Requisitos

### Base de Datos

Se deben crear dos tablas en la base de datos:

#### Tabla `Employee` (Médicos):

| employee_id | department    | name              | status  |
|-------------|--------------|-------------------|---------|
| 356712      | cardiology   | Alonso Flores     | ON_CALL |
| 564134      | immunology   | Sam Ortega        | ON      |
| 761527      | cardiology   | German Ruiz       | OFF     |
| 166552      | pulmonary     | Maria Lin         | ON      |
| 156545      | orthopaedic  | Paolo Rodriguez   | ON_CALL |
| 172456      | psychiatric  | John Paul Armes   | OFF     |

#### Tabla `Patient` (Pacientes):

| patient_id | name            | date_of_birth | admitted_by |
|------------|-----------------|---------------|-------------|
| 1          | Jaime Jordan    | 1984-03-02    | 564134      |
| 2          | Marian Garcia   | 1972-01-12    | 564134      |
| 3          | Julia Dusterdieck | 1954-06-11  | 356712      |
| 4          | Steve McDuck    | 1931-11-10    | 761527      |
| 5          | Marian Garcia   | 1999-02-15    | 172456      |

### Especificaciones

Debes crear rutas REST que permitan obtener la siguiente información:

1. **Obtener todos los médicos**: Crear una ruta que devuelva todos los médicos.
2. **Obtener un médico por ID**: Crear una ruta que devuelva un médico por `employee_id`.
3. **Obtener médicos por estado**: Crear una ruta que devuelva los médicos por su estado (ON, OFF, ON_CALL).
4. **Obtener médicos por departamento**: Crear una ruta que devuelva los médicos según su departamento.
5. **Obtener todos los pacientes**: Crear una ruta que devuelva todos los pacientes.
6. **Obtener un paciente por ID**: Crear una ruta que devuelva un paciente por `patient_id`.
7. **Obtener pacientes por rango de fecha de nacimiento**: Crear una ruta que devuelva los pacientes nacidos dentro de un rango de fechas.
8. **Obtener pacientes por departamento del médico que los atendió**: Crear una ruta que devuelva los pacientes cuyo médico pertenece a un departamento específico.
9. **Obtener todos los pacientes con un médico cuyo estado sea OFF**: Crear una ruta que devuelva todos los pacientes cuyo médico tiene el estado "OFF".

## Configuración de la Base de Datos

### `application.properties`

La conexión a la base de datos se configura en el archivo `application.properties` de la siguiente manera:

```properties
spring.application.name=demo

# Configuración de la conexión a la base de datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3314/demo?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Ironhack
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
