package pe.com.claro.api.marcasespeciales.properties;

import java.math.BigDecimal;

public class Constantes {

  private Constantes() {
    super();
  }

  public static final String SQL_TIMEOUTEXCEPTION = "SQLTIMEOUTEXCEPTION";

  public static final String SEPARADOR_SEIS_LLAVES = "{}{}{}{}{}{}";
  public static final String SEPARADOR_CINCO_LLAVES = "{}{}{}{}{}";
  public static final String SEPARADOR_CUATRO_LLAVES = "{}{}{}{}";
  public static final String SEPARADOR_TRES_LLAVES = "{}{}{}";
  public static final String SEPARADOR_DOS_LLAVES = "{}{}";

  public static final String CONSULTAR_NOTIFICACION_CARTA = "enviarMarcaDCMS";

  public static final String CORCHETE = "]";
  public static final String PARAMETROSOBLIGATORIOS = "----0. Validar parametros obligatorios ------";

  public static final String SALTO_LINEA = "\n";
  public static final String CONTENTTYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";
  public static final String VARIABLE_SP = "$sp";
  public static final String MSG_ID = " msgid=";

  public static final String LEFT_CLASP = " [";
  public static final String EX_CON_CORCHETE = "$ex";
  public static final String VALIDACIONCORRECTA = "[Validacion es Correcta]";

  public static final String RIGHT_CLASP = "]";
  public static final String VERSION = "1.0.0";
  public static final String EJBEXCEPTION = "EJBException ";
  public static final String MENSAJEIDF = "[IDF CONTROLADO]: ";
  public static final String HEADER_PARAMS = " Parametros del Header:";
  public static final String EMPTY_TEXT = "";
  public static final String BODY_VALIDATION = "Validacion del Header/Body";

  public static final String FORMATOFECHAZH = "yyyyMMdd'T'HH:mm:ssZZZZ";
  public static final String FORMATOGTM = "America/Lima";
  public static final String MENSAJERROR = "[ERROR]: ";
  public static final String MENSAJERPTA = "[MENSAJE]: ";
  public static final String RESPUESTA = "RESPUESTA: ";
  public static final String ERROREX = "[$ex]";
  public static final String MENSAJE = "mensaje";
  public static final String MILISEGUNDOS = "milisegundos";
  public static final String SPACE = " ";
  public static final String FINMETODO = "[ FIN de metodo ";
  public static final String TIEMPOTOTAL = " ] Tiempo total de proceso (ms):";
  public static final String DEVOLVERRESPONSE = " Response a devolver:";

  public static final String METODOINICIO = " Inicio Metodo: ";
  public static final String METODO = "Metodo :";
  public static final String NOMBRERECURSO = "claro-fact-cyr-marcasEspeciales";

  public static final String DATAAUDIT = "dataAudit";
  public static final String SEPARADOR = "=========================================================================================";

  public static final String CHARSET = ";charset=UTF-8";
  public static final String HEADERREQUEST = "Header Request: ";

  public static final String PARAMETROSENTRADA = "Parametros de entrada: ";
  public static final String ID_TXT = " idTx=";

  public static final String TIEMPO_TOTAL = " ] Tiempo total de proceso (ms): ";
  public static final String PARAMETROSHEADER = " Header Request:";
  public static final String PARAMETROSBODY = "Body Request: ";
  public static final String VALIDACIONPARAMETROSCORRECTOS = " Validacion correcta de parametros de entrada";
  public static final String VALIDACIONPARAMETROSINCORRECTOS = " Parametros de entrada no cumple validacion";
  public static final String DATOSSALIDA = "Datos de salida: ";
  public static final String DATOSENVIADO = "Datos Enviados a Servicio :";
  public static final String VALORESRESPUESTA = "Valores de Respuesta : ";

  public static final String SEPARADORPUNTO = ".";
  public static final String FORMATOFECHACABECERA = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public static final String FORMATOFECHABALANCE = "yyyyMMdd'T'HH:mm:ssZ";
  public static final String FORMATOFECHA = "dd-MM-yyyy";
  public static final String FORMATOFECHAHHMMSS = "ddMMyyyy HH:mm:ss";

  public static final String IDTRANSACCION = "idTransaccion";
  public static final String APLICACION = "aplicacion";
  public static final String IP_APLICACION = "ipAplicacion";

  public static final String MSGID = "msgid";
  public static final String USRID = "userId";

  public static final String CANAL = "canal";
  public static final String IDAPLICACION = "idAplicacion";
  public static final String USUARIOAPLICACION = "usuarioAplicacion";
  public static final String USUARIOSESION = "usuarioSesion";
  public static final String IDTRANSACCIONNEGOCIO = "idTransaccionNegocio";
  public static final String FECHAINICIO = "fechaInicio";

  public static final String NODOADICIONAL = "nodoAdicional";
  public static final String TIMESTAMP = "timestamp";
  public static final String ACCEPT = "accept";

  public static final String NOMBAPLICACION = "nombreAplicacion";
  public static final String ACCEPTT = "Accept";
  public static final String API = "api";
  public static final String CHAR_CORCHETE_IZQUIERDO = " [";

  public static final String PROPERTIESINTERNOS = "config.properties";
  public static final String PROPERTIESEXTERNOS = ".properties";
  public static final String PROPERTIESKEY = "claro.properties";
  public static final String CONSTANTENOJNDI = "javax.persistence.PersistenceException";
  public static final String CONSTANTENOJNDIWS = "MessageBodyProviderNotFoundException";
  public static final String CONSTANTETIMEOUTWS = "java.net.SocketTimeoutException";
  public static final int NUM_ZERO = 0;
  public static final int NUM_UNO = 1;
  public static final int NUM_SEIS = 6;
  public static final int NUM_SIETE = 7;

  public static final String INICIO_METODO = " INICIO METODO: ";
  public static final String FIN_METODO = " FIN METODO: ";
  public static final String FININSUMO = " FIN INSUMO: ";
  public static final String INI = " INICIO: ";
  public static final String FIN = " FIN: ";
  public static final String ESPACIO = " ";
  public static final String IGUAL = "=";
  public static final String BUSCARPIPELINE = "\\|";
  public static final String EMPTY = "";
  public static final String CHAR_CORCHETE_DERECHO = "] ";
  public static final String MILISEG = " milisegundos.";
  public static final String INI_CORCHETE1 = " 1. [";
  public static final String PALOTE = "|";
  public static final String PUNTO_COMA = ";";
  public static final String TEXTOESPACIO = " ";
  public static final String EXCEPTIONWSNODISPONIBLE01 = "RemoteAccessException";
  public static final String EXCEPTIONWSNODISPONIBLE02 = "404";
  public static final String EXCEPTIONWSNODISPONIBLE03 = "WebServiceException";
  public static final String EXCEPTIONWSNODISPONIBLE04 = "ConnectException";
  public static final String TIMEOUTEXCEPTION = "Timeout";
  public static final String TIMEOUTEXCEPTION2 = "Timed out";
  public static final String EXCEPTION_WS_TIMEOUT_01 = "timed out";
  public static final String EXCEPTION_WS_TIMEOUT_02 = "SocketTimeoutException";
  public static final String SWAGGERJAXRSCONFIG = "SwaggerJaxrsConfig";
  public static final String URLSWAGGERJAXRSCONFIG = "/SwaggerJaxrsConfig";
  public static final String HTML5CORSFILTER = "HTML5CorsFilter";
  public static final String URLPATTERNS = "/api/*";
  public static final String ACCESSCONTROLALLOWORIGIN = "Access-Control-Allow-Origin";
  public static final String ACCESSCONTROLALLOWMETHODS = "Access-Control-Allow-Methods";
  public static final String ACCESSCONTROLALLOWHEADERS = "Access-Control-Allow-Headers";
  public static final String ASTERISCO = "*";
  public static final String METODOSPERMITIDOS = "GET, POST, DELETE, PUT";

  public static final String ERROR_EXCEPTION = "Error Exception: ";
  public static final String TEXTO_VACIO = "";

  public static final String SALTOLINEA = "\n";
  public static final char PUNTO = '.';
  public static final int NZ_UNO = -1;
  public static final int NZ_DOS = -2;
  public static final int NUM_TRES = 3;
  public static final int LINEA_INACTIVA = 3;
  public static final BigDecimal BGCERO = new BigDecimal("0");

  public static final String RESP_SIN_DATOS = "Response sin datos";

  public static final String EJECUTO_CORRECTO = "Se ejecuto correctamente";
  public static final String ERROR_EJECUCION_SP = " Error en la ejecucion del SP : ";
  public static final String CODE = " [CODE]= ";
  public static final String MSG = " [MSG]= ";
  public static final String TRACE = " [TRACE] ";

  public static final String HIBERNATEJDBCEXCEPTION = "The application must supply JDBC connections";
  public static final String GENERICJDBCEXCEPTION = "org.hibernate.exception";
  public static final String ORAEXCEPTION = "ORA-06576";
  public static final String SQLEXCEPTION = "SQLException";
  public static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";
  public static final String CANNOT_GET_CONNECTION = "Cannot get connection: ";
  public static final String JNDI = " JNDI: ";
  public static final String OWNER = " OWNER: ";
  public static final String PACKAGE = " PACKAGE: ";
  public static final String STORED_SP = " STORED PROCEDURE: ";
  public static final String READTIMEOUT = " READ TIMEOUT: ";
  public static final String BD = " BD: ";
  public static final String PARAM_INPUT_SP = " PARAMETROS SP [INPUT]:";
  public static final String PARAM_OUTPUT_SP = " PARAMETROS SP [Output]: ";
  public static final String TIEMPO_TOTAL_SP = " Tiempo total de proceso del llamado del SP (ms): ";
  public static final String EJECUTAR_SP = " Ejecutando SP : ";
  public static final String CALL_SP = "call ";
  public static final String PARAMETRO_CALL = "{call ";
  public static final String PARAMETRO_INICIAL_JDBC = "jdbc:oracle";
  public static final String VARIABLE_JNDI = "$jndi";

  public static final String INICIO_ACTIVIDAD_CORCHETE = "[INICIO ACTIVIDAD: ";
  public static final String FIN_ACTIVIDAD_CORCHETE = "[FIN ACTIVIDAD: ";

  public static final String CONNECT_TIMEOUT_SB = "CONNECT_TIMEOUT";
  public static final String REQUEST_TIMEOUT_SB = "REQUEST_TIMEOUT";
  public static final String NOTNULL = "El campo debe de tener un valor";
  public static final String INICIO_ACTIVIDAD = "====[INICIO ACTIVIDAD]: ";
  public static final String SEPARADOR_ACTIVIDAD = " ========================";
  public static final String FIN_ACTIVIDAD = "====[FIN ACTIVIDAD]: ";

  public static final String ERROR_CONVERSION = "Error en la conversion ... ";
  public static final String VALUE_COR = "] value = [";
  public static final String COR_DERECHA = "] ";
  public static final String ERROR_PARSE_XML = "Error parseando object to xml:";
  public static final String COR_GUION = "] - [";
  public static final String GUION = " - ";
  public static final String ERROR_COR = "ERROR: [";
  public static final String CONECTIONSERVICIO = "Conectando con el servicio";
  public static final String TIEMPO_TOTAL_PROCESO = " Tiempo total de proceso (ms): ";

  public static final String CERO = "0";
  public static final String UNO = "1";
  public static final String DOS = "2";

  public static final String TIME_CONECTION = "[TimeConection] ";
  public static final String TIME_EXECUTION = "[TimeExecution] ";
  public static final String CONEXION_CERRADA_EXITOSAMENTE_PS = " Conexion Cerrada exitosamente PS ";
  public static final String CONEXION_CERRADA_EXITOSAMENTE_CONN = " Conexion Cerrada exitosamente CONN ";
  public static final String ERROR_CERRAR_RECURSOS_SP = "******** Error al cerrar los recursos usados en la ejecucion del SP : ";
  public static final String PARA_MAYOR_DETALLE_ORIGINAL = ", para mayor detalle ver la excepcion original: ";
  public static final String VARIABLE_REPLACE = "%s";
  public static final String SEPARADOR_MEDIO = "=====================================";
  public static final String SEPARADOR_CHICO = "================";

  public static final String PO_CODIGO_ERROR = "PO_CODERROR";
  public static final String PO_MENSAJE = "PO_ERROR";

  public static final String V_FORMATO_FECHA = "dd/MM/yyyy";
  public static final String V_FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

  public static final String PARAMETROS_BODY_RESPONSE = " Body Response: ";
  public static final String TIEMPOTOTALPROCESO = "Tiempo TOTAL del proceso: [";

  public static final String ACTIVIDAD_1 = "[ACTIVIDAD N ][1.0. Validar campos de entrada]";
  public static final String ACTIVIDAD_2 = "[ACTIVIDAD N ][2.0. Registrar Marca Especial]";
  public static final String ACTIVIDAD_3 = "[ACTIVIDAD N ][3.0. Enviar Marca Especial a DIM]";
  public static final String ACTIVIDAD_4 = "[ACTIVIDAD N ][4.0. Actualizar estado de Marca Especial]";

  public static final String VARIABLE = "[variable]";
  public static final String VARIABLE_CAMPO = "[campo]";

  public static final String LIST = "LIST";
  public static final String MOSTRAR_LOG_FORMAT = "[%s] = %s";
  public static final String CANTIDAD_CURSOR = "[CANTIDAD LISTA]: ";

  public static final String ERROR_INESPERADO = "Ocurrio un error inesperado en la ejecucion del metodo ";
  public static final String INTENTO = "Intento: ";
  public static final String PARAMETRO_OUT = "Parametero OUT : ";
}