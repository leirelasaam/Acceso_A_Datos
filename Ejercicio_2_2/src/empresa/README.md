# 1. Conexión a la BBDD
Para la conexión a la base de datos, es necesario cambiar las constantes almacenadas en la clase *src/empresa/utils/BUtils.java*.
Es especialmente importante tener en cuenta el **nombre de la base de datos** y el **puerto** (que por defecto está en *3305*, lo cual, no es habitual).

Además, se adjunta la librería utilizada de mysql (*mysql-connector-java-8.0.25.jar*) que es importante importarla al proyecto.

# 2. Procedimientos
Los procedimientos se han incluído en el archivo *src/empresa/procedimientos.sql*.

Estos procedimientos contienen las mismas queries que las propias consultas.