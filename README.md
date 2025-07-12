# Enterprise-Java-Development-4.06

## Pruebas con MockMvc

Se parte del proyecto hecho Enterprise-Java-Development-4.04.

Este proyecto incluye pruebas de integración para todas las rutas definidas, utilizando `MockMvc`. Estas pruebas permiten validar tanto los casos positivos como negativos de los endpoints REST implementados.

### Configuración y uso de MockMvc

Se utilizó el entorno de pruebas de Spring Boot (`@SpringBootTest`) junto con `MockMvc` y `WebApplicationContext` para simular peticiones HTTP sin necesidad de desplegar el servidor.

Pasos seguidos para realizar las pruebas:

1. **Importación de dependencias**: Se añadieron las dependencias necesarias para pruebas (`spring-boot-starter-test`).
2. **Configuración de contexto**: Se utilizó `@SpringBootTest` y `@AutoConfigureMockMvc` para levantar el contexto de Spring y autowirear `WebApplicationContext`.
3. **Inicialización de MockMvc**: Usando `MockMvcBuilders.webAppContextSetup(webApplicationContext)`.
4. **Pruebas de rutas GET, POST y PUT**:
    - Se realizaron peticiones simuladas a los endpoints de empleados y pacientes.
    - Se validaron respuestas exitosas con `status().isOk()` y contenido esperado con `jsonPath`.
    - Se probaron respuestas erróneas con `status().isBadRequest()` y validación del mensaje con `containsString("Bad Request")`.
5. **Parámetros de ruta y query**: Se utilizaron `.param("paramName", "paramValue")` para enviar parámetros en las pruebas.
6. **Cobertura**: Se probaron todas las rutas incluidas en los labs 4.02 y 4.04.
7. **Aislamiento**: Las pruebas no afectan el estado de otras, asegurando su independencia y confiabilidad.

> Las pruebas se encuentran ubicadas en el paquete correspondiente dentro de `src/test/java`, organizadas por controlador. Cada test cubre un caso funcional definido en la sección de especificaciones del proyecto.

