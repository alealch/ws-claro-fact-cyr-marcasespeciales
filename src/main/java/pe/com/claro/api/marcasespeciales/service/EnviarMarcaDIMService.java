package pe.com.claro.api.marcasespeciales.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;
import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseStatusType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSRequest;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponse;
import pe.com.claro.api.marcasespeciales.canonical.consultar.EnviarMarcaDCMSResponseType;
import pe.com.claro.api.marcasespeciales.canonical.consultar.ResponseData;
import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.exception.IDFException;
import pe.com.claro.api.marcasespeciales.exception.WSException;
import pe.com.claro.api.marcasespeciales.integration.SetPackageClient;
import pe.com.claro.api.marcasespeciales.integration.bean.BusinessData;
import pe.com.claro.api.marcasespeciales.integration.bean.Messages;
import pe.com.claro.api.marcasespeciales.integration.bean.PackageRequest;
import pe.com.claro.api.marcasespeciales.integration.bean.PackageResponse;
import pe.com.claro.api.marcasespeciales.integration.bean.ProcessContext;
import pe.com.claro.api.marcasespeciales.integration.bean.Tags;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.repository.SiopDbDao;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivResponse;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateResponse;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@Service
public class EnviarMarcaDIMService {

  private static final Logger logger = LoggerFactory.getLogger(EnviarMarcaDIMService.class);

  @Autowired
  private PropertiesExternos propertiesExternos;

  @Autowired
  private SiopDbDao siopDbDao;

  @Autowired
  private SetPackageClient setPackageClient;

  public EnviarMarcaDCMSResponse enviarMarcaDIM(HeaderRequest headerRequest, EnviarMarcaDCMSRequest request)
      throws IDFException, JsonProcessingException {

    long iniciotiempo = System.currentTimeMillis();
    String mensajeLog = "[" + headerRequest.getIdTransaccion() + "]";
    logger.info(Constantes.SEPARADOR_DOS_LLAVES, "INICIO METODO enviarMarcaDIM ", mensajeLog);
    ResponseStatusType responseStatusType = new ResponseStatusType();
    responseStatusType.setIdTransaccion(headerRequest.getIdTransaccion());
    EnviarMarcaDCMSResponse response = new EnviarMarcaDCMSResponse();
    EnviarMarcaDCMSResponseType enviarMarcaDCMSResponseType = new EnviarMarcaDCMSResponseType();
    ClaroFault claroFaultType = new ClaroFault();
    try {
      validarCamposObligatorios(request, mensajeLog);
      actividad2(mensajeLog, request, enviarMarcaDCMSResponseType, headerRequest);

    } catch (BDException dbe) {
      logger.info(Constantes.SEPARADOR_CINCO_LLAVES, mensajeLog, Constantes.ERROR_COR,
          String.valueOf(Constantes.TEXTO_VACIO + dbe), Constantes.COR_DERECHA);
      responseStatusType = ClaroUtil.setResponseStatus(responseStatusType, dbe.getCodError(), dbe.getMessage(),
          headerRequest.getIdTransaccion());
      claroFaultType = ClaroUtil.responseClaroFaultTypeDb(dbe, headerRequest);
      enviarMarcaDCMSResponseType.setResponseStatus(responseStatusType);
      response.setClaroFault(claroFaultType);
    } catch (IDFException idf) {
      logger.info(Constantes.SEPARADOR_CINCO_LLAVES, mensajeLog, Constantes.ERROR_COR,
          String.valueOf(Constantes.TEXTO_VACIO + idf), Constantes.COR_DERECHA);
      responseStatusType = ClaroUtil.setResponseStatus(responseStatusType, idf.getCodError(), idf.getMessage(),
          headerRequest.getIdTransaccion());
      enviarMarcaDCMSResponseType.setResponseStatus(responseStatusType);
      response.setClaroFault(claroFaultType);
    } catch (Exception e) {
      logger.error(Constantes.SEPARADOR_CINCO_LLAVES, mensajeLog, Constantes.ERROR_COR,
          String.valueOf(Constantes.TEXTO_VACIO + e), Constantes.COR_DERECHA, e.getMessage());
      responseStatusType.setCodigoRespuesta(propertiesExternos.codigoIdt3);
      responseStatusType.setMensajeRespuesta(
          propertiesExternos.mensajeIdt3.replace("$ex", String.valueOf(Constantes.TEXTO_VACIO + e)));
      enviarMarcaDCMSResponseType.setResponseStatus(responseStatusType);

    } finally {
      response.setEnviarMarcaDCMSResponse(enviarMarcaDCMSResponseType);
      String responsePrint = ClaroUtil.printPrettyJSONString(response);
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, Constantes.DEVOLVERRESPONSE, Constantes.SALTO_LINEA,
          responsePrint);
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, Constantes.TIEMPO_TOTAL_PROCESO,
          (System.currentTimeMillis() - iniciotiempo), Constantes.MILISEGUNDOS);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, "FIN METODO enviarMarcaDIM");

    }

    return response;
  }

  private void validarCamposObligatorios(EnviarMarcaDCMSRequest request, String mensajeLog) throws IDFException {
    ClaroUtil.inicioActividad(mensajeLog, Constantes.ACTIVIDAD_1);
    if (request.getEnviarMarcaDCMSRequest().getEntidadId() == null
        || request.getEnviarMarcaDCMSRequest().getEntidadId().isEmpty()) {
      throw new IDFException(propertiesExternos.codigoIdf1,
          propertiesExternos.mensajeIdf1.replace(Constantes.VARIABLE_CAMPO, "entidadId"), "", null);
    }
    if (request.getEnviarMarcaDCMSRequest().getCodigoMarca() == null
        || request.getEnviarMarcaDCMSRequest().getCodigoMarca().isEmpty()) {
      throw new IDFException(propertiesExternos.codigoIdf1,
          propertiesExternos.mensajeIdf1.replace(Constantes.VARIABLE_CAMPO, "codigoMarca"), "", null);
    }
    if (request.getEnviarMarcaDCMSRequest().getSistemaOrigen() == null
        || request.getEnviarMarcaDCMSRequest().getSistemaOrigen().isEmpty()) {
      throw new IDFException(propertiesExternos.codigoIdf1,
          propertiesExternos.mensajeIdf1.replace(Constantes.VARIABLE_CAMPO, "sistemaOrigen"), "", null);
    }
    if (request.getEnviarMarcaDCMSRequest().getValorMarca() == null
        || request.getEnviarMarcaDCMSRequest().getValorMarca().isEmpty()) {
      throw new IDFException(propertiesExternos.codigoIdf1,
          propertiesExternos.mensajeIdf1.replace(Constantes.VARIABLE_CAMPO, "valorMarca"), "", null);
    }
    ClaroUtil.finActividad(mensajeLog, Constantes.ACTIVIDAD_1);
  }

  private EnviarMarcaDCMSResponseType actividad2(String mensajeLog, EnviarMarcaDCMSRequest request,
      EnviarMarcaDCMSResponseType enviarMarcaDCMSResponseType, HeaderRequest headerRequest)
      throws BDException, WSException, JsonProcessingException, IDFException {
    ClaroUtil.inicioActividad(mensajeLog, Constantes.ACTIVIDAD_2);
    SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse = new SpMarcasEspecialIndivResponse();
    for (int i = 1; i <= Integer.parseInt(propertiesExternos.registrarReposicionReintentos); i++) {
      try {
        SpMarcasEspecialIndivRequest req = new SpMarcasEspecialIndivRequest();
        req.setCodigo(request.getEnviarMarcaDCMSRequest().getCodigoMarca());
        req.setEntidadId(request.getEnviarMarcaDCMSRequest().getEntidadId());
        req.setFacturador(request.getEnviarMarcaDCMSRequest().getSistemaOrigen());
        req.setValor(request.getEnviarMarcaDCMSRequest().getValorMarca());
        req.setUsuarioMod(headerRequest.getUserId());

        spMarcasEspecialIndivResponse = siopDbDao.marcasEspecialesIndv(mensajeLog, req);
        break;
      } catch (BDException ex) {
        String error = (ex + Constantes.TEXTO_VACIO);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.ERROR_INESPERADO, ex);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.INTENTO, i);
        if (i == Integer.parseInt(propertiesExternos.registrarReposicionReintentos)) {
          if (error.contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
            throw new BDException(propertiesExternos.codigoIdt1, ex.getMessage(), "", ex);
          } else {
            throw new BDException(propertiesExternos.codigoIdt2, ex.getMessage(), "", ex);
          }
        }
      } finally {
        ClaroUtil.finActividad(mensajeLog, Constantes.ACTIVIDAD_2);
      }
    }

    return getConsultarLineasActivasPrePago(spMarcasEspecialIndivResponse, mensajeLog, headerRequest,
        enviarMarcaDCMSResponseType);
  }

  private EnviarMarcaDCMSResponseType getConsultarLineasActivasPrePago(
      SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse, String mensajeLog, HeaderRequest headerRequest,
      EnviarMarcaDCMSResponseType enviarMarcaDCMSResponseType)
      throws WSException, JsonProcessingException, BDException, IDFException {
    return getContarLineasResponseType(headerRequest, enviarMarcaDCMSResponseType, mensajeLog,
        spMarcasEspecialIndivResponse);
  }

  private EnviarMarcaDCMSResponseType getContarLineasResponseType(HeaderRequest headerRequest,
      EnviarMarcaDCMSResponseType enviarMarcaDCMSResponseType, String mensajeLog,
      SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse)
      throws WSException, JsonProcessingException, BDException, IDFException {
    String codigoRespuesta = "";
    String mensajeRespuesta = "";
    ResponseData responseData = new ResponseData();
    ResponseStatusType responseStatusType = new ResponseStatusType();

    if ("0".equals(spMarcasEspecialIndivResponse.getErrorCode())) {
      if (spMarcasEspecialIndivResponse.getListaPre() != null
          && !spMarcasEspecialIndivResponse.getListaPre().isEmpty()) {
        logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, "CURSOR [TAMA[",
            spMarcasEspecialIndivResponse.getListaPre().size(), "]");

        codigoRespuesta = propertiesExternos.codigoIdf0;
        mensajeRespuesta = propertiesExternos.mensajeIdf0;
        actividad3(mensajeLog, spMarcasEspecialIndivResponse, headerRequest);

      } else {
        codigoRespuesta = propertiesExternos.codigoIdf2;
        mensajeRespuesta = propertiesExternos.mensajeIdf2;
      }
    } else {
      codigoRespuesta = propertiesExternos.codigoIdf2;
      mensajeRespuesta = propertiesExternos.mensajeIdf2;
    }
    responseStatusType.setCodigoRespuesta(codigoRespuesta);
    responseStatusType.setMensajeRespuesta(mensajeRespuesta);
    responseStatusType.setIdTransaccion(headerRequest.getIdTransaccion());

    logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, responseStatusType.getCodigoRespuesta());
    logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, responseStatusType.getMensajeRespuesta());
    enviarMarcaDCMSResponseType.setResponseData(responseData);
    enviarMarcaDCMSResponseType.setResponseStatus(responseStatusType);

    return enviarMarcaDCMSResponseType;
  }

  private void actividad3(String mensajeLog, SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse,
      HeaderRequest headerRequest) throws WSException, JsonProcessingException, BDException, IDFException {

    PackageResponse setPackageResponse = new PackageResponse();
    PackageRequest packageRequest = new PackageRequest();
    ClaroUtil.inicioActividad(mensajeLog, Constantes.ACTIVIDAD_3);
    String estadoEnviado = propertiesExternos.estadoEnviado;
    String estadoError = propertiesExternos.estadoError;
    String errorMensaje1 = propertiesExternos.mensajeTimeout;
    String errorMensaje2 = propertiesExternos.mensajeDisponibilidad;
    String errorMensajeExito = propertiesExternos.mensajeExito;
    SpMarcasEspecialUpdateRequest beanMarcasEspeciales = new SpMarcasEspecialUpdateRequest();
    for (int i = 1; i <= Integer.parseInt(propertiesExternos.registrarReposicionReintentos); i++) {
      try {
        headerRequest.setIdAplicacion(propertiesExternos.idaplicacion);
        setPackageResponse = setPackageClient.setPackage(mensajeLog,
            validarRequest(packageRequest, spMarcasEspecialIndivResponse), headerRequest);
        beanMarcasEspeciales.setErrorMensaje(errorMensajeExito);
        beanMarcasEspeciales.setEstado(Integer.parseInt(estadoEnviado));
        break;
      } catch (WSException ex) {
        String error = (ex + Constantes.TEXTO_VACIO);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.ERROR_INESPERADO, ex);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.INTENTO, i);
        if (i == Integer.parseInt(propertiesExternos.registrarReposicionReintentos)) {
          beanMarcasEspeciales = validarIdt(error, errorMensaje1, estadoError, errorMensaje2);
        }
      } finally {
        ClaroUtil.finActividad(mensajeLog, Constantes.ACTIVIDAD_3);
      }
    }
    actividad4(mensajeLog, beanMarcasEspeciales, headerRequest, spMarcasEspecialIndivResponse);
  }

  private SpMarcasEspecialUpdateRequest validarIdt(String error, String errorMensaje1, String estadoError,
      String errorMensaje2) {
    SpMarcasEspecialUpdateRequest beanMarcasEspeciales = new SpMarcasEspecialUpdateRequest();
    if (error.contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
      beanMarcasEspeciales.setErrorMensaje(errorMensaje1);
      beanMarcasEspeciales.setEstado(Integer.parseInt(estadoError));
    } else {
      beanMarcasEspeciales.setErrorMensaje(errorMensaje2);
      beanMarcasEspeciales.setEstado(Integer.parseInt(estadoError));
    }
    return beanMarcasEspeciales;
  }

  private void actividad4(String mensajeLog, SpMarcasEspecialUpdateRequest beanMarcasEspeciales,
      HeaderRequest headerRequest, SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse)
      throws BDException, IDFException {

    ClaroUtil.inicioActividad(mensajeLog, Constantes.ACTIVIDAD_4);
    SpMarcasEspecialUpdateResponse spMarcasEspecialUpdateResponse = new SpMarcasEspecialUpdateResponse();
    for (int i = 1; i <= Integer.parseInt(propertiesExternos.registrarReposicionReintentos); i++) {
      try {
        SpMarcasEspecialUpdateRequest req = new SpMarcasEspecialUpdateRequest();
        req.setMaesnId(Integer.parseInt(spMarcasEspecialIndivResponse.getListaPre().get(0).getId()));
        req.setEstado(beanMarcasEspeciales.getEstado());
        req.setErrorMensaje(beanMarcasEspeciales.getErrorMensaje());
        req.setUsuarioMod(headerRequest.getUserId());
        spMarcasEspecialUpdateResponse = siopDbDao.marcasEspecialesUpdate(mensajeLog, req);
        if (spMarcasEspecialUpdateResponse.getCode().equalsIgnoreCase(propertiesExternos.codigoIdf0)) {
          break;
        } else {
          throw new IDFException(propertiesExternos.codigoIdf3, propertiesExternos.mensajeIdf3, "", null);
        }
      } catch (BDException ex) {
        String error = (ex + Constantes.TEXTO_VACIO);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.ERROR_INESPERADO, ex);
        logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.INTENTO, i);
        if (i == Integer.parseInt(propertiesExternos.registrarReposicionReintentos)) {
          if (error.contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
            throw new BDException(propertiesExternos.codigoIdt1, ex.getMessage(), "", ex);
          } else {
            throw new BDException(propertiesExternos.codigoIdt2, ex.getMessage(), "", ex);
          }
        }
      } finally {
        ClaroUtil.finActividad(mensajeLog, Constantes.ACTIVIDAD_4);
      }
    }
  }

  private String validarRequest(PackageRequest packageRequest,
      SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse) throws JsonProcessingException {

    List<Messages> messagesList = new ArrayList<>();
    Messages mensages = new Messages();
    mensages.setAction(propertiesExternos.dimAction);
    mensages.setBatchId(propertiesExternos.dimMe + spMarcasEspecialIndivResponse.getListaPre().get(0).getCodigo() + "-"
        + spMarcasEspecialIndivResponse.getListaPre().get(0).getId());
    mensages.setReference(propertiesExternos.dimMe + spMarcasEspecialIndivResponse.getListaPre().get(0).getCodigo()
        + "-" + spMarcasEspecialIndivResponse.getListaPre().get(0).getId());
    mensages.setIdentifier(propertiesExternos.dimMe + spMarcasEspecialIndivResponse.getListaPre().get(0).getCodigo()
        + "-" + spMarcasEspecialIndivResponse.getListaPre().get(0).getId());
    BusinessData businessData = new BusinessData();
    businessData.setEntity(spMarcasEspecialIndivResponse.getListaPre().get(0).getEntidad());
    businessData.setEntityId(spMarcasEspecialIndivResponse.getListaPre().get(0).getEntidadId());
    businessData.setEntityProvider(spMarcasEspecialIndivResponse.getListaPre().get(0).getFacturador());
    businessData.setCode(spMarcasEspecialIndivResponse.getListaPre().get(0).getCodigo());
    businessData.setValue(spMarcasEspecialIndivResponse.getListaPre().get(0).getValor());
    businessData.setValidTo(ClaroUtil.getDateFormato(
        spMarcasEspecialIndivResponse.getListaPre().get(0).getFecVigInicio(), propertiesExternos.formatoFechaVig));
    businessData.setValidFrom(ClaroUtil.getDateFormato(
        spMarcasEspecialIndivResponse.getListaPre().get(0).getFecVigFin(), propertiesExternos.formatoFechaVig));
    mensages.setBusinessData(businessData);
    mensages.setEntity(propertiesExternos.dimEntity);
    mensages.setPriority(propertiesExternos.dimPriority);
    List<ProcessContext> processContextList = new ArrayList<>();
    ProcessContext processContext = new ProcessContext();
    processContext.setKey(propertiesExternos.dimProccessKey);
    processContext.setValue(propertiesExternos.dimProccessValue);
    processContextList.add(processContext);
    mensages.setProcessContext(processContextList);
    mensages.setRequester(propertiesExternos.dimRequester);
    List<Tags> tags = new ArrayList<>();
    Tags tag0 = new Tags();
    tag0.setKey(propertiesExternos.dimTagKey);
    tag0.setValue(propertiesExternos.dimTagValue);
    tags.add(tag0);
    Tags tag1 = new Tags();
    tag1.setKey(propertiesExternos.dimTagKeyTwo);
    tag1.setValue(propertiesExternos.dimMe + spMarcasEspecialIndivResponse.getListaPre().get(0).getCodigo() + "-"
        + spMarcasEspecialIndivResponse.getListaPre().get(0).getId());
    tags.add(tag1);
    mensages.setTags(tags);
    messagesList.add(mensages);
    packageRequest.setMessages(messagesList);

    return ClaroUtil.printPrettyJSONString(packageRequest);
  }

}