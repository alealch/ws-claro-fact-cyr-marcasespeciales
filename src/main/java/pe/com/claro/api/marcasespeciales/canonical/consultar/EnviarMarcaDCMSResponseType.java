package pe.com.claro.api.marcasespeciales.canonical.consultar;

import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseStatusType;

public class EnviarMarcaDCMSResponseType {

  private ResponseStatusType responseStatus;
  private ResponseData responseData;

  public ResponseData getResponseData() {
    return responseData;
  }

  public void setResponseData(ResponseData responseData) {
    this.responseData = responseData;
  }

  public ResponseStatusType getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(ResponseStatusType responseStatus) {
    this.responseStatus = responseStatus;
  }

}