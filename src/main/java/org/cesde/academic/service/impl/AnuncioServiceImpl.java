package org.cesde.academic.service.impl;

import org.cesde.academic.model.Anuncio;
import org.cesde.academic.repository.AnuncioRepository;
import org.cesde.academic.service.IAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioServiceImpl implements IAnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Override
    public Anuncio createAnuncio(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    @Override
    public List<Anuncio> getAnuncios() {
        return anuncioRepository.findAll();
    }

    @Override
    public Optional<Anuncio> getAnuncioById(Integer id) {
        return anuncioRepository.findById(id);
    }

    @Override
    public List<Anuncio> getAnunciosByClaseId(Integer claseId) {
        return anuncioRepository.findByClase_Id(claseId);
    }

    @Override
    public Anuncio updateAnuncio(Anuncio anuncio, Anuncio anuncioUpdated) {
        anuncioUpdated.setId(anuncio.getId()); // Solo actualizamos el id para que se realice un UPDATE y no un INSERT
        return anuncioRepository.save(anuncioUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteAnuncio(Anuncio anuncio) {
        anuncioRepository.delete(anuncio);
    }
}
