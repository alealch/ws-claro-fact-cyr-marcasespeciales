package pe.com.claro.api.marcasespeciales.repository.bean;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.canonical.comun.ListaOpcionalType;
import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseOpcional;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponseType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.ResponseData;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;

public class BeansTest {

	@Test
	public void test_ConsultarCambioTitularidad_Beans() {
		SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse = new SpMarcasEspecialIndivResponse();
		spMarcasEspecialIndivResponse.setErrorMsg("ErrorMsg");
		spMarcasEspecialIndivResponse.getErrorMsg();
		assertNotNull(spMarcasEspecialIndivResponse.getErrorMsg());
	}

	@Test
	public void test_PropertiesExternos_Beans() {
		PropertiesExternos p = new PropertiesExternos();
		assertNotNull(p);
	}

	@Test
	public void test_ResponseOpcional_Beans() {
		ResponseOpcional p = new ResponseOpcional();
		assertNotNull(p);
	}

	@Test
	public void test_ResponseData_Beans() {
		ResponseData responseData = new ResponseData();
		ListaOpcionalType elemento = new ListaOpcionalType();
		elemento.setCampo("campo");
		elemento.setValor("valor");
		List<ListaOpcionalType> lista = new ArrayList<>();
		lista.add(elemento);
		responseData.setListaOpcional(lista);
		responseData.getListaOpcional();
		assertNotNull(responseData.getListaOpcional());
	}

	@Test
	public void test_ConsultarNotificacionCartaResponseType_Beans() {
		EnviarMarcaDCMSResponseType bean = new EnviarMarcaDCMSResponseType();
		bean.setResponseData(new ResponseData());
		assertNotNull(bean.getResponseData());
	}

}