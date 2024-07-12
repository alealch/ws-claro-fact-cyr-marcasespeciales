package pe.com.claro.api.marcasespeciales.util;

import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pe.com.claro.api.marcasespeciales.properties.Constantes;

/**
 * @author
 * @clase: JAXBUtilitarios.java
 * @descripcion Clase JAXBUtilitarios
 * @author_company: CLARO
 * @fecha_de_creacion: 01-10-2014
 * @fecha_de_ultima_actualizacion: 01-10-2014
 * @version 1.0
 */

public final class JAXBUtilitarios {

  private static final Logger wlLogger = LoggerFactory.getLogger(JAXBUtilitarios.class);

  @SuppressWarnings("rawtypes")
  private static HashMap<Class, JAXBContext> mapContexts = new HashMap<>();

  private JAXBUtilitarios() {
    super();
  }

  @SuppressWarnings("rawtypes")
  private static JAXBContext obtainJaxBContextFromClass(Class clas) {
    JAXBContext context;
    context = mapContexts.computeIfAbsent(clas, k -> null);

    try {
      wlLogger.info(Constantes.SEPARADOR_DOS_LLAVES, "Inicializando jaxcontext... para la clase ",
          clas.getName());
      context = JAXBContext.newInstance(clas);
      mapContexts.put(clas, context);
    } catch (Exception e) {
      wlLogger.error(Constantes.SEPARADOR_DOS_LLAVES, "Error creando JAXBContext:", e);
    }

    return context;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static String anyObjectToXmlText(Object objJaxB) {
    String commandoRequestEnXml = null;
    try {
      JAXBContext context = obtainJaxBContextFromClass(objJaxB.getClass());

      Marshaller marshaller = context.createMarshaller();

      StringWriter xmlWriter = new StringWriter();
      marshaller.marshal(
          new JAXBElement(new QName("", objJaxB.getClass().getName()), objJaxB.getClass(), objJaxB),
          xmlWriter);

      XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());

      commandoRequestEnXml = xmlObj.toString();
    } catch (Exception e) {
      wlLogger.error(Constantes.SEPARADOR_DOS_LLAVES, Constantes.ERROR_PARSE_XML, e);
    }
    return commandoRequestEnXml;
  }

  public static String getXmlTextFromJaxB(Object anyObject) {
    String commandoRequestEnXml = null;
    try {
      JAXBContext context = obtainJaxBContextFromClass(anyObject.getClass());
      Marshaller marshaller = context.createMarshaller();
      StringWriter xmlWriter = new StringWriter();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(new JAXBElement(new QName("", anyObject.getClass().getSimpleName()),
          anyObject.getClass(), anyObject), xmlWriter);
      commandoRequestEnXml = xmlWriter.toString();
    } catch (Exception e) {
      wlLogger.error(Constantes.SEPARADOR_TRES_LLAVES, "ERROR[XMLException]: ", e.getMessage(), e);
    }
    return commandoRequestEnXml;
  }

  public static String objectToJson(String msjTx, Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(obj);
    } catch (Exception e) {
      wlLogger.error(Constantes.SEPARADOR_TRES_LLAVES, msjTx, "Ocurrio un error al convertir el objeto en json: ",
          e);
    }
    return Constantes.TEXTO_VACIO;
  }
}