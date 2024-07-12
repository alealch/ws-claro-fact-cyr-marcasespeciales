package pe.com.claro.api.marcasespeciales.exception;

public class JMSException extends BaseException {

  private static final long serialVersionUID = 1L;

  public JMSException(String code, String message, String messageDeveloper, Exception exception) {
    super(code, message, messageDeveloper, exception);
  }
}
