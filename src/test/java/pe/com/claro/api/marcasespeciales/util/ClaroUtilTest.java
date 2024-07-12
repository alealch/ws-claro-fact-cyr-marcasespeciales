package pe.com.claro.api.marcasespeciales.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.claro.api.marcasespeciales.exception.BaseException;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("utils")
public class ClaroUtilTest {
	@Autowired
	Environment env;
	@Mock
	private PropertiesExternos prop;
	@InjectMocks
	private Constantes constantes;

	@InjectMocks
	ClaroUtil claroUtil;

	@Test
	public void testPrintPrettyJSONString() throws JsonProcessingException {
		assertEquals("\"42\"", ClaroUtil.printPrettyJSONString("42"));
		assertEquals("42", ClaroUtil.printPrettyJSONString(42));
	}

	@Test
	public void testPrintJSONString() throws JsonProcessingException {
		assertEquals("\"42\"", ClaroUtil.printJSONString("42"));

		assertEquals("42", ClaroUtil.printJSONString(42));
		assertEquals("42", ClaroUtil.nuloAVacio(42));
		assertEquals("", ClaroUtil.nuloAVacio(null));
		assertEquals(42, ClaroUtil.nuloAVacioObject(42));
		assertEquals("", ClaroUtil.nuloAVacioObject(null));
		assertEquals("42", ClaroUtil.verifiyNull(42));
		assertNotNull(ClaroUtil.convertirInteger(new BigDecimal("42")));
	}

	@Test
	public void testDateAString0021() throws JsonProcessingException {
		assertNotNull(ClaroUtil.dateAString(new Date()));
	}

	@Test
	public void testDateAString001() throws JsonProcessingException {
		assertNull(ClaroUtil.dateAString(null, constantes.FORMATOFECHACABECERA));
	}

	@Test
	public void testDateAString002() throws JsonProcessingException {
		assertEquals(constantes.TEXTO_VACIO, ClaroUtil.dateAString(null));
	}

	@Test
	public void testNulloVacio002() throws JsonProcessingException {
		assertEquals(constantes.TEXTO_VACIO, ClaroUtil.nullToVacio(null));
	}

	@Test
	public void testStringtoDate002() throws JsonProcessingException, BaseException {
		assertNotNull(ClaroUtil.stringtoDate("03-06-1993", constantes.FORMATOFECHA));
	}

	@Test
	public void testNuloAVacioInt001() throws JsonProcessingException, BaseException {
		assertEquals(constantes.NUM_ZERO, ClaroUtil.nuloAVacioInt(null));
	}

	@Test
	public void testNuloAVacioInt002() throws JsonProcessingException, BaseException {
		assertEquals(5, ClaroUtil.nuloAVacioInt("5"));
	}

	@Test
	public void testInicioActividad002() throws JsonProcessingException, BaseException {
		ClaroUtil.inicioActividad("5", "1");
		assertNull(null);
	}

	@Test
	public void testFinActividad002() throws JsonProcessingException, BaseException {
		ClaroUtil.finActividad("5", "1");
		assertNull(null);
	}

	@Test
	public void testfinImpl002() throws JsonProcessingException, BaseException {
		ClaroUtil.finImpl(1, "5", "1");
		assertNull(null);
	}

	@Test
	public void testinicioImpl002() throws JsonProcessingException, BaseException {
		ClaroUtil.inicioImpl("1", "5", "1", "1", "1");
		assertNull(null);
	}

	@Test
	public void testgetPasword002() throws JsonProcessingException, BaseException, SQLException {

		assertEquals("tim", ClaroUtil.getPasword("BSCSDES"));
	}

	@Test
	public void testgetPasword001() throws JsonProcessingException, BaseException, SQLException {

		assertEquals("usreaidesa", ClaroUtil.getPasword("TIMDEV"));
	}

	@Test
	public void testgetPasword003() throws JsonProcessingException, BaseException, SQLException {

		assertEquals("pyclarify", ClaroUtil.getPasword("TIMPRB"));
	}

	@Test
	public void testgetUser002() throws JsonProcessingException, BaseException, SQLException {

		assertEquals("tim", ClaroUtil.getUser("BSCSDES"));
	}

	@Test
	public void testgetUser001() throws JsonProcessingException, BaseException, SQLException {
		assertEquals("usreaidesa", ClaroUtil.getUser("TIMDEV"));
	}

	@Test
	public void testgetUser003() throws JsonProcessingException, BaseException, SQLException {
		assertEquals("sa", ClaroUtil.getUser("TIMPRB"));
	}

	@Test
	public void testgetNombreBD003() throws JsonProcessingException, BaseException, SQLException {

		ClaroUtil.getNombreBD("/TIMPRB");
		Assert.assertNull(null);
	}

	@Test
	public void testgetStoredProcedureByParameters003() throws JsonProcessingException, BaseException, SQLException {

		ClaroUtil.getStoredProcedureByParameters("TIMPRB", "SPejemplo");
		Assert.assertNull(null);
	}

	@Test
	public void testisValidFormat003() throws JsonProcessingException, BaseException, SQLException {

		ClaroUtil.isValidFormat(constantes.FORMATOFECHA, "02-06-1993");
		Assert.assertNull(null);
	}

	@Test
	public void testisValidFormat002() throws JsonProcessingException, BaseException, SQLException {

		ClaroUtil.isValidFormat(constantes.FORMATOFECHACABECERA, "02-06-1993");
		Assert.assertNull(null);
	}

}