package pe.com.claro.api.marcasespeciales.exception;

public class WSException extends BaseException {
  private static final long serialVersionUID = 1L;

  public WSException(String codError, String msjError, String nombreSP, Exception objException) {
    super(codError, msjError, nombreSP, objException);
  }

}
