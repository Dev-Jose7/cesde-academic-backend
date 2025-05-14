package org.cesde.academic.service;

import org.cesde.academic.dto.request.AnuncioRequestDTO;
import org.cesde.academic.dto.response.AnuncioResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IAnuncioService {
    AnuncioResponseDTO createAnuncio(AnuncioRequestDTO request);
    List<AnuncioResponseDTO> getAnuncios();
    AnuncioResponseDTO getAnuncioById(Integer id);
    List<AnuncioResponseDTO> getAnunciosByClaseId(Integer claseId);
    List<AnuncioResponseDTO> getAnunciosByTitulo(String titulo);
    List<AnuncioResponseDTO> getAnunciosByFechaRange(LocalDateTime desde, LocalDateTime hasta);
    AnuncioResponseDTO updateAnuncio(Integer id, AnuncioRequestDTO request);
    void deleteAnuncio(Integer id);
}
