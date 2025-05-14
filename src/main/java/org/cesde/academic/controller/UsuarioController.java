package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.UsuarioRequestDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO request){
        UsuarioResponseDTO usuario = usuarioService.createUsuario(request);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioResponseDTO>> getListaUsuarios(){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarios();
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Integer id){
        UsuarioResponseDTO usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByNombre(@PathVariable String nombre){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByNombre(nombre);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/cedula/{cedula}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByCedula(@PathVariable String cedula){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByCedula(cedula);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/correo/{correo}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByCorreo(@PathVariable String correo){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByCorreo(correo);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDTO request){
        UsuarioResponseDTO usuario = usuarioService.updateUsuario(id, request);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id){
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
