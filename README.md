# Sistema de Citas Inmobiliarias

Este proyecto es una aplicación de gestión de citas para clientes, agentes y administradores de un sistema inmobiliario.

## Configuración

### Requisitos
- **Java 17** o superior
- **Maven** para la gestión de dependencias
- Una base de datos **MySQL**

## Instrucciones para levantar el proyecto

### 1. Hacer un fork del repositorio

- Dirígete al repositorio del proyecto en GitHub: [Sistema de Citas Inmobiliaria](https://github.com/SergioCB20/Sistema-Citas-Inmobiliaria).
- Haz un fork del repositorio en tu cuenta de GitHub para poder realizar cambios y usar el proyecto en tu propio entorno.

### 2. Crear el schema `sistema-citas` en tu servidor local de MySQL

- En tu servidor local de MySQL, crea una nueva base de datos llamada `sistema-citas`. Puedes hacerlo ejecutando el siguiente comando en tu cliente MySQL:

```sql
CREATE DATABASE sistema_citas;
```

## Modificar el archivo `application.properties`

1. Navega al archivo `src/main/resources/application.properties` dentro de tu proyecto.
2. Modifica las siguientes propiedades con los valores correspondientes a tu configuración local de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema-citas
spring.datasource.username=root
spring.datasource.password=TuContraseña
spring.jpa.hibernate.ddl-auto=update
```

## Ejecutar el script `data.sql` para proyectos iniciales

1. Dirígete al archivo `src/main/resources/data.sql` dentro de tu proyecto.
2. Este archivo contiene las instrucciones SQL necesarias para poblar la base de datos con proyectos de ejemplo, esto se hizo para simular que eso ya fue creado en un sistema aparte.

## Ejecutar el proyecto

Una vez realizados los pasos anteriores, puedes levantar el proyecto en tu entorno local.

### Usando Maven

Si estás utilizando Maven, ejecuta el siguiente comando en la raíz del proyecto para iniciar el servidor Spring Boot:

```bash
mvn spring-boot:run
```

## Documentación de Endpoints

### **AdministradorController**

- **GET** - `/api/administradores/usuarios` - Obtiene la lista de todos los usuarios del sistema.
- **GET** - `/api/administradores/citas/estado/{estado}` - Obtiene todas las citas con un estado específico.
- **POST** - `/api/administradores/citas/{id}/asignar` - Asigna un agente a una cita específica.
- **POST** - `/api/administradores/citas/{id}/rechazar` - Rechaza una cita.

### **ClienteController**

- **POST** - `/api/clientes/citas` - Permite al cliente autenticado crear una nueva cita.
- **GET** - `/api/clientes/citas/{clienteId}` - Obtiene todas las citas asociadas a un cliente.
- **POST** - `/api/clientes/citas/{id}/reabrir` - Solicita la reapertura de una cita.

### **SignUpController**

- **POST** - `/api/signup` - Registra un nuevo usuario en el sistema (cliente, agente o administrador).

### **AgenteController**

- **GET** - `/api/agentes/citas/{agenteId}` - Obtiene todas las citas asignadas a un agente.
- **POST** - `/api/agentes/citas/{id}/completar` - Marca una cita como completada.


