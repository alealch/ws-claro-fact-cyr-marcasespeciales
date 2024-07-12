package pe.com.claro.api.marcasespeciales.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;
import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.canonical.comun.ResponseStatusType;
import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.exception.BaseException;
import pe.com.claro.api.marcasespeciales.exception.WSException;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;

public class ClaroUtil {
  private static final Logger log = LoggerFactory.getLogger(ClaroUtil.class);

  public static DateFormat getLocalFormat() {
    DateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATOFECHACABECERA);
    dateFormat.setTimeZone(TimeZone.getDefault());
    return dateFormat;
  }

  public static String printPrettyJSONString(Object o) throws JsonProcessingException {
    return new ObjectMapper().setDateFormat(ClaroUtil.getLocalFormat())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
        .writeValueAsString(o);
  }

  public static String printJSONString(Object o) {
    try {
      return new ObjectMapper().setDateFormat(ClaroUtil.getLocalFormat())
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(o);
    } catch (JsonProcessingException e) {
      log.error("Ocurrio un error al convertir JSON", e);
      return Constantes.TEXTO_VACIO;
    }
  }

  public static String nuloAVacio(Object object) {

    if (object == null) {
      return Constantes.TEXTO_VACIO;
    } else {
      return object.toString();
    }
  }

  public static Object nuloAVacioObject(Object object) {
    if (object == null) {
      return Constantes.TEXTO_VACIO;
    } else {
      return object;
    }
  }

  public static String verifiyNull(Object object) {
    String a = null;
    if (object != null) {
      a = object.toString();
    }
    return a;
  }

  public static Integer convertirInteger(Object object) {

    Integer res = null;
    if (object != null) {
      if (object instanceof BigDecimal) {
        BigDecimal bd = (BigDecimal) object;
        res = bd.intValueExact();
      } else {
        log.error(object.getClass().getSimpleName());
      }
    }
    return res;
  }

  public static String dateAString(Date fecha) {
    if (fecha == null) {
      return Constantes.TEXTO_VACIO;
    }
    return dateAString(fecha, Constantes.FORMATOFECHACABECERA);
  }

  public static String dateAString(Date fecha, String formato) {

    if (fecha != null) {
      SimpleDateFormat formatoDF = new SimpleDateFormat(formato, Locale.ROOT);
      return formatoDF.format(fecha);
    } else {
      return null;
    }

  }

  public static boolean isValidFormat(String format, String value) {
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ROOT);
      date = sdf.parse(value);
      if (!value.equals(sdf.format(date))) {
        date = null;
      }
    } catch (ParseException ex) {
      date = null;
    }
    return date != null;
  }

  public static String getStoredProcedureByParameters(Object owner, Object name) {
    StringBuilder storedProcedure = new StringBuilder();
    if (owner != null && !owner.toString().isEmpty()) {
      storedProcedure.append(owner.toString());
      storedProcedure.append(Constantes.SEPARADORPUNTO);
    }

    if (name != null && !name.toString().isEmpty()) {
      storedProcedure.append(name.toString());
    }
    return storedProcedure.toString();
  }

  public static Connection getJNDIConnection(String mensajeTransaccion, String jndi, String tiempoConexion)
      throws SQLException {
    Connection result = null;
    try {
      Class.class.getDeclaredMethod("forName", String.class).invoke(null, Constantes.ORACLEDRIVER);
      if (jndi.contains(Constantes.PARAMETRO_INICIAL_JDBC)) {
        DriverManager.setLoginTimeout(Integer.valueOf(tiempoConexion));
        String nombreBD = getNombreBD(jndi);
        result = DriverManager.getConnection(jndi, getUser(nombreBD), getPasword(nombreBD));
      } else {
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(jndi);
        datasource.setLoginTimeout(Integer.parseInt(tiempoConexion));
        result = datasource.getConnection();
      }
    } catch (SQLException ex) {
      log.info("{}{}{}", mensajeTransaccion, Constantes.CANNOT_GET_CONNECTION, ex);
      throw new SQLException(ex);

    } catch (Exception e) {
      log.info("{}{}{}", mensajeTransaccion, Constantes.CANNOT_GET_CONNECTION, e);
      throw new SQLException(e);
    }
    return result;
  }

  public static String getNombreBD(String conexion) {
    String resultado = Constantes.TEXTO_VACIO;
    resultado = conexion.substring(conexion.lastIndexOf('/') + 1);
    return resultado;
  }

  public static String getUser(String parametro) {
    String resultado = Constantes.TEXTO_VACIO;
    if (parametro.equals("BSCSDES")) {
      resultado = "tim";
    } else if (parametro.equals("TIMDEV")) {
      resultado = "usreaidesa";
    } else if (parametro.equals("TIMPRB")) {
      resultado = "sa";
    }
    return resultado;
  }

  public static String getPasword(String parametro) {
    String resultado = Constantes.TEXTO_VACIO;
    if (parametro.equals("BSCSDES")) {
      resultado = "tim";
    } else if (parametro.equals("TIMDEV")) {
      resultado = "usreaidesa";
    } else if (parametro.equals("TIMPRB")) {
      resultado = "pyclarify";
    }
    return resultado;
  }

  public static String validaVacio(ResultSet result, int valor) throws SQLException {
    return result.getString(valor) != null ? result.getString(valor) : Constantes.TEXTO_VACIO;
  }

  public static String validaVacio(CallableStatement call, int valor) throws SQLException {
    return call.getString(valor) != null ? call.getString(valor) : Constantes.TEXTO_VACIO;
  }

  public static void inicioImpl(String cadenaTrazabilidad, String metodo, String bd, String sp, String owner) {
    log.info(Constantes.SEPARADOR_DOS_LLAVES, cadenaTrazabilidad, Constantes.SEPARADOR_MEDIO);
    log.info(Constantes.SEPARADOR_SEIS_LLAVES, cadenaTrazabilidad, Constantes.SEPARADOR_CHICO, " [", metodo, " ]",
        Constantes.SEPARADOR_CHICO);
    log.info(Constantes.SEPARADOR_DOS_LLAVES, cadenaTrazabilidad, Constantes.SEPARADOR_MEDIO);
    log.info(Constantes.SEPARADOR_TRES_LLAVES, cadenaTrazabilidad, "OWNER:", owner);
    log.info(Constantes.SEPARADOR_TRES_LLAVES, cadenaTrazabilidad, "BD:", bd);
    log.info(Constantes.SEPARADOR_TRES_LLAVES, cadenaTrazabilidad, "SP:", sp);
  }

  public static void finImpl(long tiempoInicio, String metodo, String cadenaTrazabilidad) {
    log.info(Constantes.SEPARADOR_DOS_LLAVES, cadenaTrazabilidad, Constantes.SEPARADOR_MEDIO);
    long tiempofinal = System.currentTimeMillis() - tiempoInicio;
    log.info(Constantes.SEPARADOR_SEIS_LLAVES, cadenaTrazabilidad, "================  [", metodo,
        "][Tiempo total de proceso (ms):", tiempofinal, " ]" + Constantes.SEPARADOR_CHICO);
    log.info(Constantes.SEPARADOR_DOS_LLAVES, cadenaTrazabilidad, Constantes.SEPARADOR_MEDIO);
  }

  public static void inicioActividad(String menTran, String actividad) {
    log.info("{}{}", menTran, Constantes.SEPARADOR);
    log.info("{}{}{}{}", menTran, Constantes.INICIO_ACTIVIDAD, actividad, Constantes.SEPARADOR_ACTIVIDAD);
    log.info("{}{}", menTran, Constantes.SEPARADOR);
  }

  public static void finActividad(String menTran, String actividad) {
    log.info("{}{}", menTran, Constantes.SEPARADOR);
    log.info("{}{}{}{}", menTran, Constantes.FIN_ACTIVIDAD, actividad, Constantes.SEPARADOR_ACTIVIDAD);
    log.info("{}{}", menTran, Constantes.SEPARADOR);
  }

  public static int nuloAVacioInt(String object) {
    if (object == null) {
      return Constantes.NUM_ZERO;
    } else {
      return Integer.parseInt(object);
    }
  }

  public static Date stringtoDate(String fechaString, String formato) throws BaseException {
    Date fechaRetorno = null;
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(formato);
      fechaRetorno = formatter.parse(fechaString);
    } catch (Exception e) {
      throw new BaseException("99", "ERROR stringtoDate", "stringtoDate", e);
    }
    return fechaRetorno;
  }

  public static java.sql.Date stringtoDateToSqlDate(String fechaString, String formato) throws BaseException {
    java.sql.Date fechaRetorno = null;
    try {
      fechaRetorno = new java.sql.Date(stringtoDate(fechaString, formato).getTime());
    } catch (BaseException e) {
      throw new BaseException("99", "ERROR stringtoDateToSqlDate", "stringtoDateToSqlDate", e);
    } catch (Exception e) {
      log.info("{}{}", "stringtoDateToSqlDate: ", e);
    }
    return fechaRetorno;
  }

  public static java.sql.Timestamp stringtoDateToSqlDateTimestamp(String fechaString, String formato)
      throws BaseException {
    java.sql.Timestamp fechaRetornoTimestamp = null;
    Date fechaRetorno = null;
    try {
      fechaRetorno = (stringtoDate(fechaString, formato));
      fechaRetornoTimestamp = new java.sql.Timestamp(fechaRetorno.getTime());
    } catch (BaseException e) {
      throw new BaseException("99", "ERROR stringtoDateToSqlDateTimestamp", "stringtoDateToSqlDateTimestamp", e);
    } catch (Exception e) {
      log.info("{}{}", "stringtoDateToSqlDateTimestamp: ", e);
    }
    return fechaRetornoTimestamp;
  }

  public static void errorGenericoBD(Exception ex, String msjTx, String sp, String jndi,
      PropertiesExternos propertiesExternos) throws BDException {

    log.error(Constantes.SEPARADOR_TRES_LLAVES, msjTx, Constantes.ERROR_EJECUCION_SP, ex);

    StringWriter errors = new StringWriter();
    ex.printStackTrace(new PrintWriter(errors));
    String error = String.valueOf(errors.toString());

    if (error.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
      throw new BDException(propertiesExternos.codigoIdt1, propertiesExternos.mensajeIdt1
          .replace(Constantes.VARIABLE_SP, sp).replace(Constantes.VARIABLE_JNDI, jndi), sp, ex);

    } else {
      throw new BDException(propertiesExternos.codigoIdt2, propertiesExternos.mensajeIdt2
          .replace(Constantes.VARIABLE_SP, sp).replace(Constantes.VARIABLE_JNDI, jndi), sp, ex);

    }

  }

  public static void errorGenerico(String trazabilidad, String sp, String jndi, PropertiesExternos propertiesExternos,
      CallableStatement call, Connection connection) throws BDException {

    try {
      if (call != null) {
        call.close();
      }
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException ex) {
      ClaroUtil.errorGenericoBD(ex, trazabilidad, sp, jndi, propertiesExternos);

    }
  }

  public static void cerrarConexiones(Connection conn, StringBuilder storedProcedure, String msj,
      PreparedStatement ps) {
    try {
      if (conn != null) {
        conn.close();
        log.info("{}{}", msj, Constantes.CONEXION_CERRADA_EXITOSAMENTE_CONN);
      }
      if (ps != null) {
        ps.close();
        log.info("{}{}", msj, Constantes.CONEXION_CERRADA_EXITOSAMENTE_PS);
      }
    } catch (Exception e) {
      log.info("{}{}{}{}{}", msj, Constantes.ERROR_CERRAR_RECURSOS_SP, storedProcedure,
          Constantes.PARA_MAYOR_DETALLE_ORIGINAL, e);
    }
  }

  public static void capturarErrorWs(Exception e, String nombreComponente, String nombreMetodo,
      PropertiesExternos properties, int status) throws WSException {
    if (status == Integer.parseInt(Constantes.EXCEPTIONWSNODISPONIBLE02)) {
      throw new WSException(properties.codigoIdt2, properties.mensajeIdt2
          .replace(Constantes.VARIABLE_SP, nombreComponente).replace(Constantes.VARIABLE_JNDI, nombreMetodo),
          null, e);
    }
    String errorMsgConsultaParticipante = e + Constantes.TEXTO_VACIO;
    log.error("{}{}", errorMsgConsultaParticipante, e.getMessage(), e);
    if (errorMsgConsultaParticipante.contains(Constantes.EXCEPTION_WS_TIMEOUT_01)
        || errorMsgConsultaParticipante.contains(Constantes.EXCEPTION_WS_TIMEOUT_02)) {
      throw new WSException(properties.codigoIdt1, properties.mensajeIdt1
          .replace(Constantes.VARIABLE_SP, nombreComponente).replace(Constantes.VARIABLE_JNDI, nombreMetodo),
          null, e);
    } else if (errorMsgConsultaParticipante.contains(Constantes.EXCEPTIONWSNODISPONIBLE01)
        || errorMsgConsultaParticipante.contains(Constantes.EXCEPTIONWSNODISPONIBLE02)
        || errorMsgConsultaParticipante.contains(Constantes.EXCEPTIONWSNODISPONIBLE03)
        || errorMsgConsultaParticipante.contains(Constantes.EXCEPTIONWSNODISPONIBLE04)) {
      throw new WSException(properties.codigoIdt2, properties.mensajeIdt2
          .replace(Constantes.VARIABLE_SP, nombreComponente).replace(Constantes.VARIABLE_JNDI, nombreMetodo),
          null, e);
    } else {
      throw new WSException(properties.codigoIdt3,
          properties.mensajeIdt3.replace(Constantes.EX_CON_CORCHETE, e.getMessage())
              .replace(Constantes.VARIABLE_SP, nombreComponente)
              .replace(Constantes.VARIABLE_JNDI, nombreMetodo),
          null, e);
    }
  }

  public static String nullToVacio(String cadena) {
    return (cadena == null) ? "" : cadena;
  }

  public static String obtenerDocumento(String tipoDocumento, String listaDocumentos) {
    String rpta = "";
    String[] lista = listaDocumentos.split(";");
    for (String documentoFiltro : lista) {
      if (tipoDocumento.equals(String.valueOf(documentoFiltro.charAt(0)))) {
        rpta = documentoFiltro.substring(2);
        break;
      }
    }
    return rpta;
  }

  public static String obtenerTipoDocumento(String valor, String tipoDocu) {
    String valorDocumento = "";
    log.info(Constantes.SEPARADOR_DOS_LLAVES, "tipoDocu: ", tipoDocu);
    String[] listaObtenida = tipoDocu.split("\\,");
    log.info(Constantes.SEPARADOR_DOS_LLAVES, "valor: ", valor);
    for (String tipoDocumentos : listaObtenida) {
      String[] listaFinal = tipoDocumentos.split(Constantes.BUSCARPIPELINE);
      log.info(Constantes.SEPARADOR_DOS_LLAVES, "listaFinal[0]: ", listaFinal[0]);
      if (valor.equalsIgnoreCase(listaFinal[0])) {
        valorDocumento = listaFinal[1];
        break;
      }
    }
    log.info(Constantes.SEPARADOR_DOS_LLAVES, "valorDocumento: ", valorDocumento);
    return valorDocumento;
  }

  public static Date getUtilDate(String fecha, String formato) throws ParseException {
    if (StringUtils.isEmpty(fecha)) {
      return null;
    }
    return (new SimpleDateFormat(formato)).parse(fecha);
  }

  public static String getDateFormato(Date fecha, String formato) {
    if (fecha == null) {
      return "";
    }
    return (new SimpleDateFormat(formato)).format(fecha);
  }

  public static String getDateFormato(String fecha, String formato) {
    if (fecha == null || fecha.trim().equals("")) {
      return "";
    }
    return (new SimpleDateFormat(formato)).format(fecha);
  }

  public static String getDateFormato(Calendar fecha, String formato) {
    if (fecha == null) {
      return "";
    }
    return (new SimpleDateFormat(formato)).format(fecha.getTime());
  }

  public static String getDateFormato(long fecha, String formato) {
    return (new SimpleDateFormat(formato)).format(Long.valueOf(fecha));
  }

  public static boolean validarTransaccion(String valor, String lista) {
    boolean foco = false;
    log.info(Constantes.SEPARADOR_DOS_LLAVES, "lista: ", lista);
    String[] listaFinal = lista.split(",");
    log.info(Constantes.SEPARADOR_DOS_LLAVES, "valor: ", valor);
    for (String estadoFiltro : listaFinal) {
      log.info(Constantes.SEPARADOR_DOS_LLAVES, "estadoFiltro.toUpperCase(): ", estadoFiltro.toUpperCase());
      if (valor.toUpperCase().equalsIgnoreCase(estadoFiltro.toUpperCase())) {
        foco = true;
        break;
      }
    }
    return foco;
  }

  public static void inputOutputStoredProcedure(String trazabilidad, Object object)
      throws NoSuchFieldException, IllegalAccessException {

    Field[] fields = object.getClass().getDeclaredFields();

    for (Field item : fields) {
      Field field = object.getClass().getDeclaredField(item.getName());
      field.setAccessible(true);
      Object value = field.get(object);
      if (field.getType().getSimpleName().equalsIgnoreCase(Constantes.LIST)) {
        cursorStoredProcedure(trazabilidad, (ArrayList) value);
      } else {
        log.info(trazabilidad
            + String.format(Constantes.MOSTRAR_LOG_FORMAT, item.getName().toUpperCase(), value));
      }

    }

  }

  public static void cursorStoredProcedure(String trazabilidad, List<?> listOfObjectsbject) {

    if (null != listOfObjectsbject) {

      log.info(trazabilidad + Constantes.CANTIDAD_CURSOR + listOfObjectsbject.size());

      listOfObjectsbject.stream().filter(Objects::nonNull)
          .forEach(e -> log.info(trazabilidad + JAXBUtilitarios.objectToJson(trazabilidad, e)));

    }

  }

  public static ResponseStatusType setResponseStatus(ResponseStatusType responseStatus, String code, String message,
      String idTransaccion) {
    responseStatus.setIdTransaccion(idTransaccion);
    responseStatus.setCodigoRespuesta(code);
    responseStatus.setMensajeRespuesta(message);
    return responseStatus;
  }

  public static ClaroFault responseClaroFaultTypeDb(BDException e, HeaderRequest headerRequest) {
    ClaroFault responseError = new ClaroFault();
    responseError.setIdAudit(headerRequest.getIdTransaccion());
    responseError.setCodeError(e.getCodError());
    responseError.setDescriptionError(e.getMessage());
    responseError.setLocationError(e.getNombreSP());
    responseError.setDate(ClaroUtil.dateAString(new Date()));
    responseError.setOriginError(Constantes.TEXTO_VACIO);
    return responseError;
  }
}