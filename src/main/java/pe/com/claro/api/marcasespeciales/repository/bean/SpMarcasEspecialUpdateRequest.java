package pe.com.claro.api.marcasespeciales.repository.bean;

public class SpMarcasEspecialUpdateRequest {
	private int maesnId;
	private int estado;
	private String errorMensaje;
	private String usuarioMod;

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getErrorMensaje() {
		return errorMensaje;
	}

	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}

	public int getMaesnId() {
		return maesnId;
	}

	public void setMaesnId(int maesnId) {
		this.maesnId = maesnId;
	}

	public String getUsuarioMod() {
		return usuarioMod;
	}

	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}

}