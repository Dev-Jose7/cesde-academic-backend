package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.DiaRequestDTO;
import org.cesde.academic.dto.response.DiaResponseDTO;
import org.cesde.academic.enums.NombreDia;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Dia;
import org.cesde.academic.repository.DiaRepository;
import org.cesde.academic.service.IDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaServiceImpl implements IDiaService {

    @Autowired
    private DiaRepository diaRepository;

    @Override
    public DiaResponseDTO createDia(DiaRequestDTO request) {
        Dia dia = createEntity(request);
        return createResponse(diaRepository.save(dia));
    }

    @Override
    public List<DiaResponseDTO> getDias() {
        return createResponseList(diaRepository.findAll());
    }

    @Override
    public DiaResponseDTO getDiaById(Integer id) {
        Dia dia = getDiaByIdOrException(id);
        return createResponse(dia);
    }

    @Override
    public DiaResponseDTO getDiaByNombre(NombreDia nombre) {
        Dia dia = diaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RecursoNoEncontradoException("Día no encontrado"));
        return createResponse(dia);
    }

    @Override
    public DiaResponseDTO updateDia(Integer id, DiaRequestDTO request) {
        Dia original = getDiaByIdOrException(id);
        Dia updatedDia = createEntity(request);

        updatedDia.setId(original.getId());
        return createResponse(diaRepository.save(updatedDia));
    }

    @Override
    public void deleteDia(Integer id) {
        Dia dia = getDiaByIdOrException(id);
        diaRepository.delete(dia);
    }

    private Dia getDiaByIdOrException(Integer id) {
        return diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Día no encontrado"));
    }

    private void validateDia(NombreDia dia){
        boolean existe = diaRepository.existsByNombre(dia);
        if(existe){
            throw new RecursoExistenteException("Este día ya se encuentra registrado");
        }
    }

    private Dia createEntity(DiaRequestDTO request){
        validateDia(request.getNombre());

        Dia dia = new Dia();
        dia.setNombre(request.getNombre());
        return dia;
    }

    private DiaResponseDTO createResponse(Dia dia) {
        return new DiaResponseDTO(dia.getId(), dia.getNombre());
    }

    private List<DiaResponseDTO> createResponseList(List<Dia> dias) {
        List<DiaResponseDTO> list = new ArrayList<>();
        for (Dia dia : dias) {
            list.add(createResponse(dia));
        }
        return list;
    }
}
