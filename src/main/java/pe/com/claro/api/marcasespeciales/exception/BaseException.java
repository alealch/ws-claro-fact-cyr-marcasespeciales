package pe.com.claro.api.marcasespeciales.exception;

public class BaseException extends Exception {

  private static final long serialVersionUID = 2141005611296085858L;

  public final Exception objException;
  public final String codError;
  public final String msjError;
  public final String nombreSP;

  public Exception getObjException() {
    return objException;
  }

  public String getCodError() {
    return codError;
  }

  public String getMsjError() {
    return msjError;
  }

  public String getNombreSP() {
    return nombreSP;
  }

  public BaseException(String codError, String msjError, String nombreSP, Exception objException) {
    super(msjError);
    this.codError = codError;
    this.msjError = msjError;
    this.nombreSP = nombreSP;
    this.objException = objException;
  }

}