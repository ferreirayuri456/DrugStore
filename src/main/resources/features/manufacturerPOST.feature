# language: pt
@FarmaciaTeste
Funcionalidade: Testar a metodo POST da API. 

Esquema do Cenário: Como um cliente, eu quero criar um novo fabricante
	Dado eu posso criar um novo fabricante
	E eu realizo uma requisicao com o id <id>, codigo do fabricante<codeManufacturer>, cnpj<cnpj>, nome fantasia<fantasyName> e o país de origem<countryOrigin>
	Entao eu deveria ver o novo fabricante criado
	
	Exemplos:
	|id |codeManufacturer| cnpj| countryOrigin | fantasyName | 
	| 1 | 1597532   		 | 12345313 |  "Brazil"     | "DOVE" |