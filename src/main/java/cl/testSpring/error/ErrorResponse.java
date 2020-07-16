package cl.testSpring.error;

import java.time.LocalDateTime;

/**
 * Clase con el formato de error de respuesta del servicio
 * 
 * @author wmunoz
 *
 */
public class ErrorResponse {

	private final int code;
	private final String message;


	public ErrorResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ErrorResponse(int code, String message, String error, LocalDateTime timestamp) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}