package pe.com.claro.api.marcasespeciales.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesExternos {

  /************************
   * IDT *
   ************************/
  @Value("${codigo.idt1}")
  public String codigoIdt1;
  @Value("${mensaje.idt1}")
  public String mensajeIdt1;
  @Value("${codigo.idt2}")
  public String codigoIdt2;
  @Value("${mensaje.idt2}")
  public String mensajeIdt2;
  @Value("${codigo.idt3}")
  public String codigoIdt3;
  @Value("${mensaje.idt3}")
  public String mensajeIdt3;
  /************************
   * IDF *
   ************************/
  @Value("${codigo.idf0}")
  public String codigoIdf0;

  @Value("${mensaje.idf0}")
  public String mensajeIdf0;

  @Value("${codigo.idf1}")
  public String codigoIdf1;

  @Value("${mensaje.idf1}")
  public String mensajeIdf1;

  @Value("${codigo.idf2}")
  public String codigoIdf2;

  @Value("${mensaje.idf2}")
  public String mensajeIdf2;

  @Value("${codigo.idf3}")
  public String codigoIdf3;

  @Value("${mensaje.idf3}")
  public String mensajeIdf3;
  /************************
   * BD CLARIFY *
   ************************/

  @Value("${bd.siop.name}")
  public String bdSiopName;

  @Value("${bd.siop.owner}")
  public String bdSiopOwner;

  @Value("${spring.datasource.jndi.bd.timeai}")
  public String bdSiopJndi;

  @Value("${bd.siop.pkg.marcas.especiales}")
  public String bdSiopPkgMarcasEspeciales;

  @Value("${bd.siop.sp.marcas.especiales.update}")
  public String bdSiopSpMarcasEspecialesUpdate;

  @Value("${bd.siop.sp.marcas.especiales.update.conexion.timeout}")
  public String bdSiopSpMarcasEspecialesUpdateConexionTimeout;

  @Value("${bd.siop.sp.marcas.especiales.update.ejecucion.timeout}")
  public String bdSiopSpMarcasEspecialesUpdateEjecucionTimeout;

  @Value("${bd.siop.sp.marcas.especiales.indiv}")
  public String bdSiopSpMarcasEspecialesIndiv;

  @Value("${bd.siop.sp.marcas.especiales.indiv.conexion.timeout}")
  public String bdSiopSpMarcasEspecialesIndivConexionTimeout;

  @Value("${bd.siop.sp.marcas.especiales.indiv.ejecucion.timeout}")
  public String bdSiopSpMarcasEspecialesIndivEjecucionTimeout;

  @Value("${formato.fecha.vigencia}")
  public String formatoFechaVig;

  /************************
   * SERVICIO claro-fact-cyr-dim *
   ************************/
  @Value("${ws.cyr.dim.metodo}")
  public String wsCyrDimMetodo;
  @Value("${ws.cyr.dim.nombre}")
  public String wsCyrDimNombre;
  @Value("${ws.cyr.dim.url}")
  public String wsCyrDimsUrl;
  @Value("${ws.cyr.dim.timeout.connect}")
  public String wsCyrDimTimeoutConnect;
  @Value("${ws.cyr.dim.timeout.eject}")
  public String wsCyrDimTimeoutEjecucion;

  @Value("${dim.id.aplicacion}")
  public String idaplicacion;

  @Value("${registrar.programacion.intentos}")
  public String registrarReposicionReintentos;

  @Value("${dim.estado.enviado}")
  public String estadoEnviado;

  @Value("${dim.estado.error}")
  public String estadoError;

  @Value("${dim.mensaje.exito}")
  public String mensajeExito;

  @Value("${dim.mensaje.disponibilidad}")
  public String mensajeDisponibilidad;

  @Value("${dim.mensaje.timeout}")
  public String mensajeTimeout;

  @Value("${dim.action}")
  public String dimAction;

  @Value("${dim.me}")
  public String dimMe;

  @Value("${dim.entity}")
  public String dimEntity;

  @Value("${dim.priority}")
  public String dimPriority;

  @Value("${dim.proccess.key}")
  public String dimProccessKey;

  @Value("${dim.proccess.value}")
  public String dimProccessValue;

  @Value("${dim.requester}")
  public String dimRequester;

  @Value("${dim.tag.key}")
  public String dimTagKey;

  @Value("${dim.tag.value}")
  public String dimTagValue;

  @Value("${dim.tag.key.two}")
  public String dimTagKeyTwo;

}