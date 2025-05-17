package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ModuloRequestDTO;
import org.cesde.academic.dto.response.ModuloResponseDTO;
import org.cesde.academic.enums.TipoModulo;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.model.Programa;
import org.cesde.academic.repository.ModuloRepository;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Override
    public ModuloResponseDTO createModulo(ModuloRequestDTO request) {
        Modulo modulo = createEntity(request, null);
        return createResponse(moduloRepository.save(modulo));
    }

    @Override
    public List<ModuloResponseDTO> getModulos() {
        List<Modulo> modulos = moduloRepository.findAll();
        return createResponseList(modulos);
    }

    @Override
    public ModuloResponseDTO getModuloById(Integer id) {
        Modulo modulo = getModuloByIdOrException(id);
        return createResponse(modulo);
    }

    @Override
    public List<ModuloResponseDTO> getModuloByNombre(String nombre) {
        List<Modulo> modulos = moduloRepository.findAllByNombreContainingIgnoreCase(nombre);
        return createResponseList(modulos);
    }

    @Override
    public ModuloResponseDTO updateModulo(Integer id, ModuloRequestDTO request) {
        Modulo updatedModulo = createEntity(request, id);
        Modulo oldModulo = getModuloByIdOrException(id);

        updatedModulo.setId(oldModulo.getId());
        updatedModulo.setCreado(oldModulo.getCreado());

        return createResponse(moduloRepository.save(updatedModulo));
    }

    @Override
    public void deleteModulo(Integer id) {
        Modulo modulo = getModuloByIdOrException(id);
        moduloRepository.delete(modulo);
    }

    // Métodos auxiliares
    private Modulo getModuloByIdOrException(Integer id){
        return moduloRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Módulo no encontrado"));
    }

    private Programa getProgramaByIdOrException(Integer id){
        return programaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no encontrado"));
    }

    private void validateUniqueNombre(String nombre, TipoModulo tipo, Integer id){
        boolean  existe = id == null
                ? moduloRepository.existsByNombreIgnoreCase(nombre)
                : moduloRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);

        if (existe){
            throw new RecursoExistenteException("El módulo ya está registrado");
        }
    }

    private Modulo createEntity(ModuloRequestDTO request, Integer id){
        validateUniqueNombre(request.getNombre(), request.getTipo(), id);

        Modulo modulo = new Modulo();
        modulo.setNombre(request.getNombre());
        modulo.setTipo(request.getTipo());
        return modulo;
    }

    private ModuloResponseDTO createResponse(Modulo modulo){
        return new ModuloResponseDTO(
                modulo.getId(),
                modulo.getNombre(),
                modulo.getTipo(),
                modulo.getCreado(),
                modulo.getActualizado()
        );
    }

    private List<ModuloResponseDTO> createResponseList(List<Modulo> modulos){
        List<ModuloResponseDTO> lista = new ArrayList<>();
        for (Modulo modulo : modulos){
            lista.add(createResponse(modulo));
        }
        return lista;
    }
}
