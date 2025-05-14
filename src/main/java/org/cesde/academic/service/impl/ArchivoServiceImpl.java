package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ArchivoRequestDTO;
import org.cesde.academic.dto.response.ArchivoResponseDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Archivo;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ArchivoRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ArchivoResponseDTO createArchivo(ArchivoRequestDTO request) {
        Archivo archivo = createEntity(request);
        return createResponse(archivoRepository.save(archivo));
    }

    @Override
    public List<ArchivoResponseDTO> getArchivos() {
        return createResponseList(archivoRepository.findAll());
    }

    @Override
    public ArchivoResponseDTO getArchivoById(Integer id) {
        return createResponse(getArchivoByIdOrException(id));
    }

    @Override
    public List<ArchivoResponseDTO> getArchivosByUsuarioId(Integer usuarioId) {
        return createResponseList(archivoRepository.findByUsuarioId(usuarioId));
    }

    @Override
    public List<ArchivoResponseDTO> getArchivosByNombreArchivo(String nombreArchivo) {
        return createResponseList(archivoRepository.findAllByNombreArchivoContainingIgnoreCase(nombreArchivo));
    }

    @Override
    public List<ArchivoResponseDTO> getArchivosByRutaArchivo(String rutaArchivo) {
        return createResponseList(archivoRepository.findAllByRutaArchivoContainingIgnoreCase(rutaArchivo));
    }

    @Override
    public ArchivoResponseDTO updateArchivo(Integer id, ArchivoRequestDTO request) {
        Archivo original = getArchivoByIdOrException(id);
        Archivo updated = createEntity(request);
        updated.setId(original.getId());
        updated.setCreado(original.getCreado());
        return createResponse(archivoRepository.save(updated));
    }

    @Override
    public void deleteArchivo(Integer id) {
        Archivo archivo = getArchivoByIdOrException(id);
        archivoRepository.delete(archivo);
    }

    // Métodos auxiliares

    private Archivo getArchivoByIdOrException(Integer id) {
        return archivoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Archivo no encontrado"));
    }

    private Archivo createEntity(ArchivoRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        Archivo archivo = new Archivo();
        archivo.setUsuario(usuario);
        archivo.setNombreArchivo(request.getNombreArchivo());
        archivo.setRutaArchivo(request.getRutaArchivo());
        archivo.setFechaSubida(request.getFechaSubida());
        return archivo;
    }

    private ArchivoResponseDTO createResponse(Archivo archivo) {
        return new ArchivoResponseDTO(
                archivo.getId(),
                archivo.getUsuario().getNombre(),
                archivo.getNombreArchivo(),
                archivo.getRutaArchivo(),
                archivo.getFechaSubida(),
                archivo.getCreado(),
                archivo.getActualizado()
        );
    }

    private List<ArchivoResponseDTO> createResponseList(List<Archivo> archivos) {
        List<ArchivoResponseDTO> responseList = new ArrayList<>();
        for (Archivo archivo : archivos) {
            responseList.add(createResponse(archivo));
        }
        return responseList;
    }
}
