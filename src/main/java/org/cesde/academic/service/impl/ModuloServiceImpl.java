package org.cesde.academic.service.impl;

import org.cesde.academic.model.Modulo;
import org.cesde.academic.repository.ModuloRepository;
import org.cesde.academic.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements IModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public Modulo createModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @Override
    public List<Modulo> getModulos() {
        return moduloRepository.findAll();
    }

    @Override
    public Optional<Modulo> getModuloById(Integer id) {
        return moduloRepository.findById(id);
    }

    @Override
    public Optional<Modulo> getModuloByNombre(String nombre) {
        return moduloRepository.findByNombre(nombre);
    }

    @Override
    public List<Modulo> getModulosByProgramaId(Integer programaId) {
        return moduloRepository.findByPrograma_Id(programaId);
    }

    @Override
    public Modulo updateModulo(Modulo modulo, Modulo moduloUpdated) {
        moduloUpdated.setId(modulo.getId());
        return moduloRepository.save(moduloUpdated);
    }

    @Override
    public void deleteModulo(Modulo modulo) {
        moduloRepository.delete(modulo);
    }
}
