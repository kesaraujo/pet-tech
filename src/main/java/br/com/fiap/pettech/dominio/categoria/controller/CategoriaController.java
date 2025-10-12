package br.com.fiap.pettech.dominio.categoria.controller;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                      @RequestParam(value = "tamanho", defaultValue = "5") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var categorias = categoriaService.findAll(pageRequest);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable UUID id) {
        var categoria = categoriaService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto) {
        var categoriaSaved = categoriaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((categoriaSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(categoriaSaved);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable UUID id, @RequestBody CategoriaDTO dto) {
        var categoriaUpdated = categoriaService.update(id, dto);
        return ResponseEntity.ok(categoriaUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
