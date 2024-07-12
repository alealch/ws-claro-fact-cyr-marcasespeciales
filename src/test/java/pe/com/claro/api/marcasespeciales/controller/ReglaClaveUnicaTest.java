package pe.com.claro.api.marcasespeciales.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;
import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.canonical.comun.ListaOpcionalType;
import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseStatusType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequest;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequestType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponse;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponseType;
import pe.com.claro.api.marcasespeciales.exception.IDFException;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.service.EnviarMarcaDIMService;
//import pe.com.claro.api.marcasespeciales.service.ProcesarCartasFisicasService;

@ActiveProfiles("resr")
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReglaClaveUnicaTest {
    @Autowired
    Environment env;
    @Mock
    private PropertiesExternos prop;

    private String idTransaccion = "123456789";

    @Mock
    private EnviarMarcaDIMService enviarMarcaDIMService;

    @InjectMocks
    private MarcasEspecialesResource marcasEspecialesResource;

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
        prop.bdSiopPkgMarcasEspeciales = env.getProperty("bd.siop.pkg.marcas.especiales");
        prop.bdSiopSpMarcasEspecialesIndiv = env.getProperty("bd.siop.sp.nocoss.cartafisica");
        prop.bdSiopSpMarcasEspecialesIndivConexionTimeout = env
                .getProperty("bd.siop.sp.nocoss.cartafisica.conexion.timeout");
        prop.bdSiopSpMarcasEspecialesIndivEjecucionTimeout = env
                .getProperty("bd.siop.sp.nocoss.cartafisica.ejecucion.timeout");

    }

    @Test
    public void testObtenerDatosReposicionExito001() throws JsonProcessingException, IDFException {

        String userId = "EAI";
        String msgid = idTransaccion;
        String timestamp = "2022-06-14T18:00:00Z";
        String accept = "application/json";
        String aplicacion = "EAI";
        String canal = "MDM";
        String mensajeExito = "Operacion exitosa";
        ClaroFault claroFault = new ClaroFault();
        List<ListaOpcionalType> responseOpcional = new ArrayList<>();
        ListaOpcionalType listaRes = new ListaOpcionalType();
        listaRes.setCampo("campo");
        listaRes.setValor("valor");
        responseOpcional.add(listaRes);

        EnviarMarcaDCMSResponse obtenerDatosReposicionResponse = new EnviarMarcaDCMSResponse();
        EnviarMarcaDCMSResponseType obtenerDatosReposicionResponseTp = new EnviarMarcaDCMSResponseType();
        ResponseStatusType responseStatus = new ResponseStatusType();
        responseStatus.setIdTransaccion(idTransaccion);
        responseStatus.setCodigoRespuesta(Constantes.CERO);
        responseStatus.setMensajeRespuesta(mensajeExito);

        obtenerDatosReposicionResponseTp.setResponseStatus(responseStatus);
        obtenerDatosReposicionResponse.setEnviarMarcaDCMSResponse(obtenerDatosReposicionResponseTp);

        obtenerDatosReposicionResponse.setClaroFault(claroFault);
        Mockito.when(enviarMarcaDIMService.enviarMarcaDIM(Mockito.any(HeaderRequest.class),
                Mockito.any(EnviarMarcaDCMSRequest.class))).thenReturn(obtenerDatosReposicionResponse);

        EnviarMarcaDCMSRequest request = new EnviarMarcaDCMSRequest();
        EnviarMarcaDCMSRequestType obtenerDatosReposicionRequest = new EnviarMarcaDCMSRequestType();
        obtenerDatosReposicionRequest.setListaOpcional(responseOpcional);
        request.setEnviarMarcaDCMSRequest(obtenerDatosReposicionRequest);

        obtenerDatosReposicionResponse = marcasEspecialesResource.enviarMarcaDIM(idTransaccion, userId, msgid, accept,
                timestamp, aplicacion, canal, request);

        assertEquals(Constantes.CERO,
                obtenerDatosReposicionResponse.getEnviarMarcaDCMSResponse().getResponseStatus().getCodigoRespuesta());
        assertEquals(mensajeExito,
                obtenerDatosReposicionResponse.getEnviarMarcaDCMSResponse().getResponseStatus().getMensajeRespuesta());

    }

}