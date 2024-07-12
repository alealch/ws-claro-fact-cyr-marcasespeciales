package pe.com.claro.api.marcasespeciales.service;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.canonical.comun.ListaOpcionalType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequest;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequestType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponse;
import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.exception.IDFException;
import pe.com.claro.api.marcasespeciales.exception.WSException;
import pe.com.claro.api.marcasespeciales.integration.SetPackageClient;
import pe.com.claro.api.marcasespeciales.integration.bean.PackageResponse;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.repository.SiopDbDao;
import pe.com.claro.api.marcasespeciales.repository.bean.BeanCambioTitularidad;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class EnviarMarcaDIMServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(EnviarMarcaDIMServiceTest.class);
	@Autowired
	Environment env;

	@Mock
	private PropertiesExternos prop;

	@Mock
	private SiopDbDao siopDbDao;
	@Mock
	private SetPackageClient setPackageClient;
	@InjectMocks
	private EnviarMarcaDIMService enviarMarcaDIMService;

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
		prop.codigoIdf1 = env.getProperty("codigo.idf1");
		prop.mensajeIdf1 = env.getProperty("mensaje.idf1");
		prop.codigoIdf2 = env.getProperty("codigo.idf2");
		prop.mensajeIdf2 = env.getProperty("mensaje.idf2");
		prop.bdSiopName = env.getProperty("bd.siop.name");
		prop.bdSiopOwner = env.getProperty("bd.siop.owner");
		prop.bdSiopJndi = env.getProperty("spring.datasource.jndi.bd.siop");
		prop.bdSiopPkgMarcasEspeciales = env.getProperty("bd.siop.pkg.notificacion.cobranza");
		prop.bdSiopSpMarcasEspecialesIndiv = env.getProperty("bd.siop.sp.marcas.especiales.indiv");
		prop.bdSiopSpMarcasEspecialesIndivConexionTimeout = env
				.getProperty("bd.siop.sp.marcas.especiales.indiv.conexion.timeout");
		prop.bdSiopSpMarcasEspecialesIndivEjecucionTimeout = env
				.getProperty("bd.siop.sp.marcas.especiales.indiv.ejecucion.timeout");
		prop.registrarReposicionReintentos = env.getProperty("registrar.programacion.intentos");
		prop.formatoFechaVig = env.getProperty("formato.fecha.vigencia");
		prop.estadoEnviado = env.getProperty("dim.estado.enviado");
		prop.estadoError = env.getProperty("dim.estado.error");
		prop.mensajeTimeout = env.getProperty("dim.mensaje.timeout");
		prop.mensajeDisponibilidad = env.getProperty("dim.mensaje.disponibilidad");
		prop.mensajeExito = env.getProperty("dim.mensaje.exito");
		prop.dimAction = env.getProperty("dim.action");
		prop.dimMe = env.getProperty("dim.me");
		prop.dimEntity = env.getProperty("dim.entity");
		// prop.bdTimEaiQueryUpdate=env.getProperty("bd.timeai.query.update");
		// prop.bdTimEaiUpdateConexionTimeout=env.getProperty("bd.timeai.query.update.conexion.timeout");
		// prop.bdTimEaiUpdateEjecucionTimeout=env.getProperty("bd.timeai.query.update.ejecucion.timeout");
		// prop.estado19=env.getProperty("estado.diecinueve");
		// prop.estado20=env.getProperty("estado.veinte");
		// prop.estado19=env.getProperty("estado.diecinueve");
		// prop.estado20=env.getProperty("estado.veinte");
		// prop.estado19=env.getProperty("estado.diecinueve");
		// prop.estado20=env.getProperty("estado.veinte");
		// prop.estado19=env.getProperty("estado.diecinueve");
		// prop.estado20=env.getProperty("estado.veinte");
	}

	@Test
	public void testconsultarReglaSUCCESS000() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS000");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS000");
	}

	@Test
	public void testconsultarReglaSUCCESS001() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS001");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS001");
	}

	@Test
	public void testconsultarReglaSUCCESS002() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS002");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest02();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);

		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS002");
	}

	@Test
	public void testconsultarReglaSUCCESS003() throws BDException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS003");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest3();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);

		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS003");
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest3() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);
		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	@Test
	public void testconsultarReglaSUCCESS004() throws BDException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS004");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest4();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS004");
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest4() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);

		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	@Test
	public void testconsultarReglaSUCCESS005() throws BDException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS005");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest5();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);

		assertNotNull(

				obtenerDatosPortabilidadResponse.getEnviarMarcaDCMSResponse().getResponseStatus().getCodigoRespuesta());
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS005");
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest5() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);
		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	@Test
	public void testconsultarReglaSUCCESS006() throws BDException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS006");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest6();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);

		assertNotNull(

				obtenerDatosPortabilidadResponse.getEnviarMarcaDCMSResponse().getResponseStatus().getCodigoRespuesta());
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS006");
	}

	@Test
	public void testconsultarReglaSUCCESS007() throws BDException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS007");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest7();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);

		assertNotNull(

				obtenerDatosPortabilidadResponse.getEnviarMarcaDCMSResponse().getResponseStatus().getCodigoRespuesta());
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS007");
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest7() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);

		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest6() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);

		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	@Test
	public void testconsultarReglaSUCCESS008() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS008");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS008");
	}

	@Test
	public void testconsultarReglaSUCCESS009() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS009");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS009");
	}

	@Test
	public void testconsultarReglaSUCCESS014() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS014");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS014");
	}

	@Test
	public void testconsultarReglaSUCCESS010() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS010");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		SpMarcasEspecialIndivResponse titularidadResponse = new SpMarcasEspecialIndivResponse();
		titularidadResponse.setErrorCode("0");
		titularidadResponse.setErrorMsg("SE ENCONTRARON 0 REGISTROS");
		List<BeanCambioTitularidad> listaPres = new ArrayList<BeanCambioTitularidad>();
		titularidadResponse.setListaPre(listaPres);
		Mockito.when(siopDbDao.marcasEspecialesIndv(Mockito.any(String.class),
				Mockito.any(SpMarcasEspecialIndivRequest.class))).thenReturn(titularidadResponse);

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS010");
	}

	@Test
	public void testconsultarReglaSUCCESS011() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS011");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		SpMarcasEspecialIndivResponse titularidadResponse = new SpMarcasEspecialIndivResponse();
		titularidadResponse.setErrorCode("0");
		titularidadResponse.setErrorMsg("SE ENCONTRARON 0 REGISTROS");
		List<BeanCambioTitularidad> listaPres = new ArrayList<BeanCambioTitularidad>();
		BeanCambioTitularidad cambioTitularidad = new BeanCambioTitularidad();
		cambioTitularidad.setId("");
		cambioTitularidad.setCodigo("");
		cambioTitularidad.setValor("");
		cambioTitularidad.setEntidadId("");
		cambioTitularidad.setFacturador("");
		cambioTitularidad.setEntidad("");
		cambioTitularidad.setFecVigInicio(new Date());
		cambioTitularidad.setFecVigFin(new Date());
		listaPres.add(cambioTitularidad);
		titularidadResponse.setListaPre(listaPres);
		Mockito.when(siopDbDao.marcasEspecialesIndv(Mockito.any(String.class),
				Mockito.any(SpMarcasEspecialIndivRequest.class))).thenReturn(titularidadResponse);

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS011");
	}

	@Test
	public void testconsultarReglaSUCCESS013() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS013");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		SpMarcasEspecialIndivResponse titularidadResponse = new SpMarcasEspecialIndivResponse();
		titularidadResponse.setErrorCode("0");
		titularidadResponse.setErrorMsg("SE ENCONTRARON 0 REGISTROS");
		List<BeanCambioTitularidad> listaPres = new ArrayList<BeanCambioTitularidad>();
		BeanCambioTitularidad cambioTitularidad = new BeanCambioTitularidad();
		cambioTitularidad.setId("");
		cambioTitularidad.setCodigo("");
		cambioTitularidad.setValor("");
		cambioTitularidad.setEntidadId("");
		cambioTitularidad.setFacturador("");
		cambioTitularidad.setEntidad("");
		cambioTitularidad.setFecVigInicio(new Date());
		cambioTitularidad.setFecVigFin(new Date());
		listaPres.add(cambioTitularidad);
		BeanCambioTitularidad cambioTitularidad2 = new BeanCambioTitularidad();
		cambioTitularidad2.setId("");
		cambioTitularidad2.setCodigo("");
		cambioTitularidad2.setValor("");
		cambioTitularidad2.setEntidadId("");
		cambioTitularidad2.setFacturador("");
		cambioTitularidad2.setEntidad("");
		cambioTitularidad2.setFecVigInicio(new Date());
		cambioTitularidad2.setFecVigFin(new Date());
		listaPres.add(cambioTitularidad2);
		titularidadResponse.setListaPre(listaPres);
		Mockito.when(siopDbDao.marcasEspecialesIndv(Mockito.any(String.class),
				Mockito.any(SpMarcasEspecialIndivRequest.class))).thenReturn(titularidadResponse);

		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS013");
	}

	@Test
	public void testconsultarReglaSUCCESS012() throws BDException, WSException, IDFException, JsonProcessingException {
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO testconsultarReglaSUCCESS012");
		EnviarMarcaDCMSRequest request = this.buildObtenerDatosReposicionRequest();
		HeaderRequest headerRequest = this.buildHeaderRequest();

		SpMarcasEspecialIndivResponse titularidadResponse = new SpMarcasEspecialIndivResponse();
		titularidadResponse.setErrorCode("1");
		titularidadResponse.setErrorMsg("No existe");
		List<BeanCambioTitularidad> listaPres = new ArrayList<BeanCambioTitularidad>();
		titularidadResponse.setListaPre(listaPres);
		Mockito.when(siopDbDao.marcasEspecialesIndv(Mockito.any(String.class),
				Mockito.any(SpMarcasEspecialIndivRequest.class))).thenReturn(titularidadResponse);
		PackageResponse setpackageResponse = new PackageResponse();
		Mockito.when(setPackageClient.setPackage(Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(HeaderRequest.class))).thenReturn(setpackageResponse);
		EnviarMarcaDCMSResponse obtenerDatosPortabilidadResponse = enviarMarcaDIMService.enviarMarcaDIM(headerRequest,
				request);
		assertNotNull(obtenerDatosPortabilidadResponse);
		logger.info(Constantes.SEPARADOR_DOS_LLAVES, "FIN testconsultarReglaSUCCESS012");
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);
		enviarMarcaDCMSRequestType.setCodigoMarca("SubTipoCorporativo");
		enviarMarcaDCMSRequestType.setEntidadId("11111");
		enviarMarcaDCMSRequestType.setSistemaOrigen("EAI");
		enviarMarcaDCMSRequestType.setValorMarca("AD");
		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	private EnviarMarcaDCMSRequest buildObtenerDatosReposicionRequest02() {
		EnviarMarcaDCMSRequest enviarMarcaDCMSRequest = new EnviarMarcaDCMSRequest();
		EnviarMarcaDCMSRequestType enviarMarcaDCMSRequestType = new EnviarMarcaDCMSRequestType();
		List<ListaOpcionalType> listaOpcional = new ArrayList<>();
		ListaOpcionalType listaOp = new ListaOpcionalType();
		listaOp.setCampo("COD_TIPODOC");
		listaOp.setValor("01");
		listaOpcional.add(listaOp);

		enviarMarcaDCMSRequestType.setListaOpcional(listaOpcional);
		enviarMarcaDCMSRequest.setEnviarMarcaDCMSRequest(enviarMarcaDCMSRequestType);
		return enviarMarcaDCMSRequest;
	}

	private HeaderRequest buildHeaderRequest() {
		HeaderRequest headerRequest = new HeaderRequest();
		headerRequest.setIdTransaccion("123456789");
		headerRequest.setAccept("application/json");
		headerRequest.setMsgid("12312323");
		headerRequest.setTimestamp("2022-12-13T18:00:00Z");
		headerRequest.setUserId("EAI");
		return headerRequest;
	}

}