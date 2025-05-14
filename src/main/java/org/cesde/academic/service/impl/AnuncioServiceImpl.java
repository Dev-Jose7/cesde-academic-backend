package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.AnuncioRequestDTO;
import org.cesde.academic.dto.response.AnuncioResponseDTO;
import org.cesde.academic.dto.response.ClaseResponseInfoDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Anuncio;
import org.cesde.academic.model.Clase;
import org.cesde.academic.repository.AnuncioRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.service.IAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioServiceImpl implements IAnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public AnuncioResponseDTO createAnuncio(AnuncioRequestDTO request) {
        Anuncio anuncio = createEntity(request);
        return createResponse(anuncioRepository.save(anuncio));
    }

    @Override
    public List<AnuncioResponseDTO> getAnuncios() {
        return createResponseList(anuncioRepository.findAll());
    }

    @Override
    public AnuncioResponseDTO getAnuncioById(Integer id) {
        return createResponse(getAnuncioByIdOrException(id));
    }

    @Override
    public List<AnuncioResponseDTO> getAnunciosByClaseId(Integer claseId) {
        return createResponseList(anuncioRepository.findAllByClaseId(claseId));
    }

    @Override
    public List<AnuncioResponseDTO> getAnunciosByTitulo(String titulo) {
        return createResponseList(anuncioRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @Override
    public List<AnuncioResponseDTO> getAnunciosByFechaRange(LocalDateTime desde, LocalDateTime hasta) {
        return createResponseList(anuncioRepository.findAllByFechaBetween(desde, hasta));
    }

    @Override
    public AnuncioResponseDTO updateAnuncio(Integer id, AnuncioRequestDTO request) {
        Anuncio original = getAnuncioByIdOrException(id);
        Anuncio updated = createEntity(request);
        updated.setId(original.getId());
        updated.setCreado(original.getCreado());
        return createResponse(anuncioRepository.save(updated));
    }

    @Override
    public void deleteAnuncio(Integer id) {
        Anuncio anuncio = getAnuncioByIdOrException(id);
        anuncioRepository.delete(anuncio);
    }

    // Métodos auxiliares

    private Anuncio getAnuncioByIdOrException(Integer id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Anuncio no encontrado"));
    }

    private Anuncio createEntity(AnuncioRequestDTO request) {
        Clase clase = claseRepository.findById(request.getClaseId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));

        Anuncio anuncio = new Anuncio();
        anuncio.setClase(clase);
        anuncio.setTitulo(request.getTitulo());
        anuncio.setMensaje(request.getMensaje());
        anuncio.setFecha(request.getFecha());
        return anuncio;
    }

    private AnuncioResponseDTO createResponse(Anuncio anuncio) {
        return new AnuncioResponseDTO(
                anuncio.getId(),
                createClaseInfo(anuncio),
                anuncio.getTitulo(),
                anuncio.getMensaje(),
                anuncio.getFecha(),
                anuncio.getCreado(),
                anuncio.getActualizado()
        );
    }

    private List<AnuncioResponseDTO> createResponseList(List<Anuncio> anuncios) {
        List<AnuncioResponseDTO> list = new ArrayList<>();
        for (Anuncio anuncio : anuncios) {
            list.add(createResponse(anuncio));
        }
        return list;
    }

    private ClaseResponseInfoDTO createClaseInfo(Anuncio anuncio){
        return new ClaseResponseInfoDTO(
                anuncio.getClase().getGrupo().getCodigo(),
                anuncio.getClase().getDocente().getNombre(),
                anuncio.getClase().getModulo().getNombre()
        );
    }
}
