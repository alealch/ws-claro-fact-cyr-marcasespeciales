package pe.com.claro.api.marcasespeciales.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Utilitarios {

  private Utilitarios() {
    super();
  }

  private static final Logger logger = Logger.getLogger(Utilitarios.class);

  public static void close(String msgId, Connection c) throws SQLException {
    if (c != null) {
      c.close();
      logger.info(msgId + " Connection Cerrado");
    }
  }

  public static void close(String msgId, CallableStatement cs) throws SQLException {
    if (cs != null) {
      cs.close();
      logger.info(msgId + " CallableStatement Cerrado");
    }
  }

  public static void close(String msgId, ResultSet rs) throws SQLException {
    if (rs != null) {
      rs.close();
      logger.info(msgId + " ResultSet Cerrado");
    }
  }

}