package pe.com.claro.api.marcasespeciales.controller;

import java.io.File;

import java.util.Properties;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import pe.com.claro.api.marcasespeciales.Application;
import pe.com.claro.api.marcasespeciales.properties.Constantes;

public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class).properties(getProperties());
  }

  public static Properties getProperties() {
    Properties props = new Properties();

    String pathClaroProp = System.getProperty(Constantes.PROPERTIESKEY) + Constantes.NOMBRERECURSO + File.separator
        + "application.properties";

    props.put("spring.config.location", "file:" + pathClaroProp);
    return props;
  }

}
