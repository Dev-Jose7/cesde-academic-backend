package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.CalificacionRequestDTO;
import org.cesde.academic.dto.request.FranjaHorariaRequestDTO;
import org.cesde.academic.dto.response.FranjaHorariaResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Calificacion;
import org.cesde.academic.model.FranjaHoraria;
import org.cesde.academic.repository.FranjaHorariaRepository;
import org.cesde.academic.service.IFranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FranjaHorariaServiceImpl implements IFranjaHorariaService {

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    @Override
    public FranjaHorariaResponseDTO createFranjaHoraria(FranjaHorariaRequestDTO request) {
        validateHoraInicioFin(request, null);
        FranjaHoraria franja = createEntity(request);
        return createResponse(franjaHorariaRepository.save(franja));
    }

    @Override
    public List<FranjaHorariaResponseDTO> getFranjasHorarias() {
        return createResponseList(franjaHorariaRepository.findAll());
    }

    @Override
    public FranjaHorariaResponseDTO getFranjaHorariaById(Integer id) {
        return createResponse(getFranjaByIdOrException(id));
    }

    @Override
    public List<FranjaHorariaResponseDTO> getFranjasByHoraInicio(LocalTime horaInicio) {
        return createResponseList(franjaHorariaRepository.findAllByHoraInicio(horaInicio));
    }

    @Override
    public List<FranjaHorariaResponseDTO> getFranjasByHoraFin(LocalTime horaFin) {
        return createResponseList(franjaHorariaRepository.findAllByHoraFin(horaFin));
    }

    @Override
    public FranjaHorariaResponseDTO updateFranjaHoraria(Integer id, FranjaHorariaRequestDTO request) {
        validateHoraInicioFin(request, id);

        FranjaHoraria original = getFranjaByIdOrException(id);
        FranjaHoraria updatedFranja = createEntity(request);

        original.setHoraInicio(request.getHoraInicio());
        original.setHoraFin(request.getHoraFin());

        return createResponse(franjaHorariaRepository.save(original));
    }

    @Override
    public void deleteFranjaHoraria(Integer id) {
        FranjaHoraria franja = getFranjaByIdOrException(id);
        franjaHorariaRepository.delete(franja);
    }

    // Utilidades

    private FranjaHoraria getFranjaByIdOrException(Integer id) {
        return franjaHorariaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Franja horaria no encontrada"));
    }

    private FranjaHorariaResponseDTO createResponse(FranjaHoraria franja) {
        return new FranjaHorariaResponseDTO(
                franja.getId(),
                franja.getHoraInicio(),
                franja.getHoraFin()
        );
    }

    private FranjaHoraria createEntity(FranjaHorariaRequestDTO request){
        FranjaHoraria franja = new FranjaHoraria();
        franja.setHoraInicio(request.getHoraInicio());
        franja.setHoraFin(request.getHoraFin());
        return franja;
    }

    private List<FranjaHorariaResponseDTO> createResponseList(List<FranjaHoraria> lista) {
        List<FranjaHorariaResponseDTO> response = new ArrayList<>();
        for (FranjaHoraria franja : lista) {
            response.add(createResponse(franja));
        }
        return response;
    }

    private void validateHoraInicioFin(FranjaHorariaRequestDTO request, Integer id) {
        boolean existe = (id == null)
                ? franjaHorariaRepository.existsByHoraInicioAndHoraFin(request.getHoraInicio(), request.getHoraFin())
                : franjaHorariaRepository.existsByHoraInicioAndHoraFinAndIdNot(request.getHoraInicio(), request.getHoraFin(), id);

        if(existe){
            throw new RecursoExistenteException("Ya existe otra franja horaria con esa hora de inicio y fin.");
        }

        //Este patrón que elegiste para validar unicidad es:
        //✅ Claro
        //✅ Eficiente
        //✅ Reutilizable
        //✅ Consistente con prácticas modernas de Spring Data JPA
    }
}
