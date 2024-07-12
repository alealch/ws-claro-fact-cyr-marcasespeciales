package pe.com.claro.api.marcasespeciales.repository.bean;

import java.io.Serializable;
import java.util.Date;

public class BeanCambioTitularidad implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String codigo;
  private String valor;
  private String entidadId;
  private String facturador;
  private String entidad;
  private Date fecVigInicio;
  private Date fecVigFin;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public String getEntidad() {
    return entidad;
  }

  public void setEntidad(String entidad) {
    this.entidad = entidad;
  }

  public Date getFecVigInicio() {
    return fecVigInicio;
  }

  public void setFecVigInicio(Date fecVigInicio) {
    this.fecVigInicio = fecVigInicio;
  }

  public Date getFecVigFin() {
    return fecVigFin;
  }

  public void setFecVigFin(Date fecVigFin) {
    this.fecVigFin = fecVigFin;
  }

}