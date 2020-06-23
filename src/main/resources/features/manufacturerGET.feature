# language: pt
@FarmaciaTeste
Funcionalidade: Testar a metodo GET da API. 

Cenário: Lista todos os fabricantes 
	Dado eu perfomo a operacao GET com '/manufacturer' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver os detalhes dos fabricantes