package pe.com.claro.api.marcasespeciales.repository.bean;

import java.io.Serializable;

public class SpMarcasEspecialIndivRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  private String codigo;
  private String valor;
  private String entidadId;
  private String facturador;
  private String usuarioMod;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public String getEntidadId() {
    return entidadId;
  }

  public void setEntidadId(String entidadId) {
    this.entidadId = entidadId;
  }

  public String getFacturador() {
    return facturador;
  }

  public void setFacturador(String facturador) {
    this.facturador = facturador;
  }

  public String getUsuarioMod() {
    return usuarioMod;
  }

  public void setUsuarioMod(String usuarioMod) {
    this.usuarioMod = usuarioMod;
  }

}