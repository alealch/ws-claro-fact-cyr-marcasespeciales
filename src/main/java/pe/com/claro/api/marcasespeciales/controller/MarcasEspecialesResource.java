package pe.com.claro.api.marcasespeciales.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;
import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseStatusType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequest;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponse;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponseType;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.service.EnviarMarcaDIMService;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@RestController
@RequestMapping("/api/fact/marcasEspeciales/v1.0.0")
public class MarcasEspecialesResource {

  private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  @Autowired
  private EnviarMarcaDIMService enviarMarcaDIMService;

  @Autowired
  private PropertiesExternos prop;

  @PostMapping(value = "/enviarMarcaDCMS", consumes = "application/json", produces = "application/json")
  public EnviarMarcaDCMSResponse enviarMarcaDIM(@RequestHeader(name = Constantes.IDTRANSACCION) String idTransaccion,
      @RequestHeader(name = Constantes.USRID) String userId, @RequestHeader(name = Constantes.MSGID) String msgid,
      @RequestHeader(name = Constantes.ACCEPT) String accept,
      @RequestHeader(name = Constantes.TIMESTAMP) String timestamp,
      @RequestHeader(name = Constantes.APLICACION) String aplicacion,
      @RequestHeader(name = Constantes.CANAL) String canal,
      @RequestBody(required = true) EnviarMarcaDCMSRequest request) throws JsonProcessingException {

    HeaderRequest headerRequest = new HeaderRequest();
    headerRequest.setIdTransaccion(idTransaccion);
    headerRequest.setUserId(userId);
    headerRequest.setMsgid(msgid);
    headerRequest.setAccept(accept);
    headerRequest.setTimestamp(timestamp);
    headerRequest.setAplicacion(aplicacion);
    headerRequest.setCanal(canal);
    String idtx = headerRequest.getIdTransaccion();

    String msjTx = Constantes.CHAR_CORCHETE_IZQUIERDO + Constantes.CONSULTAR_NOTIFICACION_CARTA + Constantes.ID_TXT
        + idtx + Constantes.MSG_ID + msgid + Constantes.CORCHETE;
    long tiempoInicio = System.currentTimeMillis();
    String requestPrint = null;
    String responsePrint = null;

    EnviarMarcaDCMSResponse response = new EnviarMarcaDCMSResponse();
    EnviarMarcaDCMSResponseType enviarMarcaDCMSResponseType = new EnviarMarcaDCMSResponseType();

    ResponseStatusType responseStatus = new ResponseStatusType();

    logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.SEPARADOR);
    logger.info(Constantes.SEPARADOR_TRES_LLAVES, msjTx, Constantes.INICIO_METODO,
        Constantes.CONSULTAR_NOTIFICACION_CARTA);
    logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.SEPARADOR);
    try {

      requestPrint = ClaroUtil.printPrettyJSONString(request);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.PARAMETROSENTRADA);
      String pintarHeader = ClaroUtil.printPrettyJSONString(headerRequest);
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA,
          pintarHeader);
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA,
          requestPrint);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.PARAMETROSOBLIGATORIOS);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
      response = enviarMarcaDIMService.enviarMarcaDIM(headerRequest, request);

    } catch (Exception e) {
      logger.error(Constantes.SEPARADOR_CINCO_LLAVES, msjTx, Constantes.ERROR_COR,
          String.valueOf(Constantes.TEXTO_VACIO + e), Constantes.COR_DERECHA);
      ClaroFault responseError = new ClaroFault();
      responseError.setIdAudit(idtx);
      responseError.setCodeError(prop.codigoIdt3);
      responseError.setDescriptionError(prop.mensajeIdt3 + e.getMessage());
      responseError.setLocationError(e.getLocalizedMessage());
      responseError.setDate(new Date().toString());
      responseError.setOriginError(
          Constantes.NOMBRERECURSO + Constantes.TEXTOESPACIO + Constantes.CONSULTAR_NOTIFICACION_CARTA);
      response.setClaroFault(responseError);
      responseStatus.setMensajeRespuesta(prop.mensajeIdt3 + Constantes.ESPACIO + e.getMessage());
      responseStatus.setCodigoRespuesta(prop.codigoIdt3);
      responseStatus.setIdTransaccion(idTransaccion);

      enviarMarcaDCMSResponseType.setResponseStatus(responseStatus);
      response.setEnviarMarcaDCMSResponse(enviarMarcaDCMSResponseType);
    } finally {
      responsePrint = ClaroUtil.printPrettyJSONString(response);
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, msjTx, Constantes.DEVOLVERRESPONSE, Constantes.SALTO_LINEA,
          responsePrint);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.SEPARADOR);
      logger.info(Constantes.SEPARADOR_SEIS_LLAVES, msjTx, Constantes.FIN_METODO,
          Constantes.CONSULTAR_NOTIFICACION_CARTA, Constantes.TIEMPO_TOTAL,
          (System.currentTimeMillis() - tiempoInicio), Constantes.MILISEGUNDOS);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, msjTx, Constantes.SEPARADOR);
    }
    return response;
  }

}