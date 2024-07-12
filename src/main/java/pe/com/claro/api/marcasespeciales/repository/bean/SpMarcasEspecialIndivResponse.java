package pe.com.claro.api.marcasespeciales.repository.bean;

import java.util.List;

public class SpMarcasEspecialIndivResponse {
  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String errorMsg;
  private List<BeanCambioTitularidad> listaPre;

  public List<BeanCambioTitularidad> getListaPre() {
    return this.listaPre;
  }

  public void setListaPre(List<BeanCambioTitularidad> listaPre) {
    this.listaPre = listaPre;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}