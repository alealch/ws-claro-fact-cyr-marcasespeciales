package pe.com.claro.api.marcasespeciales.repository;

import pe.com.claro.api.marcasespeciales.exception.BDException;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialIndivResponse;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateRequest;
import pe.com.claro.api.marcasespeciales.repository.bean.SpMarcasEspecialUpdateResponse;

public interface SiopDbDao {

	public SpMarcasEspecialIndivResponse marcasEspecialesIndv(String mensajeLogMet,
			SpMarcasEspecialIndivRequest request) throws BDException;

	public SpMarcasEspecialUpdateResponse marcasEspecialesUpdate(String mensajeLogMet,
			SpMarcasEspecialUpdateRequest request) throws BDException;
}