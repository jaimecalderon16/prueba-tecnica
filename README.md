# CRUD Prueba tecnica 

Este proyecto es un CRUD (Create, Read, Update, Delete) simple para gestionar información de estudiantes. Está desarrollado utilizando Angular para el frontend y Spring Boot para el backend.

## Configuración de la Base de Datos

Este proyecto utiliza una base de datos para almacenar la información de los estudiantes. A continuación, se proporciona un script SQL que puedes ejecutar para crear las tablas necesarias y agregar algunos registros de ejemplo.

### Script SQL

El script SQL se encuentra en el archivo [`./dll.sql`](./dll.sql). Puedes ejecutar este script directamente en tu gestor de base de datos **PostgreSQL** para configurar la base de datos.


## Configuracion backend

1. Abre el proyecto en tu IDE preferido (STS, IntelliJ, Eclipse, etc.).

2. Configura la conexión a la base de datos en el archivo application.properties.
3, Ejecuta la aplicación Spring Boot.




## Configuraciofronted

Navega al directorio del frontend:

```bash
cd frontend
```
Instala las dependencias:

```bash
npm install
```
Configura la URL del backend en src/confi.ts
```bash
export const route = 'http://127.0.0.1:8081';
```

Inicia la aplicación Angular:

```bash
npm start
```
## Uso

Accede a la aplicación desde tu navegador web y podrás realizar las operaciones CRUD básicas para gestionar información de estudiantes.