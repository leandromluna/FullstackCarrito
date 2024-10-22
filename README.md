# Carrito - Proyecto de Prueba Técnica

## Descripción
Este proyecto es un carrito de compras construido con Spring Boot y React.

## Login y Fechas especiales:
vip@vip.com password: vip


vip2@vip.com password: vip


vip3@vip.com password: vip


user@user.com password: user


user2@user.com password: user

Desde el 21 Diciembre al 02 de Enero

Desde el 20 al 29 de Marzo

Desde el 20 de Agosto al 20 de Septiembre

Desde el 06 al 10 de Octubre

## Tecnologías Utilizadas
- **Backend:**
  - Java JDK 17
  - Spring Boot
  - JPA/Hibernate
  - H2 Database (Base de datos en memoria)
  - Swagger para la documentación de API
  - Postman para pruebas de API

- **Frontend:**
  - React.js
  - Node.js 18 (para desarrollo local)

## Estructura del Proyecto
- **Backend**: Contiene la lógica del servidor, controladores, servicios y repositorios.
- **Frontend**: Contiene la aplicación React y sus componentes.

## Instalación y Ejecución

### Requisitos Previos
Para ejecutar este proyecto necesitarás tener instalado:
- [Docker](https://www.docker.com/get-started)
- (Opcional) Node.js 18 para el desarrollo del frontend
- (Opcional) Java 17

### Clonar el Repositorio
Clona este repositorio en tu máquina local:
```bash
git clone https://gitlab.com/s4phulkx/challengefullstack.git
cd challengefullstack
```
### Docker
Buildea y levanta los contenedores:

```bash
docker-compose up --build
```

### Alternativas

Frontend:
```bash
npm install
npm run dev
```

Backend: 
```bash
java -jar target/carrito-0.0.1-SNAPSHOT.jar
```

