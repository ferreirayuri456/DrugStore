package br.com.example.farmacia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrugstoreError {

	private final String field;
	private final String message;
	private final Object parameter;

}
