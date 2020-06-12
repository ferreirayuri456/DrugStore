# language: pt

Funcionalidade:  Testar a metodo DELETE da API.
 
Cenario: Deleta os fabricantes 
	Dado eu faço a busca 'manufacturer/id'
	E eu realizo uma requisicao com o id <id> e apago o registro
	Entao nao deveria mais ver o fabricante
