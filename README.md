# teste-sicredi

Teste para vaga de backend no Sicredi.

A linguagem utilizada foi Java com Spring Boot. O banco foi o MongoDB e também foi utilizado o Kafka para envio de mensagens.

Métodos API Rest

- ASSOCIADOS

Buscar Todos
GET /api/associado/get-all

Buscar um associado utilizando o ID
GET /api/associado/get?id=

Salvar um associado
POST /api/associado/store?name=&cpf=

Deletar um associado
DELETE /api/associado/delete?id=


- PAUTAS

Buscar Todos
GET /api/pauta/get-all

Buscar uma pauta utilizando o ID
GET /api/pauta/get?id=

Salvar uma pauta
POST /api/pauta/store?name=

Deletar uma pauta
DELETE /api/pauta/delete?id=

Ativar pauta
PATCH /api/pauta/activate?id=&duration


- VOTOS

Votar
POST /api/voto/store?idAssociado=&idPauta=&vote

Checar Resultado
GET /api/voto/result?id=
