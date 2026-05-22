# Sistema de Gestion de Objetos Perdidos

Solucion de software orientada a resolver el reporte, almacenamiento y devolucion eficiente de pertenencias dentro de un campus institucional. El sistema centraliza los reportes de objetos hallados, facilitando a los usuarios la busqueda y reclamo de sus pertenencias mediante una arquitectura escalable.

## Caracteristicas Principales

* **Registro y Clasificacion:** Categorizacion detallada de objetos con estados en tiempo real (Encontrado, En Custodia, Reclamado).
* **Busqueda Avanzada:** Filtros por fecha, lugar de hallazgo y categoria para agilizar la localizacion.
* **Modulo de Reclamacion:** Flujo logico para la validacion y entrega segura del objeto al dueño real.
* **Control Administrativo:** Gestion de inventario de la oficina de objetos perdidos y auditoria de entregas.

## Stack Tecnologico

* **Backend:** Java, Spring Boot, Spring Data JPA.
* **Base de Datos:** MySQL (Diseño Entidad-Relacion optimizado para consistencia de datos).
* **Arquitectura:** Separacion clara de conceptos mediante controladores, servicios y repositorios.
