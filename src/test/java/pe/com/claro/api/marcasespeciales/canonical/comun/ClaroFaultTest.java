package pe.com.claro.api.marcasespeciales.canonical.comun;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pe.com.claro.api.marcasespeciales.canonical.comun.ClaroFault;

public class ClaroFaultTest {

    @Test
    public void getProperties() {
        String originError = "originError";
        String date = "14/11/2023";
        String locationError = "locationError";
        String descriptionError = "descriptionError";
        String codeError = "codeError";
        String idAudit = "idAudit";
        ClaroFault cf = new ClaroFault();
        cf.setOriginError(originError);
        cf.setDate(date);
        cf.setLocationError(locationError);
        cf.setDescriptionError(descriptionError);
        cf.setCodeError(codeError);
        cf.setIdAudit(idAudit);
        assertNotNull(cf.getOriginError());
    }

}
