package br.com.example.farmacia.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManufacturerNotFoundException(Integer id) {
		super ("ID do fabricante não foi encontrado: " + id);
	}
}
