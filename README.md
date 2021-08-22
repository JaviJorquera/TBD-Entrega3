# TBD-Entrega3
Tercera entrega de laboratorio TBD por Grupo 3

Para poder probar este programa, se debe tener anteriormente instalado PostgreSQL y Postgis.

A continuacion se indicaran los comandos para instalar la Base de Datos, crear las tablas y poblarlas.

Para probar todas las funcionalidades, debemos crear la base de datos, en esta ocacion usaremos PgAdmin4.
- Primero  se abre pgAdmin4.
- Luego, se debe crear una base de datos, con el nombre de postgres.
- Luego, se deben crear las tablas que tendra la base de datos, para ellos, ejecutaremos un script SQL.
    - Se debe abrir Query Tools, y seleccionar el script createDB.sql y se ejecuta, con esto se crean todas las tablas.
- Luego, se deben cargar datos de prueba, para ello, tambien se cuenta con un script SQL.
    - Se debe abrir Query Tools, y seleccionar el script loadData.sql y se ejecuta, con esto se llenan todas las tablas.

- Despues de haber creado la Base de Datos se debe levantar el backend.
    - Primero se debe ubicar en el directorio \Trabajo3TBD\back y se abre la la consola del sistema operativo.
    - Se ejecuta el comando "gradlew bootrun".
- Luego es turno del frontend.
    - Primero se debe ubicar en el directorio \Trabajo3TBD\front y se abre la la consola del sistema operativo.
    - Se ejecuta el comando "npm install" y se espera a que termine.
    - Luego, se ejecuta el comando "npm run serve".

- En este momento ya podemos acceder a las vistas solicitadas, para ello accederemos a algun navegador web y accederemos a la direccion localhost:8081/ , y desde ahi podremos navegar a las
    - funcionalidades solicitadas mediante los botones disponibles.

- En la vista de las columnas interactivas tendremos disponible una lista con emergencias al costado izquierdo, al momento de hacer click a alguna emergencia, se generara una lista en la parte
    - central de la vista, en la cual mostrara las tareas relacionadas con la emergencia seleccionada, luego, se puede seleccionar una de las tareas, para poder ver a los voluntarios relacionados
    - con esa emergencia y con esa tarea.

- En la vista del mapa, se tendra disponible una lista con emergencias al costado izquierdo, al momento de hacer click a alguna emergencia, se generaran marcadores en el mapa indicando la
    - ubicacion de todos los voluntarios relacionados con esa emergencia.

- Para poder terminar la ejecucion del backend y del fronted, debe abrir las consolas correspondientes, luego de seleccionar una consola, basta con apretar la combinacion de teclas ctrl+c para
    - que se nos ofresca la opcion de cerrar la ejecucion apretando la letra S y Enter.