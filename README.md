# desafioTecnico

Api de tipo de cambio.. El sistema debe poder realizar un tipo de cambio a un monto donde se deben utilizar el monto, moneda de origen, moneda de destino, monto con el tipo de campo y el tipo de cambio.


Requerimientos funcionales:

• El sistema debe poder realizar un tipo de cambio a un monto donde se deben utilizar el monto, moneda de origen, moneda de destino, monto con el tipo de campo y el
tipo de cambio.

• Debe Permitir el registro, actualización y búsqueda del tipo de cambio.

• Debe tener una autenticación.

• Por cada tipo de cambio realizado, se debe registrar quien hizo la solicitud (auditoría funcional).

Requerimientos no funcionales:

• Lenguaje es Java 11.

• Framework es Spring boot.

• Programación reactiva Webflux.

• Base de datos MariaDb.

• Seguridad a través de JWT.

• Uso de Postman para el consumo de la API.

• Documentar la arquitectura de software

# CURL DE LAS PRUEBAS EN POSTMAN

- Crear un UsuarioAdmin:
  
curl --location --request POST 'http://localhost:8080/auth/createAdmin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "Alexander",
    "email": "al@al.al",
    "password": "123456"
}'

- Crear un User:

curl --location --request POST 'http://localhost:8080/auth/createUser' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "Rodrigo",
    "email": "u@u.u",
    "password": "123456"
}'

- LOGIN:

curl --location --request POST 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "Jerry",
    "password": "123"
}'

- Crear Tipo de Cambio:

curl --location --request POST 'http://localhost:8080/api/tipos-cambio/crear' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2MTIzNiwiZXhwIjoxNzA0NzY0ODM2fQ.OwsKewaNNHxUqbw0fWfPbRguz8EwkQ0RCMCy8w02XaI' \
--header 'Cookie: JSESSIONID=9C7701671AF05C4A6AF05EEB59E1ADE9' \
--data-raw '{ 
    "monto": 600.00, 
    "monedaorigen": "Soles", 
    "monedadestino": "Euros",
    "tipocambio": 6.23 
}'

- Actualizar Tipo de Cambio

curl --location --request PUT 'http://localhost:8080/api/tipos-cambio/actualizar/3' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2NTE4MiwiZXhwIjoxNzA0NzY4NzgyfQ.hmzzkEcewuJSgm7eqAEQ-Yn0q-s5to15iu0o24h00GE' \
--header 'Cookie: JSESSIONID=9C7701671AF05C4A6AF05EEB59E1ADE9' \
--data-raw '{ 
    "monto": 730.00, 
    "monedaorigen": "Soles", 
    "monedadestino": "Pesos",
    "tipocambio": 2.23
}'

- Eliminar un Tipo de Cambio por ID

curl --location --request DELETE 'http://localhost:8080/api/tipos-cambio/eliminar/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2NTE4MiwiZXhwIjoxNzA0NzY4NzgyfQ.hmzzkEcewuJSgm7eqAEQ-Yn0q-s5to15iu0o24h00GE' \
--data-raw ''

- Listar Por Solicitante

curl --location --request GET 'http://localhost:8080/api/tipos-cambio/listarPorSolicitante' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2NTE4MiwiZXhwIjoxNzA0NzY4NzgyfQ.hmzzkEcewuJSgm7eqAEQ-Yn0q-s5to15iu0o24h00GE' \
--data-raw ''

- Listar Todos tipos de cambio
  
curl --location --request GET 'http://localhost:8080/api/tipos-cambio/listar' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2NTE4MiwiZXhwIjoxNzA0NzY4NzgyfQ.hmzzkEcewuJSgm7eqAEQ-Yn0q-s5to15iu0o24h00GE' \
--data-raw ''

- Listar Por ID

curl --location --request GET 'http://localhost:8080/api/tipos-cambio/listarId/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXJyeSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTcwNDc2NTE4MiwiZXhwIjoxNzA0NzY4NzgyfQ.hmzzkEcewuJSgm7eqAEQ-Yn0q-s5to15iu0o24h00GE' \
--data-raw ''








