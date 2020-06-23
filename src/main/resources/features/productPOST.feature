# language: pt
@FarmaciaTeste
Funcionalidade: Testar a metodo POST da API. 

Esquema do Cenário: Como um cliente, eu quero criar um novo produto 
	Dado eu posso criar um novo produto
	E eu mando uma requisicao com o id <id>, codigo do produto <codeProduct>, o nome do produto <nameProduct>, o nome fantasia <fantasyName>, o fabricante <manufacturer> e o preco <price>
	Entao eu deveria ver o novo produto criado
	
	Exemplos:
	|id |codeProduct| nameProduct| fantasyName | manufacturer | price |
	| 1 | 1597532   | 	"DOVE"   |  "DOVE"     | "DOVE"       | 15    |