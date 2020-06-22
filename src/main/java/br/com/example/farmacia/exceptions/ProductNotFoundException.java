package br.com.example.farmacia.exceptions;

public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Integer id) {
		super("ID do produto não foi encontrado: " + id);
	}
}
