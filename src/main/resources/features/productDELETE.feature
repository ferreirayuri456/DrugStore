# language: pt

Funcionalidade:  Testar a metodo DELETE da API.
 
Cenario: Deleta os produtos 
	Dado eu faço a busca '/id'
	E eu mando uma requisicao com o id <id> e apago o registro
	Entao nao deveria mais ver o produto
