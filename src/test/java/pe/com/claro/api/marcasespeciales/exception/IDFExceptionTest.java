package pe.com.claro.api.marcasespeciales.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.exception.IDFException;
import pe.com.claro.api.marcasespeciales.exception.JMSException;

public class IDFExceptionTest {
	Exception exception = new Exception("fault");

	@Test
	public void testConstructorIDFException() {
		IDFException idfException = new IDFException("1", "Consulta no exitosa", "", exception);

		assertEquals("1", idfException.getCodError());
		assertEquals("Consulta no exitosa", idfException.getMessage());
		assertSame(exception, idfException.getObjException());
		assertNotNull(idfException.getCodError());
	}

	@Test
	public void testConstructorJMSException() {
		JMSException jmsException = new JMSException("1", "Consulta no exitosa", "", exception);

		assertEquals("1", jmsException.getCodError());
		assertEquals("Consulta no exitosa", jmsException.getMessage());
		assertSame(exception, jmsException.getObjException());
		assertNotNull(jmsException.getCodError());
	}
}