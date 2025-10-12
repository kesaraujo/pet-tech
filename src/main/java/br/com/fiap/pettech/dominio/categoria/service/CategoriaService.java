package br.com.fiap.pettech.dominio.categoria.service;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.categoria.repository.ICategoriaRepository;
import br.com.fiap.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;

    public Page<CategoriaDTO> findAll(PageRequest pagina) {
        var categorias = repository.findAll(pagina);
        return categorias.map(c -> new CategoriaDTO(c));
    }

    public CategoriaDTO findById(UUID id) {
        var categoria = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Categoria não encontrada."));
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDataDeCriacao(dto.getDataDeCriacao());
        var categoriaSaved = repository.save(entity);
        return new CategoriaDTO(categoriaSaved);
    }

    public CategoriaDTO update(UUID id, CategoriaDTO dto) {

        try {
            Categoria buscaCategoria = repository.getReferenceById(id);
            buscaCategoria.setNome(dto.getNome());
            buscaCategoria.setDataDeCriacao(dto.getDataDeCriacao());
            buscaCategoria = repository.save(buscaCategoria);
            return new CategoriaDTO(buscaCategoria);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Categoria não encontrada." + id);
        }
    }

    public void delete(UUID id) {
        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Categoria não encontrada." + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade no banco.");
        }
    }
}
