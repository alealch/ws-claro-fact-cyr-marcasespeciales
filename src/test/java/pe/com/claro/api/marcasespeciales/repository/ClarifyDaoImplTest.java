package pe.com.claro.api.marcasespeciales.repository;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.exception.BaseException;
import pe.com.claro.api.marcasespeciales.integration.config.JdbcConfig;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.repository.bean.BeanCambioTitularidad;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivResponse;
import pe.com.claro.api.marcasespeciales.repository.impl.SiopDaoImpl;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class ClarifyDaoImplTest {

	@Autowired
	Environment env;

	@Mock
	private PropertiesExternos prop;
	@Mock
	private Connection connection;
	@Mock
	private DataSource dataSourceSiop;
	@Mock
	private static SimpleJdbcCall simpleJdbcCall;
	@Mock
	private MapSqlParameterSource mapSqlParameterSource;
	@Mock
	private JdbcConfig jdbcConfig;
	@Mock
	private JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	@Value("${spring.datasource.jndi.bd.siop}")
	private String siopJndiName;

	@InjectMocks
	private SiopDaoImpl siopDaoImpl;

	private String trazabilidad = "12345679";

	@Before
	public void setUp() throws Exception {

		prop.codigoIdt1 = env.getProperty("codigo.idt1");
		prop.mensajeIdt1 = env.getProperty("mensaje.idt1");
		prop.codigoIdt2 = env.getProperty("codigo.idt2");
		prop.mensajeIdt2 = env.getProperty("mensaje.idt2");
		prop.codigoIdt3 = env.getProperty("codigo.idt3");
		prop.mensajeIdt3 = env.getProperty("mensaje.idt3");

		prop.codigoIdf0 = env.getProperty("codigo.idf0");
		prop.mensajeIdf0 = env.getProperty("mensaje.idf0");
		prop.bdSiopName = env.getProperty("bd.siop.name");
		prop.bdSiopPkgMarcasEspeciales = env.getProperty("bd.siop.pkg.notificacion.cobranza");
		prop.bdSiopSpMarcasEspecialesIndiv = env.getProperty("bd.siop.sp.nocoss.cartafisica");
		// prop.formatoFechaOrigenBscs = env.getProperty("formato.fecha.origen.bscs");
		prop.bdSiopOwner = env.getProperty("bd.siop.owner");
		prop.bdSiopJndi = env.getProperty("spring.datasource.jndi.bd.siop");
		prop.bdSiopSpMarcasEspecialesIndivConexionTimeout = env
				.getProperty("bd.siop.sp.nocoss.cartafisica.conexion.timeout");
		prop.bdSiopSpMarcasEspecialesIndivEjecucionTimeout = env
				.getProperty("bd.siop.sp.nocoss.cartafisica.ejecucion.timeout");

	}

	@Test
	public void testConsultarLineasActivasPostPago001() throws SQLException, BaseException {
		try {
			Connection connection = mock(Connection.class);
			when(connection.isClosed()).thenReturn(true);
			doNothing().when(connection).close();
			when(this.dataSourceSiop.getConnection()).thenReturn(connection);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("PO_CODRPTA", "0");
			resultMap.put("PO_MENSAJE", "Exito");
			resultMap.put("PO_RESULTADO", BeanCambioTitularidad.class);
			Mockito.when(simpleJdbcCall.execute(Mockito.anyMap())).thenReturn(resultMap);

			// String fechaInicio = "31/03/2023 00:00:00";
			SpMarcasEspecialIndivRequest req = new SpMarcasEspecialIndivRequest();
			req.setCodigo("");
			req.setEntidadId("");
			req.setFacturador("");
			req.setValor("");
			// Date fechainicio = ClaroUtil.stringtoDate(fechaInicio,
			// prop.formatoFechaOrigenBscs);
			SpMarcasEspecialIndivResponse siacssCambioTitularidadResponse = siopDaoImpl
					.marcasEspecialesIndv(trazabilidad, req);
			assertEquals(Constantes.CERO, siacssCambioTitularidadResponse.getErrorCode());
		} catch (BDException e) {
			String ex = e.getMessage();
			Assert.assertNotNull(e);
			assertThat(e, isA(BDException.class));
			org.hamcrest.MatcherAssert.assertThat(ex, ex.contains("Error de disponibilidad"));
		} catch (Exception e) {
			String ex = e.getMessage();
			Assert.assertNotNull(e);
			assertThat(e, isA(Exception.class));
		}
	}

}