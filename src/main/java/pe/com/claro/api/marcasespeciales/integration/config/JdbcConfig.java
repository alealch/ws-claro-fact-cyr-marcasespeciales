package pe.com.claro.api.marcasespeciales.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

  @Value("${spring.datasource.jndi.bd.timeai}")
  private String siopJndiName;

  private JndiDataSourceLookup lookup = new JndiDataSourceLookup();

  @Bean(name = "dataSourceSiop", destroyMethod = "")
  public DataSource siopJndiName() {
    return lookup.getDataSource(siopJndiName);
  }

}