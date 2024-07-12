package pe.com.claro.api.marcasespeciales.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.api.marcasespeciales.controller.ServletInitializer;

public class ServletInitializerTest {

    private static final Logger logger = LoggerFactory.getLogger(ServletInitializerTest.class);

    @Test
    public void getProperties() {
        assertNotNull(ServletInitializer.getProperties().toString());

    }
}
