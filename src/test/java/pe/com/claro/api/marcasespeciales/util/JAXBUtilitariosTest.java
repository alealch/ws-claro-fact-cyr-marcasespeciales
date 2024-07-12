package pe.com.claro.api.marcasespeciales.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class JAXBUtilitariosTest {

	@Test
	public void testGetXmlTextFromJaxB() {
		assertNotNull(JAXBUtilitarios.getXmlTextFromJaxB("Obj Jax B"));
		assertNull(JAXBUtilitarios.getXmlTextFromJaxB(null));
	}

	@Test
	public void testAnyObjectToXmlText() {
		assertEquals("<java.lang.String>42</java.lang.String>", JAXBUtilitarios.anyObjectToXmlText("42"));

		assertNull(JAXBUtilitarios.anyObjectToXmlText(null));
	}

	@Test
	public void test_objectToJson_E001() {
		String msjTx = "idTrx";
		Object obj = new Object();
		assertNotNull(JAXBUtilitarios.objectToJson(msjTx, obj));
	}

}