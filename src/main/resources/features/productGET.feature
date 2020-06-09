# language: pt
@ProdutoTeste
Funcionalidade: Testar a metodo GET da API. 

Cenario: Lista os produtos pelo nome fantasia 
	Dado eu perfomo a operacao GET com '/' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver o nome fantasia igual a "DOVE" 
	
Cenario: Lista os produtos pelo codigo do produto 
	Dado eu perfomo a operacao GET com '/' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver o codigo do produto igual a "1" 
	
Cenario: Lista os produtos pelo nome do produto
	Dado eu perfomo a operacao GET com '/' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver o nome do produto igual a "DOVE" 
	
Cenario: Lista os produtos pelo fabricante 
	Dado eu perfomo a operacao GET com '/' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver o nome fantasia igual a "DOVE" 
	
Cenario: Lista os produtos pelo preco 
	Dado eu perfomo a operacao GET com '/' 
	E eu perfomo a operacao GET pelo numero '1' 
	Entao eu deveria ver o nome fantasia igual a "123" 
	
	
	
	
	
	
	
	
	