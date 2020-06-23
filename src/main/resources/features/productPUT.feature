# language: pt
@FarmaciaTeste
Funcionalidade: Testar a metodo PUT da API. 

Esquema do Cenário: Atualizar os produtos pelo id
	Dado eu perfomo a operacao GET com '/id' 
	E eu busco pelo  id <id> e atualizo o codigo do produto <codeProduct>, o nome do produto <nameProduct>, o nome fantasia <fantasyName>, o fabricante <manufacturer> e o preco <price>
	Entao eu deveria ver os dados atualizados  

	Exemplos:
	|id |codeProduct| nameProduct| fantasyName | manufacturer | price |
	| 1 | 1597532   | 	"DOVE"   |  "DOVE"     | "DOVE"       | 15    |