package org.cesde.academic.service;

import org.cesde.academic.model.Anuncio;

import java.util.List;
import java.util.Optional;

public interface IAnuncioService {

    Anuncio createAnuncio(Anuncio anuncio);
    List<Anuncio> getAnuncios();
    Optional<Anuncio> getAnuncioById(Integer id);
    List<Anuncio> getAnunciosByClaseId(Integer claseId);
    Anuncio updateAnuncio(Anuncio anuncio, Anuncio anuncioUpdated);
    void deleteAnuncio(Anuncio anuncio);
}
