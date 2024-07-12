package pe.com.claro.api.marcasespeciales.integration.bean;

public class ResponseStatus {

	private String idTransaccion;
	private String codigoRespuesta;
	private String descripcionRespuesta;
	private String status;
	private String ubicacionError;

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}

	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUbicacionError() {
		return ubicacionError;
	}

	public void setUbicacionError(String ubicacionError) {
		this.ubicacionError = ubicacionError;
	}

}