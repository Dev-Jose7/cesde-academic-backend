package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.UsuarioRequestDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    // La anotación @PreAuthorize permite proteger métodos según la autenticación o autorización del usuario actual, usando expresiones SpEL (Spring Expression Language). La puedes poner en métodos de controladores o servicios.
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO request){
        UsuarioResponseDTO usuario = usuarioService.createUsuario(request);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<UsuarioResponseDTO>> getListaUsuarios(){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarios();
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Integer id){
        UsuarioResponseDTO usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByNombre(@PathVariable String nombre){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByNombre(nombre);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/cedula/{cedula}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByCedula(@PathVariable String cedula){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByCedula(cedula);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/correo/{correo}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarioByCorreo(@PathVariable String correo){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByCorreo(correo);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/tipo/{tipo}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsarioByTipo(@PathVariable TipoUsuario tipo){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarioByTipo(tipo);
        return usuarios.isEmpty()
                ? new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDTO request){
        UsuarioResponseDTO usuario = usuarioService.updateUsuario(id, request);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id){
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
