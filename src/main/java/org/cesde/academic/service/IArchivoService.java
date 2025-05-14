package org.cesde.academic.service;

import org.cesde.academic.dto.request.ArchivoRequestDTO;
import org.cesde.academic.dto.response.ArchivoResponseDTO;

import java.util.List;

public interface IArchivoService {
    ArchivoResponseDTO createArchivo(ArchivoRequestDTO request);
    List<ArchivoResponseDTO> getArchivos();
    ArchivoResponseDTO getArchivoById(Integer id);
    List<ArchivoResponseDTO> getArchivosByUsuarioId(Integer usuarioId);
    List<ArchivoResponseDTO> getArchivosByNombreArchivo(String nombreArchivo);
    List<ArchivoResponseDTO> getArchivosByRutaArchivo(String rutaArchivo);
    ArchivoResponseDTO updateArchivo(Integer id, ArchivoRequestDTO request);
    void deleteArchivo(Integer id);
}
