package br.com.fiap.pettech.dominio.pessoa.service;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
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
public class PessoaService {

    @Autowired
    private IPessoaRepository repository;

    public Page<PessoaDTO> findAll(PageRequest pagina) {
        var pessoas = repository.findAll(pagina);
        return pessoas.map(p -> new PessoaDTO(p));
    }

    public PessoaDTO findById(UUID id) {
        var pessoa = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada."));
        return new PessoaDTO(pessoa);
    }

    public PessoaDTO save(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        entity.setNome(dto.getNome());
        entity.setNascimento(dto.getNascimento());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());

        var pessoaSaved = repository.save(entity);
        return new PessoaDTO(entity);
    }

    public PessoaDTO update(UUID id, PessoaDTO dto) {
        try{
            Pessoa buscaPessoa = repository.getReferenceById(id);
            buscaPessoa.setNome(dto.getNome());
            buscaPessoa.setNascimento(dto.getNascimento());
            buscaPessoa.setCpf(dto.getCpf());
            buscaPessoa.setEmail(dto.getEmail());
            buscaPessoa = repository.save(buscaPessoa);
            return new PessoaDTO(buscaPessoa);
        } catch(EntityNotFoundException r) {
            throw new ControllerNotFoundException("Pessoa não encontrada. id " + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade não encontrada." + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade de base.");
        }
    }
}
