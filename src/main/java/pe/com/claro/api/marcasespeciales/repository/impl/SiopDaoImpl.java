package pe.com.claro.api.marcasespeciales.repository.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.properties.PropertiesExternos;
import pe.com.claro.api.marcasespeciales.repository.SiopDbDao;
import pe.com.claro.api.marcasespeciales.repository.bean.BeanCambioTitularidad;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivResponse;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateResponse;
import pe.com.claro.api.marcasespeciales.repository.builder.ListaCambioTitularidadSqlReturnType;
import pe.com.claro.api.marcasespeciales.util.ClaroUtil;

@Repository
public class SiopDaoImpl implements SiopDbDao {
  private static final Logger logger = LoggerFactory.getLogger(SiopDaoImpl.class);

  @Autowired
  @Qualifier("dataSourceSiop")
  private DataSource dataSourceSiop;
  @Autowired
  PropertiesExternos propiedadesExterna;

  @Override
  public SpMarcasEspecialIndivResponse marcasEspecialesIndv(String mensajeLogMet, SpMarcasEspecialIndivRequest request)
      throws BDException {
    long tiempoInicial = System.currentTimeMillis();
    String nombreMetodo = "[marcasEspecialesIndv] ";
    String mensajeLog = mensajeLogMet + nombreMetodo;
    logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, "==Inicio del metodo marcasEspecialesIndv");
    SpMarcasEspecialIndivResponse spMarcasEspecialIndivResponse = new SpMarcasEspecialIndivResponse();
    String database = propiedadesExterna.bdSiopName;
    String pkg = propiedadesExterna.bdSiopPkgMarcasEspeciales;
    String sp = propiedadesExterna.bdSiopSpMarcasEspecialesIndiv;
    String owner = propiedadesExterna.bdSiopOwner;
    String jndi = propiedadesExterna.bdSiopJndi;
    String timeOutConect = propiedadesExterna.bdSiopSpMarcasEspecialesIndivConexionTimeout;
    String timeOutEject = propiedadesExterna.bdSiopSpMarcasEspecialesIndivEjecucionTimeout;
    SimpleJdbcCall jdbcCall = null;
    Connection connection = null;
    StringBuilder storedProcedure = new StringBuilder();
    storedProcedure.append(pkg).append(Constantes.SEPARADORPUNTO).append(sp);
    try {
      ClaroUtil.inicioImpl(mensajeLog, nombreMetodo, database, storedProcedure.toString(), owner);
      connection = dataSourceSiop.getConnection();
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, "Consultando BD SIOP, con JNDI=", jndi);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, " PARAMETROS [INPUT]: ");
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAESV_CODIGO: ", request.getCodigo());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_ENTIDADID: ", request.getEntidadId());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_FACTURADOR: ", request.getFacturador());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_VALOR: ", request.getValor());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_USUARIO: ", request.getUsuarioMod());
      MapSqlParameterSource mapSqlParameterSource = (new MapSqlParameterSource())
          .addValue("PI_MAESV_CODIGO", request.getCodigo()).addValue("PI_MAEHV_ENTIDADID", request.getEntidadId())
          .addValue("PI_MAEHV_FACTURADOR", request.getFacturador()).addValue("PI_MAEHV_VALOR", request.getValor())
          .addValue("PI_MAEHV_USUARIO", request.getUsuarioMod());

      jdbcCall = new SimpleJdbcCall(this.dataSourceSiop);
      jdbcCall.withoutProcedureColumnMetaDataAccess().withSchemaName(owner)
          .withProcedureName(storedProcedure.toString().replace(Constantes.TEXTOESPACIO, Constantes.TEXTO_VACIO))
          .declareParameters(new SqlParameter("PI_MAESV_CODIGO", 12), new SqlParameter("PI_MAEHV_ENTIDADID", 12),
              new SqlParameter("PI_MAEHV_FACTURADOR", 12), new SqlParameter("PI_MAEHV_VALOR", 12),
              new SqlParameter("PI_MAEHV_USUARIO", 12),
              new SqlOutParameter("PO_CURSOR", -10, null, new ListaCambioTitularidadSqlReturnType(mensajeLog)),
              new SqlOutParameter("PO_CODERROR", 12), new SqlOutParameter(Constantes.PO_MENSAJE, 12));

      dataSourceSiop.setLoginTimeout(Integer.parseInt(timeOutConect));
      jdbcCall.getJdbcTemplate().setQueryTimeout(Integer.parseInt(timeOutEject));
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.TIME_CONECTION, timeOutConect);

      Map<String, Object> resultMap = jdbcCall.execute(mapSqlParameterSource);
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.TIME_EXECUTION, timeOutEject);

      spMarcasEspecialIndivResponse.setErrorCode(resultMap.get("PO_CODERROR").toString());
      spMarcasEspecialIndivResponse.setErrorMsg(
          (resultMap.get(Constantes.PO_MENSAJE) == null) ? "" : resultMap.get(Constantes.PO_MENSAJE).toString());
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, Constantes.PARAMETRO_OUT, " PO_CODERROR: ",
          spMarcasEspecialIndivResponse.getErrorCode());
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, Constantes.PARAMETRO_OUT,
          Constantes.PO_MENSAJE + ": ", spMarcasEspecialIndivResponse.getErrorMsg());
      List<BeanCambioTitularidad> listaPre = (List<BeanCambioTitularidad>) resultMap.get("PO_CURSOR");
      spMarcasEspecialIndivResponse.setListaPre(listaPre);

    } catch (Exception ex) {
      ClaroUtil.errorGenericoBD(ex, mensajeLog, sp, jndi, propiedadesExterna);
    } finally {
      ClaroUtil.cerrarConexiones(connection, storedProcedure, mensajeLog, null);
      ClaroUtil.finImpl(tiempoInicial, nombreMetodo, mensajeLog);
    }
    return spMarcasEspecialIndivResponse;
  }

  @Override
  public SpMarcasEspecialUpdateResponse marcasEspecialesUpdate(String mensajeLogMet,
      SpMarcasEspecialUpdateRequest request) throws BDException {
    long tiempoInicial = System.currentTimeMillis();
    String nombreMetodo = "[marcasEspecialesUpdate] ";
    String mensajeLog = mensajeLogMet + nombreMetodo;
    logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, "==Inicio del metodo marcasEspecialesUpdate");
    SpMarcasEspecialUpdateResponse spMarcasEspecialUpdateResponse = new SpMarcasEspecialUpdateResponse();
    String database = propiedadesExterna.bdSiopName;
    String pkg = propiedadesExterna.bdSiopPkgMarcasEspeciales;
    String sp = propiedadesExterna.bdSiopSpMarcasEspecialesUpdate;
    String owner = propiedadesExterna.bdSiopOwner;
    String jndi = propiedadesExterna.bdSiopJndi;
    String timeOutConect = propiedadesExterna.bdSiopSpMarcasEspecialesUpdateConexionTimeout;
    String timeOutEject = propiedadesExterna.bdSiopSpMarcasEspecialesUpdateEjecucionTimeout;
    SimpleJdbcCall jdbcCall = null;
    Connection connection = null;
    StringBuilder storedProcedure = new StringBuilder();
    storedProcedure.append(pkg).append(Constantes.SEPARADORPUNTO).append(sp);
    try {
      ClaroUtil.inicioImpl(mensajeLog, nombreMetodo, database, storedProcedure.toString(), owner);
      connection = dataSourceSiop.getConnection();
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, "Consultando BD SIOP, con JNDI=", jndi);
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, mensajeLog, " PARAMETROS [INPUT]: ");
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAESN_ID: ", request.getMaesnId());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHN_ESTADO: ", request.getEstado());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_ERROR: ", request.getErrorMensaje());
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, " PI_MAEHV_USUARIOMOD: ", request.getUsuarioMod());
      MapSqlParameterSource mapSqlParameterSource = (new MapSqlParameterSource())
          .addValue("PI_MAESN_ID", request.getMaesnId()).addValue("PI_MAEHN_ESTADO", request.getEstado())
          .addValue("PI_MAEHV_ERROR", request.getErrorMensaje())
          .addValue("PI_MAEHV_USUARIOMOD", request.getUsuarioMod());

      jdbcCall = new SimpleJdbcCall(this.dataSourceSiop);
      jdbcCall.withoutProcedureColumnMetaDataAccess().withSchemaName(owner)
          .withProcedureName(storedProcedure.toString().replace(Constantes.TEXTOESPACIO, Constantes.TEXTO_VACIO))
          .declareParameters(new SqlParameter("PI_MAESN_ID", 2), new SqlParameter("PI_MAEHN_ESTADO", 2),
              new SqlParameter("PI_MAEHV_ERROR", 12), new SqlParameter("PI_MAEHV_USUARIOMOD", 12),
              new SqlOutParameter(Constantes.PO_CODIGO_ERROR, 12), new SqlOutParameter(Constantes.PO_MENSAJE, 12));

      dataSourceSiop.setLoginTimeout(Integer.parseInt(timeOutConect));
      jdbcCall.getJdbcTemplate().setQueryTimeout(Integer.parseInt(timeOutEject));
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.TIME_CONECTION, timeOutConect);

      Map<String, Object> resultMap = jdbcCall.execute(mapSqlParameterSource);
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, mensajeLog, Constantes.TIME_EXECUTION, timeOutEject);

      spMarcasEspecialUpdateResponse.setCode(resultMap.get(Constantes.PO_CODIGO_ERROR).toString());
      spMarcasEspecialUpdateResponse.setMensaje(
          (resultMap.get(Constantes.PO_MENSAJE) == null) ? "" : resultMap.get(Constantes.PO_MENSAJE).toString());
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, "Parametero OUT : ", " PO_CODIGO_ERROR: ",
          spMarcasEspecialUpdateResponse.getCode());
      logger.info(Constantes.SEPARADOR_CUATRO_LLAVES, mensajeLog, "Parametero OUT : ", Constantes.PO_MENSAJE + ": ",
          spMarcasEspecialUpdateResponse.getMensaje());

    } catch (Exception ex) {
      ClaroUtil.errorGenericoBD(ex, mensajeLog, sp, jndi, propiedadesExterna);
    } finally {
      ClaroUtil.cerrarConexiones(connection, storedProcedure, mensajeLog, null);
      ClaroUtil.finImpl(tiempoInicial, nombreMetodo, mensajeLog);
    }
    return spMarcasEspecialUpdateResponse;
  }

}