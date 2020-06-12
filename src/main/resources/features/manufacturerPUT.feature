# language: pt
@FarmaciaTeste
Funcionalidade: Testar a metodo PUT da API. 

Esquema do Cenário: Atualizar os fabricantes pelo id
	Dado eu perfomo a operacao GET com 'manufacturer/id' 
	E eu realizo uma requisicao com o id <id>, codigo do fabricante<codeManufacturer>, cnpj<cnpj>, nome fantasia<fantasyName> e o país de origem<countryOrigin>
	Entao eu deveria ver os dados do fabricante atualizados  
	
	Exemplos:
	|id |codeManufacturer| cnpj| countryOrigin | fantasyName | 
	| 1 | "1597532"   		 | 12345313 |  "Brazil"     | "DOVE" |