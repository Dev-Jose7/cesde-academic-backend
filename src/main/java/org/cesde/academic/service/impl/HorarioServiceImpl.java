package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.HorarioRequestDTO;
import org.cesde.academic.dto.response.HorarioResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Dia;
import org.cesde.academic.model.FranjaHoraria;
import org.cesde.academic.model.Horario;
import org.cesde.academic.repository.DiaRepository;
import org.cesde.academic.repository.FranjaHorariaRepository;
import org.cesde.academic.repository.HorarioRepository;
import org.cesde.academic.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioServiceImpl implements IHorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    @Override
    public HorarioResponseDTO createHorario(HorarioRequestDTO request) {
        Horario horario = createEntity(request, null);
        return createResponse(horarioRepository.save(horario));
    }

    @Override
    public HorarioResponseDTO getHorarioById(Integer id) {
        return createResponse(getHorarioByIdOrException(id));
    }

    @Override
    public List<HorarioResponseDTO> getHorarios() {
        return createResponseList(horarioRepository.findAll());
    }

    @Override
    public List<HorarioResponseDTO> getHorariosByDiaId(Integer diaId) {
        return createResponseList(horarioRepository.findAllByDiaId(diaId));
    }

    @Override
    public List<HorarioResponseDTO> getHorariosByFranjaHorariaId(Integer franjaId) {
        return createResponseList(horarioRepository.findAllByFranjaHorariaId(franjaId));
    }

    @Override
    public HorarioResponseDTO updateHorario(Integer id, HorarioRequestDTO request) {
        Horario actualizado = createEntity(request, id);
        Horario antiguo = getHorarioByIdOrException(id);

        actualizado.setId(antiguo.getId());
        actualizado.setCreado(antiguo.getCreado());
        return createResponse(horarioRepository.save(actualizado));
    }

    @Override
    public void deleteHorario(Integer id) {
        Horario horario = getHorarioByIdOrException(id);
        horarioRepository.delete(horario);
    }

    // Métodos auxiliares
    private Horario getHorarioByIdOrException(Integer id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Horario no encontrado"));
    }

    private Dia getDiaByIdOrException(Integer id) {
        return diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Día no encontrado"));
    }

    private FranjaHoraria getFranjaHorariaByIdOrException(Integer id) {
        return franjaHorariaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Franja horaria no encontrada"));
    }

    private Horario createEntity(HorarioRequestDTO request, Integer id) {
        validateUniqueDiaFranja(request, id);

        Dia dia = getDiaByIdOrException(request.getDiaId());
        FranjaHoraria franja = getFranjaHorariaByIdOrException(request.getFranjaId());

        Horario horario = new Horario();
        horario.setDia(dia);
        horario.setFranjaHoraria(franja);
        return horario;
    }

    private void validateUniqueDiaFranja(HorarioRequestDTO request, Integer id) {
        boolean existe = (id == null)
                ? horarioRepository.existsByDiaIdAndFranjaHorariaId(request.getDiaId(), request.getFranjaId())
                : horarioRepository.existsByDiaIdAndFranjaHorariaIdAndIdNot(request.getDiaId(), request.getFranjaId(), id);

        if (existe) {
            throw new RecursoExistenteException("Ya existe un horario con ese día y franja horaria.");
        }
    }

    private HorarioResponseDTO createResponse(Horario horario) {
        return new HorarioResponseDTO(
                horario.getId(),
                horario.getDia().getNombre(),
                horario.getFranjaHoraria().getHoraInicio(),
                horario.getFranjaHoraria().getHoraFin()
        );
    }

    private List<HorarioResponseDTO> createResponseList(List<Horario> lista) {
        List<HorarioResponseDTO> respuesta = new ArrayList<>();
        for (Horario horario : lista) {
            respuesta.add(createResponse(horario));
        }
        return respuesta;
    }
}
