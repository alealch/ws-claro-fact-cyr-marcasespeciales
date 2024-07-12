package pe.com.claro.api.marcasespeciales.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.exception.BDException;

public class BDExceptionTest {

	Exception exception = new Exception("fault");

	@Test
	public void testConstructorDBException() {
		BDException bdException = new BDException("1", "Eror de timeout", "", exception);

		assertEquals("1", bdException.getCodError());
		assertEquals("Eror de timeout", bdException.getMessage());
		assertSame(exception, bdException.getObjException());
		assertNotNull(bdException.getCodError());
	}

}