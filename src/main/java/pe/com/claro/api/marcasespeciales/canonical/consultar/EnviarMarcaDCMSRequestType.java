package pe.com.claro.api.marcasespeciales.canonical.consultar;

import java.util.List;

import pe.com.claro.api.marcasespeciales.canonical.comun.ListaOpcionalType;

public class EnviarMarcaDCMSRequestType {

  private String entidadId;
  private String sistemaOrigen;
  private String codigoMarca;
  private String valorMarca;
  private List<ListaOpcionalType> listaOpcional;

  public List<ListaOpcionalType> getListaOpcional() {
    return listaOpcional;
  }

  public void setListaOpcional(List<ListaOpcionalType> listaOpcional) {
    this.listaOpcional = listaOpcional;
  }

  public String getEntidadId() {
    return entidadId;
  }

  public void setEntidadId(String entidadId) {
    this.entidadId = entidadId;
  }

  public String getSistemaOrigen() {
    return sistemaOrigen;
  }

  public void setSistemaOrigen(String sistemaOrigen) {
    this.sistemaOrigen = sistemaOrigen;
  }

  public String getCodigoMarca() {
    return codigoMarca;
  }

  public void setCodigoMarca(String codigoMarca) {
    this.codigoMarca = codigoMarca;
  }

  public String getValorMarca() {
    return valorMarca;
  }

  public void setValorMarca(String valorMarca) {
    this.valorMarca = valorMarca;
  }

}