# GestionDeInventario
## Integrantes:
>Andres Felipe Aldana Moreno
>
>Juan David Martinez Lopez
>
>Santiago Barrera Salamanca
>
## Explicacion del proyecto:
Nuestro proyecto es un software diseñado para simplificar la gestión de inventario de una tienda. Está enfocado en ofrecer una solución eficiente y fácil de usar para mantener un registro preciso de los productos disponibles, así como gestionar sus detalles de manera rápida y sencilla. Es un software de fácil manejo, ideal para usuarios que no tengan un amplio conocimiento en estas tecnologías. Además, nuestro software cuenta con una base de datos que nos permite almacenar toda la información relacionada con los productos.

## Ejecución del proyecto
Para ejecutar nuestro proyecto, es necesario seguir estos pasos:
  - Clonar el repositorio
  - Buscar y ejecutar el archivo InventarioApplication siguiendo esta ruta:
      - src
      - main
      - java
      - InventarioApplication
  - Abrir la aplicacion "Postman"
  - Crear una coleccion y una peticion
  - Utilizar el siguiente mapeo para enviar las peticiones al servidor
     - localhost:8087/api/producto/
  - Utilizar la solicitud que desee siguiendo las indicaciones y logica de negocio del proyecto
  - Revisar la informacion en la base de datos

Ahora que conoces los pasos para ejecutar el proyecto, estás listo para comenzar.

## Cómo levantar la API con HTTPS
Para habilitar HTTPS en tu API, sigue estos pasos:

Generar Certificado: Ejecuta el siguiente comando para crear un certificado auto-firmado:
>keytool -genkeypair -alias myalias -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650

Guarda el archivo keystore.p12 y la contraseña que ingreses.

Configuración de Spring Boot: Agrega las configuraciones SSL en application.properties y coloca keystore.p12 en src/main/resources.

Docker: Asegúrate de que tu Dockerfile copie el keystore.p12 al contenedor.

Levantar API: Despliega la aplicación como de costumbre. Ahora debería estar accesible con HTTPS.
