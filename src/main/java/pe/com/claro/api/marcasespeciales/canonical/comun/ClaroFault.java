package pe.com.claro.api.marcasespeciales.canonical.comun;

public class ClaroFault {

  private String idAudit;
  private String codeError;
  private String descriptionError;
  private String locationError;
  private String date;
  private String originError;

  public String getIdAudit() {
    return idAudit;
  }

  public void setIdAudit(String idAudit) {
    this.idAudit = idAudit;
  }

  public String getCodeError() {
    return codeError;
  }

  public void setCodeError(String codeError) {
    this.codeError = codeError;
  }

  public String getDescriptionError() {
    return descriptionError;
  }

  public void setDescriptionError(String descriptionError) {
    this.descriptionError = descriptionError;
  }

  public String getLocationError() {
    return locationError;
  }

  public void setLocationError(String locationError) {
    this.locationError = locationError;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getOriginError() {
    return originError;
  }

  public void setOriginError(String originError) {
    this.originError = originError;
  }

}
