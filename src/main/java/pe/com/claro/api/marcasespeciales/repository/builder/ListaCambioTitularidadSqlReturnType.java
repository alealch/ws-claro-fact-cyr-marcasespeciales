package pe.com.claro.api.marcasespeciales.repository.builder;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnType;

import pe.com.claro.api.marcasespeciales.properties.Constantes;
import pe.com.claro.api.marcasespeciales.repository.bean.BeanCambioTitularidad;

public class ListaCambioTitularidadSqlReturnType implements SqlReturnType {
  private final Logger logger = LoggerFactory.getLogger(ListaCambioTitularidadSqlReturnType.class);
  private String msgId;

  public ListaCambioTitularidadSqlReturnType(String msgId) {
    this.msgId = msgId;
  }

  @SuppressWarnings("rawtypes")
  private RowMapper rowMapper = new RowMapper<BeanCambioTitularidad>() {

    public BeanCambioTitularidad mapRow(ResultSet rs, int numeroFila) throws SQLException {
      BeanCambioTitularidad lineas = new BeanCambioTitularidad();
      lineas.setId(rs.getString("MAEHV_ID"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAEHV_ID]: ", lineas.getId());
      lineas.setCodigo(rs.getString("MAESV_CODIGO"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAESV_CODIGO]: ", lineas.getCodigo());
      lineas.setValor(rs.getString("MAEHV_VALOR"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAEHV_VALOR]: ", lineas.getValor());
      lineas.setEntidadId(rs.getString("MAEHV_ENTIDADID"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAEHV_ENTIDADID]: ", lineas.getEntidadId());
      lineas.setFacturador(rs.getString("MAEHV_FACTURADOR"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAEHV_FACTURADOR]: ", lineas.getFacturador());
      lineas.setEntidad(rs.getString("MAESV_ENTIDAD"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAESV_ENTIDAD]: ", lineas.getEntidad());
      lineas.setFecVigInicio(rs.getDate("MAESD_FECVIGINICIO"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAESD_FECVIGINICIO]: ", lineas.getFecVigInicio());
      lineas.setFecVigFin(rs.getDate("MAESD_FECVIGFIN"));
      logger.info(Constantes.SEPARADOR_DOS_LLAVES, "[MAESD_FECVIGFIN]: ", lineas.getFecVigFin());
      return lineas;
    }
  };

  @Override
  public Object getTypeValue(CallableStatement cs, int ix, int sqlType, String typeName) throws SQLException {

    ResultSet rs = null;
    List<Object> lista = null;

    try {
      rs = (ResultSet) cs.getObject(ix);
      if (rs != null) {

        lista = new ArrayList<>();
        for (int i = 0; rs.next(); i++) {
          lista.add(rowMapper.mapRow(rs, i));
        }
      }
      return lista;
    } catch (SQLException e) {
      logger.info(Constantes.SEPARADOR_TRES_LLAVES, msgId, "ERROR [SQLException]: ", e.getMessage());

      if ((e.getMessage() != null) && (e.getMessage().startsWith("Cursor is closed"))) {
        logger.info(Constantes.SEPARADOR_DOS_LLAVES, msgId, "[Cursor is closed]: ");

        return new ArrayList<>();
      } else {
        throw e;
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
    }
  }
}