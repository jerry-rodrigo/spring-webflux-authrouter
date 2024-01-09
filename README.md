![image](https://github.com/jerry-rodrigo/desafioTecnico/assets/59638646/84d18f81-6dec-47ee-8e87-15be4165e042)


# desafioTecnico
Api de tipo de cambio.. El sistema debe poder realizar un tipo de cambio a un monto donde se deben utilizar el monto, moneda de origen, moneda de destino, monto con el tipo de campo y el tipo de cambio.

Despues de levantar el proyecto en la consola de H2 se debe realizar El siguiente Insert
INSERT INTO role(name) VALUES('USER');
INSERT INTO role(name) VALUES('ADMIN');

Comparto las CURL de las pruebas del POSTMAN

#Registrando un usuario USER
curl --location 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \
--data '{
    "username": "Jerry",
    "password": "123456"
}'

#Registrando un usuario ADMIN
curl --location 'http://localhost:8080/api/auth/registerAdm' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \
--data '{
    "username": "Jerry",
    "password": "123456"
}'

#Generando el TOKEN
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \
--data '{
    "username": "Jerry",
    "password": "123456"
}'

#Creamos un tipo de cambio
curl --location 'http://localhost:8080/api/tipos-cambio/crear' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNzAxODI0MzMxLCJleHAiOjE3MDE4MjQ2MzF9.n100akGzHQSYcdRVSibL6l56V9zkFT_UzokGskGx2tXaixG_Hv6TLrfjT6ar6mOnxpyqVTVRY43FAlWieBdmuw' \
--header 'Cookie: JSESSIONID=9C7701671AF05C4A6AF05EEB59E1ADE9' \
--data '{
    "monto": 600.00,
    "monedaOrigen": "Soles",
    "monedaDestino": "Euros",
    "montoConTipo": 123.45,
    "tipoCambio": 6.23
}'

#Para listar la lista de los tipos de cambios
curl --location 'http://localhost:8080/api/tipos-cambio' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKZXJyeSIsImlhdCI6MTcwMTgyMzA0MCwiZXhwIjoxNzAxODIzMzQwfQ.8E3OxeDB_2feTVSyGZMXDnVToHmC5s6vvXgskp2-6DxWGR50nWY_UeV0IqCKP5flrfJvBMtdWCC3vbkHL2v1gw' \
--header 'Cookie: JSESSIONID=1A45F4DA74396A6EF362BCAAEEFDD778' \
--data ''

