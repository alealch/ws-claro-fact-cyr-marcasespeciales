package pe.com.claro.api.marcasespeciales.canonical.consultar;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;

public class EnviarMarcaDCMSResponse {

  private EnviarMarcaDCMSResponseType enviarMarcaDCMSResponse;
  private ClaroFault claroFault;

  public ClaroFault getClaroFault() {
    return claroFault;
  }

  public void setClaroFault(ClaroFault claroFault) {
    this.claroFault = claroFault;
  }

  public EnviarMarcaDCMSResponseType getEnviarMarcaDCMSResponse() {
    return enviarMarcaDCMSResponse;
  }

  public void setEnviarMarcaDCMSResponse(EnviarMarcaDCMSResponseType enviarMarcaDCMSResponse) {
    this.enviarMarcaDCMSResponse = enviarMarcaDCMSResponse;
  }

}