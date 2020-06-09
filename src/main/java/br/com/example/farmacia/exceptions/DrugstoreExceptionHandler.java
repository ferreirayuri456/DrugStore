package br.com.example.farmacia.exceptions;

//@RestControllerAdvice
//public class DrugstoreExceptionHandler extends ResponseEntityExceptionHandler {
//	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		List<DrugstoreError> drugstoreErrors = drugstoreErrors(ex);
//		ErrorResponse errorResponse = getErrorResponse(ex, status, drugstoreErrors);
//		return new ResponseEntity<Object>(errorResponse, status);
//	}

//	private ErrorResponse getErrorResponse(TypeMismatchException ex, HttpStatus status,
//			List<DrugstoreError> errors) {
//		return new ErrorResponse("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(),
//				ex.getLocalizedMessage(), errors);
		
//	}
	
	//private List<DrugstoreError> drugstoreErrors(TypeMismatchException ex){
	//	return ex.getBindingResult().getFieldErrors().stream()
	//			.map(error -> new DrugstoreError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	//			.collect(Collectors.toList());
	//}
//}
