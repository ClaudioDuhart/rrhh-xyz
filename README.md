//Version localhost
    //spring.datasource.url=jdbc:mysql://localhost:3306/RRHH?createDatabaseIfNotExist=true&useSSL=true
    //spring.datasource.username=root
    //spring.datasource.password=

//Version Docker
    //spring.datasource.url=jdbc:mysql://mysql:3306/RRHH?createDatabaseIfNotExist=true&useSSL=true
    //spring.datasource.username=root
    //spring.datasource.password=1234

La primera version es para usar el local y la segunda es para usarlo con docker (que seria la version de produccion), para que funcionen los compose se deben montar las imágenes previamente con docker build -t app . y una imagen de mysql (ese punto hace referencia a la carpeta en donde esta el dockerfile), lo mismo para el front

El software utiliza hibernate como orm por detrás, por lo que al iniciar la aplicación, si detecta el puerto de 3306, generara la bdd y todas las tablas
