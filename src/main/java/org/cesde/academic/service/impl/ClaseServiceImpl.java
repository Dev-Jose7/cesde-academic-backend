package org.cesde.academic.service.impl;

import org.cesde.academic.model.Clase;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseServiceImpl implements IClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public Clase createClase(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public List<Clase> getClases() {
        return claseRepository.findAll();
    }

    @Override
    public Optional<Clase> getClaseById(Integer id) {
        return claseRepository.findById(id);
    }

    @Override
    public List<Clase> getClasesByGrupoId(Integer grupoId) {
        return claseRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public List<Clase> getClasesByDocenteId(Integer docenteId) {
        return claseRepository.findByDocente_Id(docenteId);
    }

    @Override
    public List<Clase> getClasesByModuloId(Integer moduloId) {
        return claseRepository.findByModulo_Id(moduloId);
    }

    @Override
    public Clase updateClase(Clase clase, Clase claseUpdated) {
        claseUpdated.setId(clase.getId());
        return claseRepository.save(claseUpdated);
    }

    @Override
    public void deleteClase(Clase clase) {
        claseRepository.delete(clase);
    }
}
