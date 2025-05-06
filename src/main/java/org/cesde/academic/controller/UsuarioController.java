package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    // Endpoint para crear un nuevo usuario
    @PostMapping("/crear")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioByCorreo(usuario.getCorreo());

        if(usuarioOptional.isEmpty()){
            Usuario newUsuario = usuarioService.createUsuario(usuario);
            return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    // Endpoint para obtener la lista de usuarios
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> getListaUsuarios(){
        List<Usuario> usuarioList = usuarioService.getUsuarios();

        if(usuarioList.isEmpty()){
            return new ResponseEntity<>(usuarioList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(usuarioList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id){
        Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(id);

        return optionalUsuario.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un usuario
    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario updatedUsuario){
        Optional<Usuario> usuarioById = usuarioService.getUsuarioById(id);
        Optional<Usuario> usuarioByCorreo = usuarioService.getUsuarioByCorreo(updatedUsuario.getCorreo());

        if(usuarioById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (usuarioByCorreo.isPresent() && usuarioById.get().getId() != id) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(usuarioService.updateUsuario(usuarioById.get(), updatedUsuario), HttpStatus.OK);
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id){
        Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(id);

        if(optionalUsuario.isPresent()){
            usuarioService.deleteUsuario(optionalUsuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
