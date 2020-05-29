package br.com.example.farmacia.constants;

public enum ErrorCodes {
	INTERNAL_SERVER_ERROR("Internal server error"),
	INVALID_REQUEST("Invalid request"),
	VALIDATION_FAILED("Validation failed"),
	NOT_FOUND("Not found"),
	ID_NOT_FOUND("Id not found");
	
    private final String message;

    ErrorCodes(String message) {
    	this.message = message;
    }

	public String getMessage() {
    	return message;
	}
}
