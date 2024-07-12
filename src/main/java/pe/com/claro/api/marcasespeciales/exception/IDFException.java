
package pe.com.claro.api.marcasespeciales.exception;

public class IDFException extends BaseException {

  private static final long serialVersionUID = -7482288873992395827L;

  public IDFException(String code, String message, String messageDeveloper, Exception exception) {
    super(code, message, messageDeveloper, exception);
  }

}
