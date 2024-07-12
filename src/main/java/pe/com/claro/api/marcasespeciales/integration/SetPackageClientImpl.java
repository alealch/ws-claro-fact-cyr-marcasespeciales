package pe.com.claro.api.marcasespeciales.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.exception.WSException;
import pe.com.claro.api.marcasespeciales.integration.bean.PackageResponse;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@Component
public class SetPackageClientImpl implements SetPackageClient {

  private static final Logger log = LoggerFactory.getLogger(SetPackageClientImpl.class);
  @Autowired
  private PropertiesExternos prop;
  private ObjectMapper objMapper = new ObjectMapper();

  @Override
  public PackageResponse setPackage(String mensajeTransaccion, String request, HeaderRequest header)
      throws WSException {

    String nombreMetodo = prop.wsCyrDimMetodo;
    String idTx = "[idTx: " + header.getIdTransaccion() + "]";
    String mensajeMetodo = mensajeTransaccion + "[ " + nombreMetodo + " ] ";
    long tiempoInicio = System.currentTimeMillis();
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, idTx, Constantes.INICIO_METODO, nombreMetodo,
        Constantes.CHAR_CORCHETE_DERECHO);
    log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, "Se consumira al servicio: ", prop.wsCyrDimNombre);
    String uri = prop.wsCyrDimsUrl;
    String nombreComponente = prop.wsCyrDimNombre;
    String conectTimeout = prop.wsCyrDimTimeoutConnect;
    String readTimeout = prop.wsCyrDimTimeoutEjecucion;
    PackageResponse notificacionOnlineResponse = new PackageResponse();
    WebResource webResource = null;
    String jsonPayload = Constantes.TEXTO_VACIO;
    ClientResponse responClient = null;
    int httpResulCode = 0;
    try {
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, " URI: ", uri);
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, "[conectTimeout]", conectTimeout);
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, "[readExecuteTimeout]", readTimeout);
      this.headerLog(mensajeMetodo, header);

      String requestHeaderPrint = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(header);
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, Constantes.PARAMETROSHEADER, requestHeaderPrint);
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, Constantes.PARAMETROSBODY, request);

      Client client = Client.create();
      client.setConnectTimeout(Integer.parseInt(conectTimeout));
      client.setReadTimeout(Integer.parseInt(readTimeout));
      webResource = client.resource(uri);
      responClient = webResource.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
          .header("content-type", "application/json;charset=UTF-8")
          .header(Constantes.IDTRANSACCION, header.getIdTransaccion())
          .header(Constantes.TIMESTAMP, header.getTimestamp()).header(Constantes.MSGID, header.getMsgid())
          .header(Constantes.USRID, header.getUserId()).header(Constantes.ACCEPT, header.getAccept())
          .header(Constantes.APLICACION, header.getAplicacion()).header(Constantes.CANAL, header.getCanal())
          .header(Constantes.IDAPLICACION, header.getIdAplicacion()).post(ClientResponse.class, request);
      jsonPayload = responClient.getEntity(String.class);
      httpResulCode = responClient.getStatus();
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, "Status Code: ", httpResulCode, Constantes.TEXTO_VACIO);
      if (responClient.getStatus() != 200) {
        log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, Constantes.PARAMETROS_BODY_RESPONSE, jsonPayload,
            Constantes.TEXTO_VACIO);
        throw new WSException(responClient.getStatus() + "", jsonPayload, null, null);
      }

      objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      notificacionOnlineResponse = objMapper.readValue(jsonPayload, PackageResponse.class);
      String responseBodyPrint = objMapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(notificacionOnlineResponse);
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, Constantes.PARAMETROS_BODY_RESPONSE, responseBodyPrint,
          Constantes.TEXTO_VACIO);
    } catch (Exception e) {
      ClaroUtil.capturarErrorWs(e, nombreComponente, nombreMetodo, prop, httpResulCode);
    } finally {
      log.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeMetodo, Constantes.FIN_METODO, nombreMetodo,
          Constantes.CHAR_CORCHETE_DERECHO);
      log.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeMetodo, Constantes.TIEMPOTOTALPROCESO,
          (System.currentTimeMillis() - tiempoInicio), Constantes.MILISEGUNDOS);
    }
    return notificacionOnlineResponse;
  }

  public void headerLog(String msjTx, HeaderRequest header) {
    log.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, " Request Header: ");
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.IDTRANSACCION, " :", header.getIdTransaccion());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.TIMESTAMP, " :", header.getTimestamp());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.MSGID, " :", header.getMsgid());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.USRID, " :", header.getUserId());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.ACCEPT, " :", header.getAccept());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.CANAL, " :", header.getCanal());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.APLICACION, " :", header.getAplicacion());
    log.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.IDAPLICACION, " :", header.getIdAplicacion());
  }

}