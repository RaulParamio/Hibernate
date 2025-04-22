# 🛠️ Proyecto de Gestión de Clientes y Pedidos con Hibernate

Este proyecto es una aplicación Java que permite gestionar una base de datos relacional utilizando Hibernate como ORM. Incluye operaciones CRUD completas para las entidades **Cliente** y **Pedido**,
implementadas con una arquitectura orientada a buenas prácticas, como la separación de responsabilidades, el uso de transacciones centralizadas y patrones de repositorio.

## Tecnologías utilizadas

- ☕ **Java** : Lenguaje de programación para el desarrollo de la aplicación.
- 🛢️ **Hibernate (JPA)** : Framework ORM para la gestión de la persistencia de datos.
- 🐬 **MySQL** : Sistema de gestión de bases de datos utilizado para almacenar la información de los clientes y pedidos.
- 🧰 **Maven** : Para la gestión de dependencias y la construcción del proyecto.

## Requisitos

- **JDK 11 o superior**.
- **MySQL**: Base de datos relacional.
- **Maven**: Para la gestión de dependencias y la construcción del proyecto.

## ⚙️ ¿Qué hace esta aplicación?

Esta app permite:

- ➕ Crear clientes y pedidos
- 🧾 Consultar información
- ✏️ Modificar registros
- ❌ Eliminar clientes y sus pedidos asociados
- 🔄 Gestionar todo dentro de transacciones seguras

## 🧱 Estructura del proyecto

El proyecto está organizado en capas para una mejor separación de responsabilidades:


| Carpeta         | Descripción                                                  |
|-----------------|--------------------------------------------------------------|
| `model/`        | 🧾 Entidades `Cliente` y `Pedido`                             |
| `repository/`   | 📁 Patrón repositorio y sus implementaciones                      |
| `transaction/`  | 🔄 Lógica de manejo de transacciones con Hibernate            |
| `util/`         | ⚙️ Configuración y utilidades de Hibernate                    |
| `app/`          | 🚀 Clase principal que ejecuta la aplicación (`MainApp.java`) |
