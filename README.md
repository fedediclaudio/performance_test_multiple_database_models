# Tutorial: Cargar registros en las BDs

A continuacion se explica para cada base de datos como insertar los registros propuestos para realizar las pruebas.

## MySQL

Para la inserción de los datos se proveen archivos CSV con datos aleatorios, generados mediante un script escrito en Python, cada una con la respectiva cantidad de inserciones para realizar las pruebas. Para la carga se utilizo el gestor de base de datos Workbench.

Antes de poder importar los registros se debe crear la base de datos correspondiente. Para ello ejecute la siguiente consulta:

```
CREATE DATABASE db2Final;
```
```
CREATE TABLE db2Final.user
CREATE TABLE db2Final.commit
```

Para importar los datos de los siguientes archivos:

[Para 10 mil registros](https://drive.google.com/open?id=1wRj_tHpzTCSK-87KGXH8oHDVfIjNPBmh).

[Para 100 mil registros](https://drive.google.com/open?id=1Sv-dqMTrDpQyvJyVeng-C6zplNcGGxEC).

[Para 1 millon de registros](https://drive.google.com/open?id=13iPCQ6HIPoSFtevv8lGGvxxw0fzQusYd).


Se deben

## MongoDB

Se porporciona un archivo que posee usuarios, en formato BSON, con una cantidad aleatoria de commits. 

Para cargar los documentos en mongo es sencillo utilizar mongoimport, una herramienta propia de mongo para importar desde archivos.
Desde la carpeta donde se encuentra el archivo con los datos a cargar ejecutar el siguiente comando:

```
mongoimport -u user -p 123456 --db=db2Final --collection=user --file={name}.json
```

Esto carga los documentos que contiene el archivo en una collecion llamada user y requiere la autenticacion para tal base de datos.

Para importar los datos de los siguientes archivos:

[Para 10 mil registros](https://drive.google.com/open?id=1c6eyyvoXfF0bAFS2GnIAUfaBexTyGSv5).

[Para 100 mil registros](https://drive.google.com/open?id=1HuhLFWIBSSmAfTzcBXtJzFHK7mbc9UvD).

[Para 1 millon de registros](https://drive.google.com/open?id=1jvjELReRilNnYLKHHo7wvzPkTWZ6FCbj).


## Neo4J

Para la carga de datos de Neo4J es nesesario colocar los archivos a cargar dentro de una carpeta llamada "neo4j-creation" que se ubica en la raiz del proyecto. Tal carpeta esta definida como un volumen en la configuracion del docker-compose, por lo que podra ser accedida desde dentro del contenedor. Una vez hecho esto y con la imagen de Neo4J corriendo, desde un navegador podremos acceder a una interfaz grafica en el siguiente link: localhost:7474. 

Alli en la consola para ejecutar los comandos ejecutamos:

```
CREATE CONSTRAINT ON (u:user) ASSERT u.id IS UNIQUE

CREATE CONSTRAINT ON (c:commit) ASSERT c.id IS UNIQUE
```

Estos comando crean las etiquetas para los nodos. 

Para cargar los usuarios:

````
USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM "file:///{file_name}" AS row
CREATE (u:user {id: toInteger(row.id), name: row.name, email:row.email})
````

Para cargar los commits:

```
USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM "file:///{file_name}" AS row
CREATE (c:commit {id: toInteger(row.id), hash: row.hash, message:row.message})
```

Para cargar las relaciones:

```
USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM "file:///{file_name}" AS row
MATCH (u:user {id: toInteger(row.userId)}),(c:commit {id: toInteger(row.commitId)})
CREATE (u)-[:COMMITS]->(c)
CREATE (c)-[:AUTHOR]->(u)
```

El nombre del archivo debe la ruta del archivo partiendo desde dentro de la carpeta "neo4j-creation" anteriormente creada. El uso de `USING PERIODIC COMMIT 500` permite realizar un volcado a disco cada 500 inserciones, esto para evitar que se desborde la memoria. 

Para importar los datos de los siguientes archivos:

[Para 10 mil registros](https://drive.google.com/open?id=1VSB6CFQr-vi7ytegIHf-9iMW_VGEaFc3).

[Para 100 mil registros](https://drive.google.com/open?id=1f8PEESOeFtOAy4vcHFsrQSsD29_j6ry7).

[Para 1 millon de registros](https://drive.google.com/open?id=1QAzjsHtvnhwHqi7gnnzpkGXKy_3Ct4bz).

## Elasticsearch

Para el caso de elasticsearch no es posible importar los datos desde un archivo como en el caso de las anteriores bases de datos. Para la insercion de documentos aleatorios, en el mismo proyecto de elasticsearch se encuentra un script para generarlos e insertarlos, el cual se puede llamar facilmente desde un test creado para tal fin, solo es nesesario especificar la cantidad de registros a crear. 

Esta operacion puede tardar cierto tiempo si se trata de mucha cantidad de registros
