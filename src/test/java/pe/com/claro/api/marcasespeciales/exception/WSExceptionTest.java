package pe.com.claro.api.marcasespeciales.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.exception.WSException;

public class WSExceptionTest {
	Exception exception = new Exception("fault");
	String mensaje = "Ocurrió un error";
	String code = "1";

	@Test
	public void testConstructor1() {
		WSException baseException = new WSException(code, mensaje, "", exception);
		assertSame(exception, baseException.getObjException());
		assertNotNull(baseException.getCodError());
		assertNotNull(baseException.getMessage());
		assertEquals("Ocurrió un error", baseException.getMsjError());
		assertEquals("1", baseException.getCodError());
	}
}