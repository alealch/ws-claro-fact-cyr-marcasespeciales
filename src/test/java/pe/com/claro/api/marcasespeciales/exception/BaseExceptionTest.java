package pe.com.claro.api.marcasespeciales.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.exception.BaseException;

public class BaseExceptionTest {

	Exception exception = new Exception("Ocurrió un error");
	String mensaje = "Ocurrió un error";
	String code = "1";

	@Test
	public void testConstructor1() {
		BaseException baseException = new BaseException(code, mensaje, "", exception);

		assertSame(exception, baseException.getObjException());
		assertNotNull(baseException.getCodError());
		assertNotNull(baseException.getMessage());
		assertEquals("Ocurrió un error", baseException.getMessage());
		assertEquals("1", baseException.getCodError());
		assertEquals("Ocurrió un error", baseException.getMsjError());
	}

	@Test
	public void testConstructor6() {
		BaseException baseException = new BaseException(code, mensaje, "messageDeveloper", exception);

		assertEquals(code, baseException.getCodError());
		assertEquals(mensaje, baseException.getMessage());
		assertEquals("messageDeveloper", baseException.getNombreSP());
		assertSame(exception, baseException.getObjException());
		assertEquals(mensaje, baseException.getMsjError());
	}

}