package pe.com.claro.api.marcasespeciales.canonical.consultar;

import java.util.List;

import pe.com.claro.api.marcasespeciales.canonical.comun.ListaOpcionalType;

public class ResponseData {

  private List<ListaOpcionalType> listaOpcional;

  public List<ListaOpcionalType> getListaOpcional() {
    return listaOpcional;
  }

  public void setListaOpcional(List<ListaOpcionalType> listaOpcional) {
    this.listaOpcional = listaOpcional;
  }

}