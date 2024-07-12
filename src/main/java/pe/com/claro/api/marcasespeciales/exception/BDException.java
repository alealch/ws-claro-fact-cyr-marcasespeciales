
package pe.com.claro.api.marcasespeciales.exception;

public class BDException extends BaseException {

  private static final long serialVersionUID = -7482288873992395827L;

  public BDException(String code, String message, String messageDeveloper, Exception exception) {
    super(code, message, messageDeveloper, exception);
  }

}
