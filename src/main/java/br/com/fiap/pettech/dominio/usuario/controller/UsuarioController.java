package br.com.fiap.pettech.dominio.usuario.controller;

import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                    @RequestParam(value = "tamanho", defaultValue = "5") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var usuarios = usuarioService.findAll(pageRequest);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable UUID id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuario) {
        var usuarioSaved = usuarioService.save(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((usuarioSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(usuarioSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable UUID id, @RequestBody UsuarioDTO dto) {
        var usuarioUpdated = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuarioUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
