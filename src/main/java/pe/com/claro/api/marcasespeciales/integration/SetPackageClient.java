package pe.com.claro.api.marcasespeciales.integration;

import pe.com.claro.api.marcasespeciales.canonical.comun.HeaderRequest;
import pe.com.claro.api.marcasespeciales.exception.WSException;
import pe.com.claro.api.marcasespeciales.integration.bean.PackageResponse;

public interface SetPackageClient {

      PackageResponse setPackage(String mensajeTransaccion, String request, HeaderRequest header)
                  throws WSException;

}