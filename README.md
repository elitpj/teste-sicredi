# teste-sicredi

<h1>Teste para vaga de backend no Sicredi.</h1>

<p> linguagem utilizada foi Java com Spring Boot. O banco foi o MongoDB e também foi utilizado o Kafka para envio de mensagens.</p>

<h2>Métodos API Rest</h2>
<br>

<h3>- ASSOCIADOS</h3>

<h4>Buscar Todos</h4>

<h5>GET - /api/associado/get-all</h5>

<h4>Buscar um associado utilizando o ID</h4>

<h5>GET - /api/associado/get?id=</h5>

<h4>Salvar um associado</h4>

<h5>POST - /api/associado/store?name=&cpf=</h5>

<h4>Deletar um associado</h4>

<h5>DELETE - /api/associado/delete?id=</h5>

<br>
<h3>- PAUTAS</h3>

<h4>Buscar Todos</h4>

<h5>GET - /api/pauta/get-all</h5>

<h4>Buscar uma pauta utilizando o ID</h4>

<h5>GET - /api/pauta/get?id=</h5>

<h4>Salvar uma pauta</h4>

<h5>POST - /api/pauta/store?name=</h5>

<h4>Deletar uma pauta</h4>

<h5>DELETE - /api/pauta/delete?id=</h5>

<h4>Ativar pauta</h4>

<h5>PATCH - /api/pauta/activate?id=&duration</h5>

<br>
<h3>- VOTOS</h3>

<h4>Votar</h4>

<h5>POST - /api/voto/store?idAssociado=&idPauta=&vote</h5>

<h4>Checar Resultado</h4>

<h5>GET - /api/voto/result?id=</h5>
